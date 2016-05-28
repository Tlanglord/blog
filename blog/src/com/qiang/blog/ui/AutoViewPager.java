package com.qiang.blog.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;

public class AutoViewPager extends ViewPager implements OnPageChangeListener{

	private final static int AUTO = 0;
	private int mCount = 0;
	private int mRawCount = 1;
	private boolean mIsAuto = true;
	private Timer mTimer;
	private long mDelay, mPeriod;
	private TimerTask mTask;

	private PagerAdapter mPagerAdapter;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case AUTO:
				if (mIsAuto) {
					mCount++ ;//% mRawCount;
					setCurrentItem(mCount, true);
				}
				break;
			default:
				break;
			}
		};
	};

	public AutoViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public AutoViewPager(Context context) {
		this(context, null);
	}

	private void init(Context context) {
		mPagerAdapter = getAdapter();
		if (mPagerAdapter != null) {
			mRawCount = mPagerAdapter.getCount();
		}
		setOnPageChangeListener(this);
	}

	public void setDelay(long delay) {
		setDelayAndPeriod(delay, delay);
	}

	public void setDelayAndPeriod(long delay, long period) {
		mDelay = delay;
		mPeriod = period;
	}

	public void startAuto() {
		if (getAdapter() != null) {
			mTimer = new Timer();
			mTask = new TimerTask() {
				@Override
				public void run() {
					mHandler.sendEmptyMessage(AUTO);
				}
			};
			mTimer.schedule(mTask, mDelay, mPeriod);
		}
	}
	
	public void setIsAuto(boolean isAuto){
		mIsAuto = isAuto;
	}

	@Override
	public void onPageSelected(int position) {
		mCount = position;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		switch (state) {
		case ViewPager.SCROLL_STATE_DRAGGING:
			break;
		case ViewPager.SCROLL_STATE_IDLE:
			break;
		case ViewPager.SCROLL_STATE_SETTLING:
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public void onPageScrolled(int position, float offset, int offsetPixels) {
		super.onPageScrolled(position, offset, offsetPixels);
	}
	
	public void stopAuto(){
		if(mTimer != null){
			mTimer.cancel();
		}
		
		if(mTask != null){
			mTask.cancel();
		}
	}

}
