package com.qiang.blog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiang.blog.R;

public class BlogListAdapter extends BaseAdapter {

	private JSONArray mJsonArray;
	private Context mContext;

	public BlogListAdapter(Context context, JSONArray jsonArray) {
		this.mJsonArray = jsonArray;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mJsonArray.size();
	}

	@Override
	public Object getItem(int position) {
		return mJsonArray.get(position);
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
					R.layout.activity_home_item, null);
			bindViewHolder(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		JSONObject jsonObject = mJsonArray.getJSONObject(position);
		setViewData(holder, jsonObject);
		return convertView;
	}

	private void setViewData(ViewHolder holder, JSONObject object) {
		holder.home_news_title.setText(object.getJSONObject("title").getString("rendered"));
		holder.home_news_date.setText(object.getString("date"));
	}

	private void bindViewHolder(ViewHolder holder, View convertView) {
		holder.home_news_comment_count = (TextView) convertView
				.findViewById(R.id.home_news_comment_count);
		holder.home_news_title = (TextView) convertView
				.findViewById(R.id.home_news_title);
		holder.home_news_sources = (TextView) convertView
				.findViewById(R.id.home_news_sources);
		holder.home_news_is_photo = (TextView) convertView
				.findViewById(R.id.home_news_is_photo);
		holder.home_news_date = (TextView) convertView
				.findViewById(R.id.home_news_date);
		holder.home_news_image = (ImageView) convertView
				.findViewById(R.id.home_news_image);
	}

	static class ViewHolder {
		public TextView home_news_date;
		public TextView home_news_title;
		public ImageView home_news_image;
		public TextView home_news_sources;
		public TextView home_news_is_photo;
		public TextView home_news_comment_count;

		public void reset() {
			home_news_image.setVisibility(View.GONE);
			home_news_title.setVisibility(View.GONE);
			home_news_sources.setVisibility(View.GONE);
			home_news_is_photo.setVisibility(View.GONE);
			home_news_comment_count.setVisibility(View.GONE);
		}
	}
}
