package com.qiang.blog.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiang.blog.R;
import com.qiang.blog.movie.entity.Casts;
import com.qiang.blog.movie.entity.Subjects;

public class MovieSearchAdapter extends BaseAdapter {

    private JSONArray mJsonArray;
    private Context mContext;
    private List<Subjects> mSubjects;
    private int mStarPos = 0;
    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions;

    public MovieSearchAdapter(Context context, List<Subjects> subjects) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder = null;
	if (convertView == null) {
	    holder = new ViewHolder();
	    convertView = LayoutInflater.from(mContext).inflate(
		    R.layout.movie_search_item, null);
	    bindViewHolder(holder, convertView);
	    convertView.setTag(holder);
	} else {
	    holder = (ViewHolder) convertView.getTag();
	}

	Subjects subject = mSubjects.get(position);
	setMovieViewData(holder, subject);
	return convertView;
    }

    private void setMovieViewData(ViewHolder holder, Subjects subject) {
	mImageLoader.displayImage(subject.getImages().getSmall(),
		holder.mMvImage, mOptions);

	if (subject.getDirectors() != null && subject.getDirectors().size() > 0) {
	    holder.mMvDirector.setText("导演："
		    + subject.getDirectors().get(0).getName());
	}else{
	    holder.mMvDirector.setText("导演："
		    + "未知");
	}
	holder.mMvRating.setText("评分：" + subject.getRating().getAverage() + "");
	holder.mMvYear.setText("上映时间：" + subject.getYear());
	holder.mMvName.setText(subject.getOriginal_title());
	setMovieCast(subject.getCasts(), holder);
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
	holder.mMvName = (TextView) convertView.findViewById(R.id.movie_name);
    }

    static class ViewHolder {

	public ImageView mMvImage;
	public TextView mMvDirector;
	public TextView mMvName;
	public LinearLayout mMvCasts;
	public TextView mMvRating;
	public TextView mMvYear;

	public void reset() {
	    mMvImage.setVisibility(View.GONE);
	    mMvDirector.setVisibility(View.GONE);
	    mMvCasts.setVisibility(View.GONE);
	    mMvRating.setVisibility(View.GONE);
	    mMvYear.setVisibility(View.GONE);
	    mMvName.setVisibility(View.GONE);
	}
    }
}
