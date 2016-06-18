package com.qiang.blog.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qiang.blog.R;
import com.qiang.blog.adapter.AutoPagerAdapter;
import com.qiang.blog.adapter.MovieListAdapter;
import com.qiang.blog.anim.ScalePageTransformer;
import com.qiang.blog.movie.entity.MovieInTheater;
import com.qiang.blog.movie.entity.Subjects;
import com.qiang.blog.requestbase.BaseActivity;
import com.qiang.blog.requestbase.RequestWrapper;
import com.qiang.blog.ui.ViewPagerScroller;
import com.qiang.blog.utils.UIUtils;

public class MovieActivity extends BaseActivity implements OnItemClickListener,
	OnClickListener {

    private static final int auto = 0;
    private static final float MIN_SCALE = 0.75f;

    private ViewPager mViewPager;
    private AutoPagerAdapter mAdapter;
    private ListView mMvListView;
    
    private LinearLayout mMvSearch;

    private MovieInTheater mMInTheater;
    private List<View> mViewlist = new ArrayList<View>();
    private List<String> mImageList = new ArrayList<String>();

    private int count = 0;
    private int rawCount = 1;

    private boolean isStartAuto = true;

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
	LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
		LayoutParams.MATCH_PARENT);
	imageView.setLayoutParams(params);
	imageView.setScaleType(ScaleType.FIT_XY);
	int padding = UIUtils.dp2px(MovieActivity.this, 5f);
	imageView.setPadding(padding, 0, padding, padding);
	return imageView;
    }

    private void requestMovie() {
	RequestWrapper reqWrp = new RequestWrapper();
	reqWrp.setUrl("https://api.douban.com/v2/movie/in_theaters");
	requestConnection(reqWrp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
	    long id) {
	if (parent.getId() == mMvListView.getId() && mMInTheater != null) {
	    // int pos = position - mMvListView.getHeaderViewsCount();
	    // Subjects subject = mMInTheater.getSubjects().get(pos);
	    // String movieId = subject.getId();
	    // Intent intent = new Intent(this, MovieDetailActivity.class);
	    // intent.putExtra("movieid", movieId);
	    // startActivity(intent);
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
		if (subjects != null && subjects.size() > 0
			&& subjects.size() > i) {
		    Subjects subject = subjects.get(i);
		    if (subject != null && subject.getImages() != null) {
			String url = subject.getImages().getLarge();
			mImageList.add(url);
		    }
		}
	    }
	}
	adapter = new MovieListAdapter(MovieActivity.this,
		mMInTheater.getSubjects());
	mMvListView.setAdapter(adapter);
	mAdapter.notifyDataSetChanged();
    }

    MovieListAdapter adapter = null;

    @Override
    protected int onContentView() {
	return R.layout.activity_movie;
    }

    @Override
    protected void onInitView() {

	View view = getListHeader();
	mViewPager = (ViewPager) view.findViewById(R.id.movie_viewpager);
	mMvListView = (ListView) findViewById(R.id.movie_listview);
	mMvSearch = (LinearLayout) findViewById(R.id.movie_search);
	mMvListView.setOnItemClickListener(this);
	mMvListView.addHeaderView(view);
	mMvSearch.setOnClickListener(this);
	// count = 0;
	mViewlist.add(getView("11111111"));
	mViewlist.add(getView("22222222"));
	mViewlist.add(getView("33333333"));
	mViewlist.add(getView("44444444"));
	rawCount = mViewlist.size();
	

	// LayoutTransition transition = new LayoutTransition();
	// transition.setStagger(LayoutTransition.CHANGE_APPEARING,
	// Integer.MAX_VALUE);
	// mMvListView.setLayoutTransition(transition);
	//
//	 ObjectAnimator mAnimatorAppearing = ObjectAnimator.ofFloat(null,
//	 "rotationX", -90.0f, 0.0f);
	// transition.getDuration(500);
	// // 为LayoutTransition设置动画及动画类型
	// transition.setAnimator(LayoutTransition.CHANGE_APPEARING,
	// mAnimatorAppearing);
	//
	// mAnimatorAppearing.addListener(new AnimatorListenerAdapter() {
	// @Override
	// public void onAnimationEnd(Animator animation) {
	// // TODO Auto-generated method stub
	// super.onAnimationEnd(animation);
	// // View view = (View) ((ObjectAnimator) animation).getTarget();
	// // view.setRotationX(0.0f);
	// }
	// });

	// transition.setDuration(300);
	// Animation animation = new RotateAnimation(0f, 360f);
	// Animation animation1 = new TranslateAnimation(
	// TranslateAnimation.RELATIVE_TO_SELF, 1,
	// TranslateAnimation.RELATIVE_TO_SELF, 1,
	// TranslateAnimation.RELATIVE_TO_SELF, 0,
	// TranslateAnimation.RELATIVE_TO_SELF, 1);
	// animation.setDuration(500);
	// animation1.setDuration(500);
	// LayoutAnimationController controller = new LayoutAnimationController(
	// animation1, 1f);
	// controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
	//
	// mMvListView.setLayoutAnimation(controller);

	mViewPager.setAdapter(mAdapter = new AutoPagerAdapter(
		MovieActivity.this, mViewlist, mImageList));

	mMvListView.setOnScrollListener(new OnScrollListener() {

	    @Override
	    public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void onScroll(AbsListView view, int firstVisibleItem,
		    int visibleItemCount, int totalItemCount) {
		// adapter.notifyDataSetChanged();
	    }
	});

	try {
	    Field field = ViewPager.class.getDeclaredField("mScroller");
	    field.setAccessible(true);
	    ViewPagerScroller scroller = new ViewPagerScroller(
		    mViewPager.getContext(), new AccelerateInterpolator());
	    scroller.setDuration(1000);
	    field.set(mViewPager, scroller);
	} catch (NoSuchFieldException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	}
	mViewPager.setPageTransformer(false, new ScalePageTransformer());
	// mViewPager.setPageMargin(50);
	// mViewPager.setOffscreenPageLimit(3);
	mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

	    @Override
	    public void onPageSelected(int position) {
		count = position;
	    }

	    @Override
	    public void onPageScrolled(int position, float positionOffset,
		    int positionOffsetPixels) {

	    }

	    @Override
	    public void onPageScrollStateChanged(int state) {
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

    private View getListHeader() {
	return LayoutInflater.from(this).inflate(
		R.layout.movie_viewpager_header, null);
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.movie_search:
	    Intent intent = new Intent(this, MovieSearchActivity.class);
	    startActivity(intent);
	    break;
	}
    }
}
