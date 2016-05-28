package com.qiang.blog.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qiang.blog.R;
import com.qiang.blog.adapter.AutoPagerAdapter;
import com.qiang.blog.adapter.MovieListAdapter;
import com.qiang.blog.movie.entity.MovieInTheater;
import com.qiang.blog.movie.entity.Subjects;
import com.qiang.blog.requestbase.BaseActivity;
import com.qiang.blog.requestbase.RequestWrapper;
import com.qiang.blog.ui.ViewPagerScroller;

public class MovieActivity extends BaseActivity implements OnItemClickListener {

	private ViewPager mViewPager;
	private final static int auto = 0;
	private List<View> mViewlist = new ArrayList<View>();
	private List<String> mImageList = new ArrayList<String>();
	private int count = 0;
	private int rawCount = 1;
	private boolean isStartAuto = true;
	private AutoPagerAdapter mAdapter;
	private MovieInTheater mMInTheater;
	private ListView mMvListView;
	private static final float MIN_SCALE = 0.75f;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case auto:
				if (isStartAuto) {
					count++;
					if (count >= 0) {
						mViewPager.setCurrentItem(count, true);
					}
				}
				break;
			default:
				break;
			}
		};
	};

	private View getListHeader() {
		return LayoutInflater.from(this).inflate(R.layout.movie_viewpager_layout, null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestMovie();

	}

	public class StackAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(MovieActivity.this);
			textView.setBackgroundColor(Color.parseColor("#4499ff"));
			LayoutParams params = new LayoutParams(100, 100);
			textView.setLayoutParams(params);
			textView.setTextColor(Color.parseColor("#ffffff"));
			textView.setPadding(10, 10, 10, 10);
			return textView;
		}

	}

	private View getView(String text) {
		ImageView imageView = new ImageView(MovieActivity.this);
		// imageView.setBackgroundColor(Color.parseColor("#4499ff"));
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		imageView.setLayoutParams(params);
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setPadding(20, 20, 20, 20);
		return imageView;
	}

	private void requestMovie() {
		RequestWrapper reqWrp = new RequestWrapper();
		reqWrp.setUrl("https://api.douban.com/v2/movie/in_theaters");
		requestConnection(reqWrp);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (parent.getId() == mMvListView.getId() && mMInTheater != null) {
		        int pos = position - mMvListView.getHeaderViewsCount();
			Subjects subject = mMInTheater.getSubjects().get(pos);
			String movieId = subject.getId();
			Intent intent = new Intent(this, MovieDetailActivity.class);
			intent.putExtra("movieid", movieId);
			startActivity(intent);
		}
	}

	@Override
	public void onResponse(Object tag, String response) {
		super.onResponse(tag, response);
		if (TextUtils.isEmpty(response)) {
			return;
		}
		mMInTheater = JSON.parseObject(response, MovieInTheater.class);

		if (mMInTheater != null) {
			for (int i = 0; i < mViewlist.size(); i++) {
				List<Subjects> subjects = mMInTheater.getSubjects();
				if (subjects != null && subjects.size() > 0 && subjects.size() > i) {
					Subjects subject = subjects.get(i);
					if (subject != null && subject.getImages() != null) {
						String url = subject.getImages().getLarge();
						mImageList.add(url);
					}
				}
			}
		}
		MovieListAdapter adapter = new MovieListAdapter(MovieActivity.this, mMInTheater.getSubjects());
		mMvListView.setAdapter(adapter);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	protected int onContentView() {
		return R.layout.activity_movie;
	}

	@Override
	protected void onInitView() {

		View view = getListHeader();
		mViewPager = (ViewPager) view.findViewById(R.id.movie_viewpager);
		mMvListView = (ListView) findViewById(R.id.movie_listview);
		mMvListView.setOnItemClickListener(this);
		mMvListView.addHeaderView(view);

		// count = 0;
		mViewlist.add(getView("11111111"));
		mViewlist.add(getView("22222222"));
		mViewlist.add(getView("33333333"));
		mViewlist.add(getView("44444444"));
		rawCount = mViewlist.size();

		mViewPager.setAdapter(mAdapter = new AutoPagerAdapter(MovieActivity.this, mViewlist, mImageList));

		try {
			Field field = ViewPager.class.getDeclaredField("mScroller");
			field.setAccessible(true);
			ViewPagerScroller scroller = new ViewPagerScroller(mViewPager.getContext(), new AccelerateInterpolator());
			scroller.setDuration(1000);
			field.set(mViewPager, scroller);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		mViewPager.setPageTransformer(true, new PageTransformer() {

			@Override
			public void transformPage(View page, float position) {

				int pageWidth = page.getWidth();

				if (position < -1) { // [-Infinity,-1)  
					// This page is way off-screen to the left.  
					page.setAlpha(0);

				} else if (position <= 0) { // [-1,0]   a
					// Use the default slide transition when moving to the left page  
					page.setAlpha(1);
					page.setTranslationX(0);
					page.setScaleX(1);
					page.setScaleY(1);

				} else if (position <= 1) { // (0,1]   b 
					// Fade the page out.  
					page.setAlpha(1 - position);

					// Counteract the default slide transition  
					page.setTranslationX(pageWidth * -position);

					// Scale the page down (between MIN_SCALE and 1)  
					float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
					page.setScaleX(scaleFactor);
					page.setScaleY(scaleFactor);

				} else { // (1,+Infinity]  
					page.setAlpha(0);
				}
			}
		});
		mViewPager.setPageMargin(20);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				count = position;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});

		Timer mTimer = new Timer();
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				mHandler.sendEmptyMessage(auto);
			}
		}, 3000, 3000);
	}

	@Override
	protected void onInitEvent() {
	}
}
