package com.qiang.blog.adapter;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiang.blog.R;
import com.qiang.blog.activity.WebviewActivity;
import com.qiang.blog.discovery.entity.Newslist;
import com.qiang.blog.ui.ViewWidthWrapper;
import com.qiang.blog.utils.UIUtils;

public class DiscoveryListAdapter extends
	RecyclerView.Adapter<DiscoveryListAdapter.DiscoveryViewHolder> {

    private Context mContext;
    private List<Newslist> mList = new ArrayList<Newslist>();
    private DisplayImageOptions mOptions;
    private ImageLoader mImageLoader = ImageLoader.getInstance();

    public DiscoveryListAdapter(Context context) {
	mContext = context;
	mOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
		.bitmapConfig(Config.ARGB_8888).cacheOnDisk(true)
		.resetViewBeforeLoading(true)
		.showImageForEmptyUri(R.drawable.discovery_no_empty_picture)
		.showImageOnLoading(R.drawable.discovery_no_empty_picture)
		.showImageOnFail(R.drawable.discovery_no_empty_picture).build();
    }

    public DiscoveryListAdapter(Context context, List<Newslist> list) {
	this(context);
	mList = list;
    }

    public void setData(List<Newslist> list) {
	mList = list;
	notifyDataSetChanged();
    }

    @Override
    public DiscoveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	DiscoveryViewHolder holder = new DiscoveryViewHolder(LayoutInflater
		.from(mContext).inflate(R.layout.discovery_item, parent, false));
	return holder;
    }

    Handler mHandler = new Handler();

    @Override
    public void onBindViewHolder(final DiscoveryViewHolder holder, int position) {
	final Newslist article = mList.get(position);
	mImageLoader.displayImage(article.getPicUrl(), holder.dImage, mOptions);
	holder.dTitleLayout.removeAllViews();
	final TextView tv = new TextView(mContext);
	tv.setText(article.getTitle());
	tv.setTextSize(14f);
	tv.setTextColor(Color.parseColor("#ffffff"));
	tv.setGravity(Gravity.BOTTOM);
	tv.setLines(2);
	tv.setBackgroundColor(Color.parseColor("#000000"));
	int padding = UIUtils.dp2px(mContext, 3f);
	tv.setPadding(padding, 0, padding, 0);
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	tv.setLayoutParams(params);
	holder.dTitleLayout.addView(tv);
	// tv.setPivotX(0);
	// tv.setPivotY(0);
	// tv.measure(0, 0);
	// int width = tv.getMeasuredWidth();
	// ViewWidthWrapper widthWrapper = new ViewWidthWrapper(tv);
	// widthWrapper.setWidth(0);
	// ObjectAnimator anim2 = ObjectAnimator.ofInt(widthWrapper, "width", 1,
	// width).setDuration(700);
	// anim2.setInterpolator(new AccelerateInterpolator());
	// anim2.start();

	// holder.dTitle.setText(article.getTitle());
	// int widthSpec = View.MeasureSpec.makeMeasureSpec(0,
	// MeasureSpec.UNSPECIFIED);
	// holder.dTitle.measure(widthSpec, 0);
	// int width = holder.dTitle.getMeasuredWidthAndState()
	// - holder.dTitle.getPaddingLeft()
	// - holder.dTitle.getPaddingRight();
	ViewTreeObserver vto = tv.getViewTreeObserver();
	vto.addOnGlobalLayoutListener(new

	OnGlobalLayoutListener() {

	    @Override
	    public void onGlobalLayout() {
		tv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
		Runnable runnable = new Runnable() {

		    @Override
		    public void run() {
			tv.setPivotX(0);
			tv.setPivotY(0);
			int width = tv.getMeasuredWidth();
			ViewWidthWrapper widthWrapper = new ViewWidthWrapper(tv);
			widthWrapper.setWidth(0);
			ObjectAnimator anim2 = ObjectAnimator.ofInt(
				widthWrapper, "width", 1, width).setDuration(
				700);
			anim2.setInterpolator(new AccelerateInterpolator());
			anim2.start();
			anim2.addListener(new AnimatorListener() {

			    @Override
			    public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			    }

			    @Override
			    public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			    }

			    @Override
			    public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub

			    }

			    @Override
			    public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			    }
			});
			anim2.addUpdateListener(new AnimatorUpdateListener() {

			    @Override
			    public void onAnimationUpdate(
				    ValueAnimator animation) {
				// TODO Auto-generated method stub

			    }
			});
		    }
		};
		mHandler.post(runnable);
	    }

	});

	holder.dLayout.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(mContext, WebviewActivity.class);
		intent.putExtra("url", article.getUrl());
		mContext.startActivity(intent);
	    }
	});
    }

    @Override
    public int getItemCount() {
	return mList.size();
    }

    class DiscoveryViewHolder extends ViewHolder {

	public ImageView dImage;
	public TextView dTitle;
	public LinearLayout dLayout;
	public LinearLayout dTitleLayout;

	public DiscoveryViewHolder(View view) {
	    super(view);
	    dImage = (ImageView) view.findViewById(R.id.discovery_image);
	    dTitle = (TextView) view.findViewById(R.id.discovery_title);
	    dLayout = (LinearLayout) view
		    .findViewById(R.id.discovery_item_layout);
	    dTitleLayout = (LinearLayout) view
		    .findViewById(R.id.discovery_title_layout);
	}
    }

}
