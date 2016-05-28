package com.qiang.blog.activity;

import java.util.List;

import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiang.blog.R;
import com.qiang.blog.movie.entity.CastInfo;
import com.qiang.blog.movie.entity.Works;
import com.qiang.blog.requestbase.BaseActivity;
import com.qiang.blog.requestbase.RequestWrapper;
import com.qiang.blog.utils.UIUtils;

public class MovieCastActivity extends BaseActivity {

    private TextView mMcName;
    private ImageView mMcImage;
    private TextView mMcBorn;
    private TextView mMcGender;
    private LinearLayout mMcWorksLayout;
    private String mCastId;
    private CastInfo mCastInfo;
    private DisplayImageOptions mOptions;
    private ImageLoader mImageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mCastId = getIntent().getStringExtra("castid");
	mOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
		.bitmapConfig(Config.ARGB_4444)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnLoading(R.drawable.ic_launcher).cacheInMemory(true)
		.cacheOnDisk(true).showImageOnFail(R.drawable.ic_launcher)
		.resetViewBeforeLoading(true).build();
	initView();
	requestCast(mCastId);
    }

    private void requestCast(String castId) {
	RequestWrapper requestWrapper = new RequestWrapper();
	requestWrapper.setUrl("https://api.douban.com/v2/movie/celebrity/"
		+ castId);
	requestWrapper.setTag(1);
	requestConnection(requestWrapper);
    }

    @Override
    protected int onContentView() {
	return R.layout.activity_movie_cast;
    }

    private void initView() {
	mMcName = (TextView) findViewById(R.id.mc_name);
	mMcGender = (TextView) findViewById(R.id.mc_gender);
	mMcBorn = (TextView) findViewById(R.id.mc_born_place);
	mMcWorksLayout = (LinearLayout) findViewById(R.id.mc_works_layout);
	mMcImage = (ImageView) findViewById(R.id.mc_image);
    }

    @Override
    public void onResponse(Object tag, String response) {
	super.onResponse(tag, response);
	if (!TextUtils.isEmpty(response)) {
	    mCastInfo = JSON.parseObject(response, CastInfo.class);
	    mMcName.setText(mCastInfo.getName());
	    mMcBorn.setText(mCastInfo.getBorn_place());
	    mMcGender.setText(mCastInfo.getGender());
	    mImageLoader.displayImage(mCastInfo.getAvatars().getLarge(),
		    mMcImage, mOptions);
	    buildWorks(mCastInfo.getWorks());
	}

    }
    
    private void buildWorks(List<Works> works) {
	mMcWorksLayout.removeAllViews();
	for (int i = 0; i < works.size(); i++) {
	    ImageView iv = new ImageView(this);
	    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		    UIUtils.dp2px(this, 80f), LayoutParams.MATCH_PARENT);
	    iv.setLayoutParams(params);
	    iv.setTag(i);
	   // iv.setOnClickListener(castListener);
	    iv.setScaleType(ScaleType.FIT_XY);
	    mImageLoader.displayImage(works.get(i).getSubject().getImages().getLarge(),iv,
		    mOptions);
	    mMcWorksLayout.addView(iv);
	}
    }

    private void initEvent() {

    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInitEvent() {

    }

}
