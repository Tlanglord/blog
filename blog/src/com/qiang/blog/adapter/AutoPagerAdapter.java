package com.qiang.blog.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiang.blog.R;

public class AutoPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<View> mViewList;
    private List<String> mImageList;
    private DisplayImageOptions mOptions;
    private Object String;

    public AutoPagerAdapter(Context context, List<View> viewList,
	    List<String> imageList) {
	this.mContext = context;
	this.mViewList = viewList;
	this.mImageList = imageList;
	mOptions = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnLoading(R.drawable.ic_launcher).cacheInMemory(true)
		.cacheOnDisc(true).build();
    }

    @Override
    public int getCount() {
	return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
	return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

	position %= mViewList.size();

	if (position < 0) {
	    position += mViewList.size();
	}

	ImageView view = (ImageView) mViewList.get(position);
	ViewParent vp = view.getParent();
	if (vp != null) {
	    ViewGroup parent = (ViewGroup) vp;
	    parent.removeView(view);
	}

	if (view instanceof ImageView) {
	    if (mImageList != null) {
		String imageUrl = "";
		if (mImageList.size() > position) {
		    imageUrl = mImageList.get(position);
		}
		ImageLoader.getInstance().displayImage(imageUrl,
			((ImageView) mViewList.get(position)), mOptions);
	    }
	}
	container.addView(view);
	return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
	// container.removeView(mViewList.get(position));
    }

    // @Override
    // public float getPageWidth(int position) {
    // return 0.85f;
    // }

    @Override
    public CharSequence getPageTitle(int position) {
	return super.getPageTitle(position);
    }
}
