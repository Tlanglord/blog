package com.qiang.blog.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiang.blog.R;
import com.qiang.blog.adapter.MemoRecyclerAdapter;
import com.qiang.blog.adapter.MemoTaskAdapter;
import com.qiang.blog.requestbase.BaseActivity;
import com.qiang.blog.ui.SuperRecyclerView;
import com.qiang.blog.ui.SuperRecyclerView.OnItemLongClickListener;
import com.qiang.blog.utils.DBOpenHelper;

public class MemoActivity extends BaseActivity implements OnClickListener {
    public final static int REQUESTCODE_ADD_MEMO = 1;
    public final static int REQUESTCODE_CHECK_MEMO = 2;

    private SuperRecyclerView mRecyclerView;
    private TextView mMemoTitleLeft;
    private TextView mMemoTitleRight;
    private LinearLayout mMemoAddNewTask;
    private DBOpenHelper mDbOpenHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private List<Map<String, String>> mlist = new ArrayList<Map<String, String>>();
    private MemoRecyclerAdapter mMemoRecyclerAdapter;
    private int mPageType = 0;

    public int getPageType() {
        return mPageType;
    }

    public void setPageType(int pageType) {
        this.mPageType = pageType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mRecyclerView.setItemAnimator(new DefaultItemAnimator());
	mDbOpenHelper = new DBOpenHelper(MemoActivity.this, "memo.db", 5);
	mMemoRecyclerAdapter = new MemoRecyclerAdapter(this, mlist,
		mRecyclerView);
	mSqLiteDatabase = mDbOpenHelper.getReadableDatabase();
	queryData();
	setPageType(0);
	mRecyclerView.setAdapter(mMemoRecyclerAdapter);
	mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
	
	//AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);  
    }

    private void queryData() {
	Cursor cursor = mSqLiteDatabase.rawQuery("select * from memo", null);
	convertCursorToList(cursor);
	mMemoRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.memo_title_left:
	    setPageType(0);
	    queryData();
	    mRecyclerView.setAdapter(mMemoRecyclerAdapter);
	    mRecyclerView
		    .setLayoutManager(new StaggeredGridLayoutManager(2, 1));
	    break;
	case R.id.memo_title_right:
	    setPageType(1);
	    mRecyclerView.setAdapter(new MemoTaskAdapter(MemoActivity.this));
	    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
	    break;
	case R.id.memo_add_new_task:
	    if(isAddMemo()){
		Intent intent = new Intent(this, MemoAddActivity.class);
		startActivityForResult(intent, REQUESTCODE_ADD_MEMO);
	    }else if(isAddTaskMemo()){
		Intent intent = new Intent(this, MemoTaskAddActivity.class);
		startActivity(intent);
	    }
	    break;
	}

    }

    @Override
    protected int onContentView() {
	return R.layout.activity_memo;
    }

    @Override
    protected void onInitView() {
	mRecyclerView = (SuperRecyclerView) findViewById(R.id.memo_recycler_view);
	mMemoAddNewTask = (LinearLayout) findViewById(R.id.memo_add_new_task);
	mMemoTitleLeft = (TextView) findViewById(R.id.memo_title_left);
	mMemoTitleRight = (TextView) findViewById(R.id.memo_title_right);
    }

    @Override
    protected void onInitEvent() {
	mMemoTitleLeft.setOnClickListener(this);
	mMemoTitleRight.setOnClickListener(this);
	mMemoAddNewTask.setOnClickListener(this);
	mRecyclerView.setOnItemLongClickListener(new OnItemLongClickListener() {

	    @Override
	    public void onItemLongClick(int position) {
		if (mlist != null && mlist.size() > position
			&& mSqLiteDatabase != null) {
		    mSqLiteDatabase.delete("memo", "_id="
			    + mlist.get(position).get("_id"), null);
		}
	    }
	});
    }

    private List<Map<String, String>> convertCursorToList(Cursor cursor) {
	if (mlist == null) {
	    mlist = new ArrayList<Map<String, String>>();
	}
	mlist.clear();
	String[] keys = cursor.getColumnNames();
	while (cursor.moveToNext()) {
	    Map<String, String> map = new HashMap<String, String>();
	    for (int i = 0; i < keys.length; i++) {

		// if (!keys[i].equals("_id"))
		map.put(keys[i], cursor.getString(i));
	    }
	    mlist.add(map);
	}

	return mlist;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);

	if (requestCode == REQUESTCODE_ADD_MEMO) {
	    queryData();
	} else if (requestCode == REQUESTCODE_CHECK_MEMO) {
	    queryData();
	}
    }
    
    
    private boolean  isAddMemo(){
	return getPageType() == 0;
    }
    
    private boolean  isAddTaskMemo(){
   	return getPageType() == 1;
       }
}
