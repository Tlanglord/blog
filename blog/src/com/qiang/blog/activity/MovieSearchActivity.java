package com.qiang.blog.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.qiang.blog.R;
import com.qiang.blog.adapter.MovieSearchAdapter;
import com.qiang.blog.movie.entity.MovieInTheater;
import com.qiang.blog.movie.entity.Subjects;
import com.qiang.blog.requestbase.BaseActivity;
import com.qiang.blog.requestbase.RequestWrapper;

public class MovieSearchActivity extends BaseActivity implements TextWatcher, OnClickListener {

    private EditText mMSEdit;
    private ListView mMSListView;
    private TextView mMSSearch;
    
    private MovieSearchAdapter mMSAdapter;
    
    private MovieInTheater mMSSearchInfo;
    private List<Subjects> mMSSubjects;
    
    private String mSearchKey="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mMSSubjects = new ArrayList<Subjects>();
    }

    @Override
    protected int onContentView() {
	return R.layout.activity_movie_search;
    }

    @Override
    protected void onInitView() {
	mMSEdit = (EditText) findViewById(R.id.movie_search_edit);
	mMSSearch = (TextView) findViewById(R.id.movie_search_action);
	mMSListView = (ListView) findViewById(R.id.movie_search_listview);
    }

    @Override
    protected void onInitEvent() {
	mMSEdit.addTextChangedListener(this);
	mMSSearch.setOnClickListener(this);
    }

    @Override
    public void onResponse(Object tag, String response) {
	super.onResponse(tag, response);
	if ((Integer) tag == 1 && !TextUtils.isEmpty(response)) {
	    mMSSearchInfo = JSON.parseObject(response, MovieInTheater.class);
	    if (mMSSearchInfo != null) {
		if (mMSSubjects == null) {
		    mMSSubjects = new ArrayList<Subjects>();
		}
		mMSSubjects.clear();
		mMSSubjects.addAll(mMSSearchInfo.getSubjects());
		if (mMSAdapter == null) {
		    mMSAdapter = new MovieSearchAdapter(this, mMSSubjects);
		    mMSListView.setAdapter(mMSAdapter);
		}
		mMSAdapter.notifyDataSetChanged();
		mMSAdapter.notifyDataSetInvalidated();
	    }
	}
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
	    int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
	
    }

    @Override
    public void afterTextChanged(Editable s) {
	mSearchKey = s.toString();
	if(mSearchKey.equals("") && mMSAdapter != null && mMSSubjects != null){
	    mMSSubjects.clear();
	    mMSAdapter.notifyDataSetChanged();
	}
    }

    private void requestSearch(String key) {
	if(!key.equals("")){
	    String url = "https://api.douban.com/v2/movie/search?";
		if (!key.contains("剧")) {
		    url += "q=";
		} else {
		    url += "tag=";
		}

		url += key;
		RequestWrapper rwp = new RequestWrapper();
		rwp.setUrl(url);
		rwp.setTag(1);
		requestConnection(rwp);
	}else{
	    Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
	}
	

    }

    @Override
    public void onClick(View v) {
	switch(v.getId()){
	case R.id.movie_search_action:
	    requestSearch(mSearchKey);
	}
    }

}
