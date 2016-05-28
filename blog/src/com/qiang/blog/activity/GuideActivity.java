package com.qiang.blog.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.qiang.blog.R;
import com.qiang.blog.adapter.GuidePagerAdapter;

public class GuideActivity extends Activity {
	private final static String TAG = "GuideActivity";
	private ViewPager mViewPager;
	private List<View> mViewList;
	private LayoutInflater mInflater;
	private GuidePagerAdapter mPagerAdapter;
	private View intro1;
	private View intro2;
	private View intro3;
	private View intro4;
	private int mIsFirst;
	SharedPreferences sharedPreferences ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getData();
		setContentView(R.layout.activity_guide);
		initView();
		initEvent();
		initData();
	}

	private void getData() {
		mInflater = LayoutInflater.from(this);
		sharedPreferences = getSharedPreferences("app_guide_count",Context.MODE_APPEND);
		mIsFirst = sharedPreferences.getInt("app_guide_show_first", 0);
		Log.v(TAG, mIsFirst + "");
	}

	private void initView() {
		if (mViewList == null) {
			mViewList = new ArrayList<View>();
		}

		if(mIsFirst == 0){
			intro1 = mInflater.inflate(R.layout.intro1, null);
			intro2 = mInflater.inflate(R.layout.intro2, null);
			intro3 = mInflater.inflate(R.layout.intro3, null);
			mViewList.add(intro1);
			mViewList.add(intro2);
			mViewList.add(intro3);
			sharedPreferences.edit().putInt("app_guide_show_first", 1).commit();
			//sharedPreferences.edit().commit();
		}
		
		intro4 = mInflater.inflate(R.layout.intro4, null);
		mViewList.add(intro4);

		mViewPager = (ViewPager) findViewById(R.id.app_guide_viewpager);
		mPagerAdapter = new GuidePagerAdapter(this, mViewList);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setCurrentItem(0, true);
		// initViewList();
	}

	private void initEvent(){
		intro4.findViewById(R.id.get_started).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoMainTab();
				back();
			}
		});
	}
	
	private void gotoMainTab(){
		Intent intent = new Intent(this, TabMainActivity.class);
		startActivity(intent);
	}
	
	private void initData() {

	}

	private void initViewList() {
		if (mViewList == null) {
			mViewList = new ArrayList<View>();
		}

		mViewList.add(mInflater.inflate(R.layout.intro1, null));
		mViewList.add(mInflater.inflate(R.layout.intro2, null));
		mViewList.add(mInflater.inflate(R.layout.intro3, null));
		mViewList.add(mInflater.inflate(R.layout.intro4, null));
	}
	
	private void back(){
		finish();
	}
}
