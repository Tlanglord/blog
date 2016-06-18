package com.qiang.blog.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class SuperRecyclerView extends RecyclerView {
    

    public SuperRecyclerView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    public SuperRecyclerView(Context context) {
	super(context);
    }



    private OnItemLongClickListener onItemLongClickListener;

    public OnItemLongClickListener getOnItemLongClickListener() {
	return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(
	    OnItemLongClickListener onItemLongClickListener) {
	this.onItemLongClickListener = onItemLongClickListener;
    }

   

    public interface OnItemLongClickListener {
	void onItemLongClick(int position);
    }

}
