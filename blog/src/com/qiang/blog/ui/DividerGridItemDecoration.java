package com.qiang.blog.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
    private Drawable mDivider;
    private Paint mPaint;
    private int mDividerHeight;

    public DividerGridItemDecoration(Context context, int color,
	    int dividerHeight) {
	final TypedArray a = context.obtainStyledAttributes(ATTRS);
	mDivider = a.getDrawable(0);
	mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	mPaint.setColor(color);
	mPaint.setStyle(Paint.Style.FILL);
	mDividerHeight = dividerHeight;
	a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {

	drawHorizontal(c, parent);
	drawVertical(c, parent);

    }

    private int getSpanCount(RecyclerView parent) {
	// 列数
	int spanCount = -1;
	LayoutManager layoutManager = parent.getLayoutManager();
	if (layoutManager instanceof GridLayoutManager) {

	    spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
	} else if (layoutManager instanceof StaggeredGridLayoutManager) {
	    spanCount = ((StaggeredGridLayoutManager) layoutManager)
		    .getSpanCount();
	}
	return spanCount;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
	int childCount = parent.getChildCount();
	final int left = parent.getPaddingLeft();
	final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
	for (int i = 0; i < childCount; i++) {
	    final View child = parent.getChildAt(i);
	    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
		    .getLayoutParams();
	    // final int left = child.getLeft() - params.leftMargin;
	    // final int right = child.getRight() + params.rightMargin
	    // + mDivider.getIntrinsicWidth();
	    final int top = child.getBottom() + params.bottomMargin;
	    final int bottom = top + mDividerHeight;
	    mDivider.setBounds(left, top, right, bottom);
	    mDivider.draw(c);
	    c.drawRect(left, top, right, bottom, mPaint);
	}
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
	final int childCount = parent.getChildCount();
	final int top = parent.getPaddingTop();
	final int bottom = parent.getMeasuredHeight()
		- parent.getPaddingBottom();
	for (int i = 0; i < childCount; i++) {
	    final View child = parent.getChildAt(i);

	    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
		    .getLayoutParams();
	    // final int top = child.getTop() - params.topMargin;
	    // final int bottom = child.getBottom() + params.bottomMargin;
	    final int left = child.getRight() + params.rightMargin;
	    final int right = left + mDividerHeight;

	    mDivider.setBounds(left, top, right, bottom);
	    mDivider.draw(c);
	    c.drawRect(left, top, right, bottom, mPaint);
	}
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
	    int childCount) {
	LayoutManager layoutManager = parent.getLayoutManager();
	if (layoutManager instanceof GridLayoutManager) {
	    if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
	    {
		return true;
	    }
	} else if (layoutManager instanceof StaggeredGridLayoutManager) {
	    int orientation = ((StaggeredGridLayoutManager) layoutManager)
		    .getOrientation();
	    if (orientation == StaggeredGridLayoutManager.VERTICAL) {
		if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
		{
		    return true;
		}
	    } else {
		childCount = childCount - childCount % spanCount;
		if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
		    return true;
	    }
	}
	return false;
    }

    private boolean isLastColum1(RecyclerView parent, int pos, int spanCount,
	    int childCount) {
	if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
	{
	    return true;
	}
	return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
	    int childCount) {
	LayoutManager layoutManager = parent.getLayoutManager();
	if (layoutManager instanceof GridLayoutManager) {
	    childCount = childCount - childCount % spanCount;
	    if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
		return true;
	} else if (layoutManager instanceof StaggeredGridLayoutManager) {
	    int orientation = ((StaggeredGridLayoutManager) layoutManager)
		    .getOrientation();
	    // StaggeredGridLayoutManager 且纵向滚动
	    if (orientation == StaggeredGridLayoutManager.VERTICAL) {
		childCount = childCount - childCount % spanCount;
		// 如果是最后一行，则不需要绘制底部
		if (pos >= childCount)
		    return true;
	    } else
	    // StaggeredGridLayoutManager 且横向滚动
	    {
		// 如果是最后一行，则不需要绘制底部
		if ((pos + 1) % spanCount == 0) {
		    return true;
		}
	    }
	}
	return false;
    }

    private boolean isFirstCount(int position) {
	return position == 0;
    }

    private boolean isSecCount(int position) {
	return position == 1;
    }

    private boolean isLastCount(int position, int count) {
	return position == (count - 1);
    }

    private boolean isLastSecCount(int position, int count) {
	return position == (count - 2);
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
	    RecyclerView parent) {
	int spanCount = getSpanCount(parent);
	int childCount = parent.getAdapter().getItemCount();
	if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
	{
	    // outRect.set(0, 0, mDividerHeight, 0);
	    // outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
	} else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
	{
	    // outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
	    // outRect.set(0, 0, 0, mDividerHeight);
	} else {
	    // outRect.set(0, 0, mDivider.getIntrinsicWidth(),
	    // mDivider.getIntrinsicHeight());
	    // outRect.set(0, 0, mDividerHeight, mDividerHeight);
	    // outRect.set(0, 0, mDividerHeight, mDividerHeight);
	}
	// outRect.set(0, 0, mDividerHeight, mDividerHeight);
	// outRect.set(0, 0, 0, mDividerHeight);
	// if (isLastCount(itemPosition, childCount)) {
	// outRect.set(0, 0, mDividerHeight, 0);
	// } else if (isLastSecCount(itemPosition, childCount)) {
	// outRect.set(0, 0, mDividerHeight, 0);
	// } else {
	if (isFirstCount(itemPosition)) {
	    outRect.set(mDividerHeight, mDividerHeight, mDividerHeight,
		    mDividerHeight);
	} else if (isSecCount(itemPosition)) {
	    outRect.set(0, mDividerHeight, 0, mDividerHeight);
	} else if (isLastColum1(parent, itemPosition, spanCount, childCount)) {
	    outRect.set(0, 0, 0, mDividerHeight);
	} else {
	    outRect.set(mDividerHeight, 0, mDividerHeight, mDividerHeight);
	}
	// }
    }
}
