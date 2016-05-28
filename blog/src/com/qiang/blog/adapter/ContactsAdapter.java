package com.qiang.blog.adapter;

import io.rong.imlib.model.UserInfo;

import java.util.List;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiang.blog.R;

/**
 * 
 * @author qiangqiang.dong
 * 
 */
public class ContactsAdapter extends BaseAdapter {

	/**
	 * 定义字母表的排序规则
	 */
	private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private LayoutInflater mLayoutInflater;
	private List<UserInfo> mList;
	private ImageLoader mImageLoader = ImageLoader.getInstance();
	private DisplayImageOptions mOptions;
	private String oldHc = "";

	public ContactsAdapter(Context context, List<UserInfo> list) {
		mList = list;
		mLayoutInflater = LayoutInflater.from(context);
		mOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnLoading(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisc(true).build();
	}

	@Override
	public int getCount() {
		if (mList == null)
			return 0;
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		if (mList == null || mList.size() >= position)
			return null;
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null || convertView.getTag() == null) {

			convertView = mLayoutInflater.inflate(R.layout.contacts_item,
					parent, false);
			viewHolder = new ViewHolder();
			bindView(convertView, viewHolder);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		UserInfo user = mList.get(position);
		mImageLoader.displayImage(user.getPortraitUri().toString(),
				viewHolder.contacts_image, mOptions);
		viewHolder.contacts_name.setText(user.getName());

		String hc = getHanyuHeadChar(user.getName());
		if (!hc.equals(oldHc)) {
			viewHolder.contacts_sort.setVisibility(View.VISIBLE);
			viewHolder.contacts_sort.setText(hc);
			// if (position == 0) {
			// viewHolder.contacts_line.setVisibility(View.VISIBLE);
			// } else {
			// viewHolder.contacts_line.setVisibility(View.GONE);
			// }
			viewHolder.contacts_line.setVisibility(View.VISIBLE);
			oldHc = hc;
		} else {
			viewHolder.contacts_sort.setVisibility(View.GONE);
			viewHolder.contacts_line.setVisibility(View.VISIBLE);
		}

		return convertView;
	}

	private void bindView(View convertView, ViewHolder viewHolder) {
		viewHolder.contacts_image = (ImageView) convertView
				.findViewById(R.id.contacts_image);
		viewHolder.contacts_name = (TextView) convertView
				.findViewById(R.id.contacts_name);
		viewHolder.contacts_sort = (TextView) convertView
				.findViewById(R.id.contacts_sort);
		viewHolder.contacts_line = (View) convertView
				.findViewById(R.id.contacts_line);
	}

	private String getHanyuHeadChar(String str) {
		String hc = "";
		if (str.length() > 0) {
			char word = str.charAt(0);
			String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyin != null && pinyin.length > 0) {
				hc = pinyin[0];
			} else {
				hc = String.valueOf(word);
			}
		}
		return hc;
	}

	private String getAlpha(String str) {
		if (str == null) {
			return "#";
		}

		if (str.trim().length() == 0) {
			return "#";
		}

		char c = str.trim().substring(0, 1).charAt(0);

		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else {
			return "#";
		}
	}

	private String getSortKey(String sortKeyString) {
		String key = sortKeyString.substring(0, 1).toUpperCase();
		if (key.matches("[A-Z]")) {
			return key;
		}
		return "#";
	}

	static class ViewHolder {
		ImageView contacts_image;
		TextView contacts_name;
		TextView contacts_sort;
		View contacts_line;
	}
}
