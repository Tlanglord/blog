package com.qiang.blog.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.qiang.blog.R;
import com.qiang.blog.adapter.FragmentAdapter;
import com.qiang.blog.fragment.DiscoveryFragment;
import com.qiang.blog.fragment.HomeFragment;
import com.qiang.blog.fragment.OwnerFragment;

public class TabMainActivity extends FragmentActivity implements
		OnClickListener, OnPageChangeListener, OnCheckedChangeListener {

	private RadioButton mTabHome;
	private RadioGroup mTabRadioGroup;
	private RadioButton mTabDiscovery;
	private RadioButton mTabOwner;
	private int mCurrentTabIndex = 0;
	private int mSelcetedTabIndex = 0;
	private ViewPager mViewPager;
	private List<Fragment> mFragments;
	private RadioButton[] mRadioButtons = new RadioButton[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_main);
		initView();
		initEvent();
		initData();
	}

	private void initEvent() {
		mTabHome.setOnClickListener(this);
		mTabDiscovery.setOnClickListener(this);
		mTabOwner.setOnClickListener(this);
		mViewPager.setOnPageChangeListener(this);
		mTabRadioGroup.setOnCheckedChangeListener(this);
	}

	private void initData() {
		mFragments = new ArrayList<Fragment>();
		mFragments.add(new HomeFragment());
		mFragments.add(new DiscoveryFragment());
		mFragments.add(new OwnerFragment());
		mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
				mFragments));
		initRadios();
		setCurrentTab(0);

	}

	private void initRadios() {
		for(int i = 0; i < mTabRadioGroup.getChildCount(); i++){
			mRadioButtons[i] = (RadioButton) mTabRadioGroup.getChildAt(i);
		}
	}

	private void initView() {
		mTabHome = (RadioButton) findViewById(R.id.tab_home);
		mTabDiscovery = (RadioButton) findViewById(R.id.tab_discovery);
		mTabOwner = (RadioButton) findViewById(R.id.tab_owner);
		mViewPager = (ViewPager) findViewById(R.id.tab_viewpager);
		mTabRadioGroup = (RadioGroup) findViewById(R.id.tab_radiogroup);
	}

//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.tab_home:
//			mSelcetedTabIndex = 0;
//			break;
//		case R.id.tab_discovery:
//			mSelcetedTabIndex = 1;
//			break;
//		case R.id.tab_owner:
//			mSelcetedTabIndex = 2;
//			break;
//		}
//
//		if (mSelcetedTabIndex != mCurrentTabIndex) {
//			mCurrentTabIndex = mSelcetedTabIndex;
//			setCurrentTab(mCurrentTabIndex);
//		}
//	}

	private void setCurrentTab(int currentIndex) {
		mViewPager.setCurrentItem(currentIndex, true);
		mRadioButtons[currentIndex].setChecked(true);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		setCurrentTab(position);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		Log.v("checkedId", checkedId + "");
		
		if(R.id.tab_home == checkedId){
			mSelcetedTabIndex = 0;
		}else if(R.id.tab_discovery == checkedId){
			mSelcetedTabIndex = 1;
		}else {
			mSelcetedTabIndex = 2;
		}
		
		if (mSelcetedTabIndex != mCurrentTabIndex) {
			mCurrentTabIndex = mSelcetedTabIndex;
			setCurrentTab(mCurrentTabIndex);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	// protected void initData()
	// {
	// mDatas = new ArrayList<String>();
	// for (int i = 'A'; i < 'z'; i++)
	// {
	// mDatas.add("" + (char) i);
	// }
	// }
	//
	// class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
	// {
	//
	// @Override
	// public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	// {
	// MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
	// TabMainActivity.this).inflate(R.layout.item_home, parent,
	// false));
	// return holder;
	// }
	//
	// @Override
	// public void onBindViewHolder(MyViewHolder holder, int position)
	// {
	// holder.tv.setText(mDatas.get(position));
	// }
	//
	// @Override
	// public int getItemCount()
	// {
	// return mDatas.size();
	// }
	//
	// class MyViewHolder extends ViewHolder
	// {
	//
	// TextView tv;
	//
	// public MyViewHolder(View view)
	// {
	// super(view);
	// tv = (TextView) view.findViewById(R.id.id_num);
	// }
	// }
	// }

}
