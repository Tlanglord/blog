package com.qiang.blog.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.qiang.blog.R;
import com.qiang.blog.adapter.FragmentAdapter;
import com.qiang.blog.fragment.DiscoveryFragment;
import com.qiang.blog.fragment.HomeFragment;
import com.qiang.blog.fragment.OwnerFragment;
import com.qiang.blog.utils.UIUtils;

public class TabMainActivity extends FragmentActivity implements
		OnClickListener, OnPageChangeListener, OnCheckedChangeListener {

	private long mExitTime;
	private RadioButton mTabHome;
	private RadioGroup mTabRadioGroup;
	private RadioButton mTabDiscovery;
	private RadioButton mTabOwner;
	private int mCurrentTabIndex = 0;
	private int mSelcetedTabIndex = 0;
	private ViewPager mViewPager;
	private List<Fragment> mFragments;
	private RadioButton[] mRadioButtons = new RadioButton[3];

	private Fragment mHomeFragment;
	private Fragment mDiscoveryFragment;
	private Fragment mOwnerFragment;

	private FragmentManager mFragmentManager;
	private FragmentAdapter mFragmentAdapter;
	
	private int lastPos = 0;

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_main);
		UIUtils.setStatusColor(this, "#4499ff");
		initView();
		initEvent();
		initData();
	}

	// private void setStatusColor(Activity activity, String color) {
	// setStatusColor(activity, Color.parseColor(color));
	// }
	//
	// private void setStatusColor(Activity activity, int color) {
	// Window window = activity.getWindow();
	// if (VERSION.SDK_INT <= VERSION_CODES.KITKAT) {
	// window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	// ViewGroup decorView = (ViewGroup) window.getDecorView();
	// View statusView = createStatusView(this, color);
	// decorView.addView(statusView);
	// } else {
	// window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	// window.getDecorView().setSystemUiVisibility(
	// View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	// | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
	// window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
	// window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	// window.setStatusBarColor(color);
	//
	// }
	// }
	//
	// private View createStatusView(Activity activity, int color) {
	// int resourceId = activity.getResources().getIdentifier(
	// "status_bar_height", "dimen", "android");
	// int statusBarHeight = activity.getResources().getDimensionPixelSize(
	// resourceId);
	// View statusView = new View(activity);
	// LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	// ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
	// statusView.setLayoutParams(params);
	// statusView.setBackgroundColor(color);
	// return statusView;
	// }

	private void initEvent() {
		mTabHome.setOnClickListener(this);
		mTabDiscovery.setOnClickListener(this);
		mTabOwner.setOnClickListener(this);
		mViewPager.setOnPageChangeListener(this);
		mTabRadioGroup.setOnCheckedChangeListener(this);
	}

	private void initData() {
		initRadios();
		initContent();
	}

	private void initRadios() {
		for (int i = 0; i < mTabRadioGroup.getChildCount(); i++) {
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

	private void initContent() {
		mFragmentManager = getSupportFragmentManager();
		mFragments = new ArrayList<Fragment>();
		mFragmentAdapter = new FragmentAdapter(mFragmentManager, mFragments);
		mViewPager.setAdapter(mFragmentAdapter);
		mHomeFragment = new HomeFragment();
		mFragments.add(mHomeFragment);
		mDiscoveryFragment = new DiscoveryFragment();
		mFragments.add(mDiscoveryFragment);
		mOwnerFragment = new OwnerFragment();
		mFragments.add(mOwnerFragment);
		setCurrentTab(0);
	}

	private void setCurrentTab(int currentIndex) {
		// FragmentTransaction transaction =
		// mFragmentManager.beginTransaction();
		// hideFragments(transaction);
		// switch (currentIndex) {
		// case 0:
		// if (mHomeFragment == null) {
		// mHomeFragment = new HomeFragment();
		// mFragments.add(mHomeFragment);
		// } else {
		// transaction.show(mHomeFragment);
		// }
		// break;
		// case 1:
		// if (mDiscoveryFragment == null) {
		// mDiscoveryFragment = new DiscoveryFragment();
		// mFragments.add(mDiscoveryFragment);
		// } else {
		// transaction.show(mDiscoveryFragment);
		// }
		// break;
		// case 2:
		// if (mOwnerFragment == null) {
		// mOwnerFragment = new OwnerFragment();
		// mFragments.add(mOwnerFragment);
		// } else {
		// transaction.show(mOwnerFragment);
		// }
		// break;
		// default:
		// break;
		// }
		// transaction.commit();
		
		
		mFragmentAdapter.notifyDataSetChanged();
		mViewPager.setCurrentItem(currentIndex, true);
		mViewPager.setOffscreenPageLimit(2);
		//mViewPager.setPageTransformer(false, PageTransformer)
		mRadioButtons[currentIndex].setChecked(true);
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (mHomeFragment != null) {
			transaction.hide(mHomeFragment);
		}

		if (mDiscoveryFragment != null) {
			transaction.hide(mDiscoveryFragment);
		}

		if (mOwnerFragment != null) {
			transaction.hide(mOwnerFragment);
		}
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

		if (R.id.tab_home == checkedId) {
			mSelcetedTabIndex = 0;
		} else if (R.id.tab_discovery == checkedId) {
			mSelcetedTabIndex = 1;
		} else {
			mSelcetedTabIndex = 2;
		}

		if (mSelcetedTabIndex != mCurrentTabIndex) {
			mCurrentTabIndex = mSelcetedTabIndex;
			setCurrentTab(mCurrentTabIndex);
		}
	}

	@Override
	public void onClick(View v) {

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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((System.currentTimeMillis() - mExitTime) > 2000) {
			Toast.makeText(this, "再次点击返回退出博客", Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}
		return false;
	}

}
