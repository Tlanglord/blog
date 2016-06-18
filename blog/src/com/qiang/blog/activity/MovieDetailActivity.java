package com.qiang.blog.activity;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiang.blog.R;
import com.qiang.blog.movie.entity.Casts;
import com.qiang.blog.movie.entity.MovieDetailInfo;
import com.qiang.blog.requestbase.BaseActivity;
import com.qiang.blog.utils.UIUtils;

public class MovieDetailActivity extends BaseActivity implements
	OnClickListener {
    private ImageView mMdImage;
    private RelativeLayout mMdPlay;
    private TextView mMdName;
    private TextView mMdRating;
    private TextView mMdDirector;
    private TextView mMdSummmay;
    private TextView mMdDoubanStation;
    private TextView mMdScannerMV;
    private MovieDetailInfo mMdDetailInfo;
    private String mMdMovieId;
    private DisplayImageOptions mOptions;
    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private LinearLayout mMdCastsLayout;
    private List<Casts> mCasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_movie_detail);
	getData();
	initView();
	initEvent();
	initData();
    }

    private void getData() {
	mMdMovieId = getIntent().getStringExtra("movieid");
	mOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
		.bitmapConfig(Config.ARGB_4444)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnLoading(R.drawable.ic_launcher).cacheInMemory(true)
		.cacheOnDisk(true).showImageOnFail(R.drawable.ic_launcher)
		.resetViewBeforeLoading(true).build();
    }

    private void initView() {
	mMdImage = (ImageView) findViewById(R.id.md_image);
	mMdPlay = (RelativeLayout) findViewById(R.id.md_play);
	mMdDirector = (TextView) findViewById(R.id.md_director);
	mMdRating = (TextView) findViewById(R.id.md_rating);
	mMdName = (TextView) findViewById(R.id.md_name);
	mMdSummmay = (TextView) findViewById(R.id.md_summmay);
	mMdDoubanStation = (TextView) findViewById(R.id.md_douban_station);
	mMdScannerMV = (TextView) findViewById(R.id.md_movieinfo);
	mMdCastsLayout = (LinearLayout) findViewById(R.id.md_casts_layout);
    }

    private void initEvent() {
	mMdPlay.setOnClickListener(this);
	mMdSummmay.setOnClickListener(this);
	mMdDoubanStation.setOnClickListener(this);
	mMdScannerMV.setOnClickListener(this);
	setUIUnable();
    }

    private void setUIUnable() {
	mMdSummmay.setEnabled(false);
	mMdDoubanStation.setEnabled(false);
	mMdScannerMV.setEnabled(false);
    }

    private void setUIEnable() {
	mMdSummmay.setEnabled(true);
	mMdDoubanStation.setEnabled(true);
	mMdScannerMV.setEnabled(true);
    }

    private void buildCasts(List<Casts> casts) {
	mMdCastsLayout.removeAllViews();
	for (int i = 0; i < casts.size(); i++) {
	    ImageView iv = new ImageView(this);
	    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		    UIUtils.dp2px(this, 80f), LayoutParams.MATCH_PARENT);
	    iv.setLayoutParams(params);
	    iv.setTag(i);
	    iv.setOnClickListener(castListener);
	    iv.setScaleType(ScaleType.FIT_XY);
	    mImageLoader.displayImage(casts.get(i).getAvatars().getLarge(), iv,
		    mOptions);
	    mMdCastsLayout.addView(iv);
	}
    }

    OnClickListener castListener = new OnClickListener() {

	@Override
	public void onClick(View v) {
	    int pos = (int) v.getTag();
	    enterMovieCast(pos);
	}
    };

    private void enterMovieCast(int pos) {
	Intent intent = new Intent(this, MovieCastActivity.class);
	intent.putExtra("castid", mCasts.get(pos).getId());
	startActivity(intent);
    }

    private void initData() {
	testVolly(mMdMovieId);
    }

    public <T> boolean isValidList(List<T> list) {
	return list != null && list.size() > 0;
    }

    private void testVolly(String movieId) {

	RequestQueue queue = Volley.newRequestQueue(this);
	StringRequest stringRequest = new StringRequest(
		"https://api.douban.com/v2/movie/subject/" + movieId,
		new Response.Listener<String>() {
		    @Override
		    public void onResponse(String response) {
			Log.d("TAG", response);
			mMdDetailInfo = JSON.parseObject(response,
				MovieDetailInfo.class);

			mMdName.setText("片名：" + mMdDetailInfo.getTitle());
			mMdRating.setText("评分："
				+ mMdDetailInfo.getRating().getAverage() + "");

			if (isValidList(mMdDetailInfo.getDirectors())) {
			    mMdDirector.setText("导演："
				    + mMdDetailInfo.getDirectors().get(0)
					    .getName());
			} else {
			    mMdDirector.setText("导演：" + "未知");
			}

			mMdSummmay.setText(mMdDetailInfo.getSummary());
			mImageLoader.displayImage(mMdDetailInfo.getImages()
				.getLarge(), mMdImage, mOptions);
			mCasts = mMdDetailInfo.getCasts();
			if (mCasts != null && mCasts.size() > 0) {
			    buildCasts(mCasts);
			}
			setUIEnable();
		    }
		}, new Response.ErrorListener() {
		    @Override
		    public void onErrorResponse(VolleyError error) {
			Log.e("TAG", error.getMessage(), error);
		    }
		});

	queue.add(stringRequest);
    }

    PopupWindow popupWindow = null;
    TextView popContent = null;

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	case R.id.md_play:
	    enterMoviePlay();
	    break;
	case R.id.md_summmay:
	    View view = LayoutInflater.from(this).inflate(
		    R.layout.movie_pop_item, null);
	    popContent = (TextView) view.findViewById(R.id.pop_content);
	    popContent.setText(mMdDetailInfo.getSummary());
	    popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
		    LayoutParams.WRAP_CONTENT);
	    popupWindow.setOutsideTouchable(true);
	    popupWindow.setFocusable(true);
	    popupWindow.setBackgroundDrawable(new ColorDrawable(Color
		    .parseColor("#00000000")));
	    mHandler.sendEmptyMessage(1);
	    mHandler.sendEmptyMessageDelayed(2, 500);
	    break;
	case R.id.md_douban_station:
	    enterDoubanStation();
	    break;
	case R.id.md_movieinfo:
	    enterScannerMV();
	    break;
	}
    }

    Handler mHandler = new Handler() {
	public void handleMessage(android.os.Message msg) {
	    switch (msg.what) {
	    case 1:
		int[] location = new int[2];
		mMdSummmay.getLocationOnScreen(location);
		popContent.setMaxLines(3);
		// popupWindow.showAtLocation(mMdSummmay, Gravity.TOP, 0,
		// location[1]);
		popupWindow.showAsDropDown(mMdSummmay, 0,
			-mMdSummmay.getMeasuredHeight());
		break;
	    case 2:
		popContent.setMovementMethod(ScrollingMovementMethod
			.getInstance());
		popContent.setMaxLines(10);
		break;
	    default:
		break;
	    }
	};
    };

    private void enterMoviePlay() {
	if (mMdDetailInfo != null
		&& !TextUtils.isEmpty(mMdDetailInfo.getMobile_url())) {
	    Intent intent = new Intent(this, WebviewActivity.class);
	    intent.putExtra("url", mMdDetailInfo.getMobile_url());
	    intent.putExtra("needHead", false);
	    startActivity(intent);
	}
    }

    private void enterDoubanStation() {
	if (mMdDetailInfo != null
		&& !TextUtils.isEmpty(mMdDetailInfo.getDouban_site())) {
	    Intent intent = new Intent(this, WebviewActivity.class);
	    intent.putExtra("url", mMdDetailInfo.getDouban_site());
	    intent.putExtra("needHead", false);
	    startActivity(intent);
	}
    }

    private void enterScannerMV() {
	if (mMdDetailInfo != null
		&& !TextUtils.isEmpty(mMdDetailInfo.getSchedule_url())) {
	    Intent intent = new Intent(this, WebviewActivity.class);
	    intent.putExtra("url", mMdDetailInfo.getSchedule_url());
	    intent.putExtra("needHead", false);
	    startActivity(intent);
	}
    }

    @Override
    protected int onContentView() {
	return R.layout.activity_movie_detail;
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInitEvent() {

    }

    @Override
    public void onResponse(Object tag, String response) {
	// TODO Auto-generated method stub
	super.onResponse(tag, response);
    }
}
