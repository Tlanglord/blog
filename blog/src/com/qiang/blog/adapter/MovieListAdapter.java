package com.qiang.blog.adapter;

import java.util.List;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiang.blog.R;
import com.qiang.blog.movie.entity.Casts;
import com.qiang.blog.movie.entity.Subjects;
import com.qiang.blog.utils.UIUtils;

public class MovieListAdapter extends BaseAdapter {

    private JSONArray mJsonArray;
    private Context mContext;
    private List<Subjects> mSubjects;
    private int mStarPos = 0;
    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions;

    public MovieListAdapter(Context context, List<Subjects> subjects) {
	mContext = context;
	mSubjects = subjects;

	mOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
		.bitmapConfig(Config.ARGB_4444).cacheOnDisk(true)
		.resetViewBeforeLoading(true)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnLoading(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher).build();

    }

    @Override
    public int getCount() {
	return mSubjects.size();
    }

    @Override
    public Object getItem(int position) {
	return mSubjects.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    private boolean isDelete = false;
    private int deletePos = 0;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
	ViewHolder holder = null;
	if (convertView == null) {
	    holder = new ViewHolder();
	    convertView = LayoutInflater.from(mContext).inflate(
		    R.layout.movie_item, null);
	    bindViewHolder(holder, convertView);
	    convertView.setTag(holder);
	} else {
	    holder = (ViewHolder) convertView.getTag();
	}

	Subjects subject = mSubjects.get(position);
	
	setMovieViewData(holder, subject);
	holder.mMvLayout.setOnLongClickListener(new OnLongClickListener() {

	    @Override
	    public boolean onLongClick(final View v) {
		// Animation animation = new TranslateAnimation(
		// TranslateAnimation.RELATIVE_TO_SELF, 1,
		// TranslateAnimation.RELATIVE_TO_SELF, 1,
		// TranslateAnimation.RELATIVE_TO_SELF, 1,
		// TranslateAnimation.RELATIVE_TO_SELF, 0);
		deletePos = position;
		// v.getTranslationX()
		ObjectAnimator animator = ObjectAnimator.ofFloat(v,
			"translationX", v.getMeasuredWidth() / 2,
			-(float) v.getMeasuredWidth());

		animator.setDuration(800);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();
		animator.addListener(new AnimatorListener() {

		    @Override
		    public void onAnimationStart(Animator animation) {

		    }

		    @Override
		    public void onAnimationRepeat(Animator animation) {

		    }

		    @Override
		    public void onAnimationEnd(Animator animation) {
			mSubjects.remove(position);
			isDelete = true;
			notifyDataSetChanged();
			v.setTranslationX(0);
		    }

		    @Override
		    public void onAnimationCancel(Animator animation) {

		    }
		});

		// animation.setDuration(300);
		// v.
		// animation.setAnimationListener(new AnimationListener() {
		//
		// @Override
		// public void onAnimationStart(Animation animation) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onAnimationRepeat(Animation animation) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onAnimationEnd(Animation animation) {
		// mSubjects.remove(position);
		// notifyDataSetChanged();
		// // notifyDataSetInvalidated();
		// }
		// });
		return true;
	    }
	});
	
	if (isDelete && position == ((ListView)parent).getLastVisiblePosition()) {
	    isDelete = false;
	}
	return convertView;
    }

    private void setMovieViewData(ViewHolder holder, Subjects subject) {

	mImageLoader.displayImage(subject.getImages().getSmall(),
		holder.mMvImage, mOptions);

	if (subject.getDirectors() != null && subject.getDirectors().size() > 0) {
	    holder.mMvDirector.setText("导演："
		    + subject.getDirectors().get(0).getName());
	} else {
	    holder.mMvDirector.setText("导演：" + "未知");
	}
	holder.mMvRating.setText("评分：" + subject.getRating().getAverage() + "");
	holder.mMvYear.setText("上映时间：" + subject.getYear());
	setMovieCast(subject.getCasts(), holder);

	if (!isDelete) {
	    Animation animation = new TranslateAnimation(
		    TranslateAnimation.RELATIVE_TO_SELF, 1,
		    TranslateAnimation.RELATIVE_TO_SELF, 1,
		    TranslateAnimation.RELATIVE_TO_SELF, 0,
		    TranslateAnimation.RELATIVE_TO_SELF, 1);
	    animation.setDuration(300);
	    holder.mMvLayout.startAnimation(animation);
	}

    }

    private void setMovieCast(List<Casts> casts, ViewHolder holder) {
	addCast(casts, holder);
    }

    private void addCast(List<Casts> casts, ViewHolder holder) {
	int min = Math.min(3, casts.size());
	holder.mMvCasts.removeAllViews();
	for (int i = 0; i < min; i++) {
	    TextView cast = new TextView(mContext);
	    cast.setText(casts.get(i).getName());
	    cast.setSingleLine();
	    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    params.setMargins(0, 0, 5, 0);
	    holder.mMvCasts.addView(cast, params);
	}
    }

    private void bindViewHolder(ViewHolder holder, View convertView) {
	holder.mMvCasts = (LinearLayout) convertView
		.findViewById(R.id.movie_cast);
	holder.mMvDirector = (TextView) convertView
		.findViewById(R.id.movie_director);
	holder.mMvImage = (ImageView) convertView
		.findViewById(R.id.movie_image);
	holder.mMvRating = (TextView) convertView
		.findViewById(R.id.movie_rating);
	holder.mMvYear = (TextView) convertView.findViewById(R.id.movie_year);
	holder.mMvLayout = (LinearLayout) convertView
		.findViewById(R.id.movie_item_layout);
    }

    static class ViewHolder {

	public ImageView mMvImage;
	public TextView mMvDirector;
	public LinearLayout mMvCasts;
	public TextView mMvRating;
	public TextView mMvYear;
	public LinearLayout mMvLayout;

	public void reset() {
	    mMvImage.setVisibility(View.GONE);
	    mMvDirector.setVisibility(View.GONE);
	    mMvCasts.setVisibility(View.GONE);
	    mMvRating.setVisibility(View.GONE);
	    mMvYear.setVisibility(View.GONE);
	}
    }
}
