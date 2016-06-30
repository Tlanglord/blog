package com.qiang.blog.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.qiang.blog.R;
import com.qiang.blog.entity.MemoEntity;
import com.qiang.blog.requestbase.BaseActivity;
import com.qiang.blog.ui.RichEditText2;
import com.qiang.blog.utils.DBOpenHelper;

public class MemoAddActivity extends BaseActivity implements OnClickListener {

    private final static int REQUESTCODE_TO_IMAGE = 1;

    private TextView mMemoAddImage;
    private RichEditText2 mMemoRichEdit;
    private DBOpenHelper mDbOpenHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private MemoEntity mEntity;
    private int mActionType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mEntity = (MemoEntity) getIntent().getSerializableExtra("memo");
	if (mEntity != null) {
	    setActionType(mEntity.getMemo_action());
	}
	mDbOpenHelper = new DBOpenHelper(this, "memo.db", 5);
	mSqLiteDatabase = mDbOpenHelper.getWritableDatabase();

	if (isCheckMemo() && mEntity != null) {
	    mMemoRichEdit.setText(Html.fromHtml(mEntity.getMemo_content(),
		    imgGetter, null));
	}
    }

    @Override
    protected int onContentView() {
	return R.layout.activity_memo_add;
    }

    @Override
    protected void onInitView() {
	mMemoAddImage = (TextView) findViewById(R.id.memo_add_image);
	mMemoRichEdit = (RichEditText2) findViewById(R.id.memo_add_rich_edit);
    }

    @Override
    protected void onInitEvent() {
	mMemoAddImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.memo_add_image:
	    Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
	    albumIntent.setDataAndType(
		    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
	    startActivityForResult(albumIntent, REQUESTCODE_TO_IMAGE);
	}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == REQUESTCODE_TO_IMAGE) {
	    if (data != null) {
		Uri uri = data.getData();
		if (uri != null) {
		    String[] proj = { MediaStore.Images.Media.DATA };
		    Cursor cursor = managedQuery(uri, proj, null, null, null);
		    int column_index = cursor
			    .getColumnIndex(MediaStore.Images.Media.DATA);
		    cursor.moveToFirst();
		    String path = cursor.getString(column_index);
		    mMemoRichEdit.setImgUrl(path);
		    mMemoRichEdit.notifyDataSetChanged();
		}
	    }
	}
    }

    @Override
    protected void onDestroy() {
	super.onDestroy();
	if (mSqLiteDatabase != null) {
	    mSqLiteDatabase.close();
	}
    }

    @Override
    protected void back() {

	if (!TextUtils.isEmpty(mMemoRichEdit.getText().toString())
		&& isAddMemo()) {
	    ContentValues values = new ContentValues();
	    values.put("memo_type", 1);
	    values.put("memo_overdue", false);
	    values.put("memo_content", mMemoRichEdit.getText().toString()
		    .toString());
	    values.put("memo_createtime", new Time().format("T-HH-MM"));
	    values.put("memo_date", new Time().format("YYYY年MM月DD日"));

	    long id = mSqLiteDatabase.insert("memo", null, values);
	    Toast.makeText(this, "----  " + id + " ------", Toast.LENGTH_SHORT)
		    .show();

	} else if (isCheckMemo() && mEntity != null) {
	    ContentValues values = new ContentValues();
	    values.put("memo_content", mMemoRichEdit.getText().toString());
	    long id = mSqLiteDatabase.update("memo", values, "_id=" + mEntity.getMemo_id(), null);
	}
	super.back();
    }

    private int getActionType() {
	return mActionType;
    }

    private void setActionType(int actionType) {
	this.mActionType = actionType;
    }

    private boolean isAddMemo() {
	return getActionType() == 0;
    }

    private boolean isCheckMemo() {
	return getActionType() == 1;
    }

    ImageGetter imgGetter = new Html.ImageGetter() {
	@Override
	public Drawable getDrawable(String source) {
	    Drawable drawable = null;
	    try {
		drawable = Drawable.createFromPath(source);
	    } catch (Exception e) {
		return null;
	    }
	    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
		    drawable.getIntrinsicHeight());
	    return drawable;
	}
    };
    
    
}
