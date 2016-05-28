package com.qiang.blog.activity;

import com.qiang.blog.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class MemoActivity extends Activity {
	private TextView mMemoBack;
	private RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memo);
		mMemoBack=(TextView)findViewById(R.id.memo_back);
		mRecyclerView=(RecyclerView)findViewById(R.id.memo_recycler_view);		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
