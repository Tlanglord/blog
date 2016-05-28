package com.qiang.blog.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qiang.blog.R;

/**
 * 
 * @author qiangqiang.dong
 * 
 */
public class LifeSphereAdapter extends BaseAdapter {

	private List<String> mList;
	private Context mContext;
	
	public LifeSphereAdapter(List<String> mList, Context mContext) {
		super();
		this.mList = mList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lifesphere_item, null);
		}
		
		return convertView;
	}

}
