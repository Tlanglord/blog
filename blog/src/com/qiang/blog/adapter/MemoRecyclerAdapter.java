/**
 * 
 */
package com.qiang.blog.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiang.blog.R;
import com.qiang.blog.activity.MemoActivity;
import com.qiang.blog.activity.MemoAddActivity;
import com.qiang.blog.adapter.MemoRecyclerAdapter.MemoViewHolder;
import com.qiang.blog.entity.MemoEntity;
import com.qiang.blog.ui.SuperRecyclerView;

/**
 * @author qiangqiang.dong
 * 
 */
public class MemoRecyclerAdapter extends RecyclerView.Adapter<MemoViewHolder> {

    private MemoActivity mContext;
    private List<Map<String, String>> mList = new ArrayList<Map<String, String>>();
    private SuperRecyclerView mRecyclerView;

    // private OnItemLongClickListener onItemLongClickListener;
    //
    // public OnItemLongClickListener getOnItemLongClickListener() {
    // return onItemLongClickListener;
    // }
    //
    // public void setOnItemLongClickListener(
    // OnItemLongClickListener onItemLongClickListener) {
    // this.onItemLongClickListener = onItemLongClickListener;
    // }

    public MemoRecyclerAdapter(Context context, List<Map<String, String>> list,
	    SuperRecyclerView recyclerView) {
	mContext = (MemoActivity) context;
	mList = list;
	mRecyclerView = recyclerView;

    }

    @Override
    public int getItemCount() {
	return mList.size();
    }

    @Override
    public void onBindViewHolder(final MemoViewHolder holder, final int position) {
	holder.mTitle.setText(mList.get(position).get("memo_content"));
	holder.mItemLayout.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(mContext, MemoAddActivity.class);
		Map<String, String> map = mList.get(position);
		MemoEntity memo = new MemoEntity();
		// "memo_type"
		// "memo_overdue"
		// "memo_content"
		// "memo_createtime"
		// "memo_date"
		memo.setMemo_content(map.get("memo_content"));
		memo.setMemo_type(Integer.parseInt(map.get("memo_type")));
		memo.setMemo_overdue(Boolean.getBoolean(map.get("memo_overdue")));
		memo.setMemo_createtime(map.get("memo_createtime"));
		memo.setMemo_date(map.get("memo_date"));
		memo.setMemo_action(1);
		memo.setMemo_id(Integer.valueOf(map.get("_id")));
		intent.putExtra("memo", memo);
		mContext.startActivityForResult(intent, 2);
	    }
	});

	holder.mItemLayout.setOnLongClickListener(new OnLongClickListener() {

	    @Override
	    public boolean onLongClick(View v) {

		if (mRecyclerView.getOnItemLongClickListener() != null) {
		    mRecyclerView.getOnItemLongClickListener().onItemLongClick(
			    holder.getAdapterPosition());
		}

		mList.remove(holder.getAdapterPosition());
		notifyItemRemoved(holder.getAdapterPosition());
		// .onItemLongClick(holder
		// .getAdapterPosition());
		return true;
	    }
	});
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	MemoViewHolder holder = new MemoViewHolder(LayoutInflater
		.from(mContext).inflate(R.layout.memo_item, parent, false));
	return holder;
    }

    public class MemoViewHolder extends ViewHolder {
	public ImageView mImg;
	public TextView mTitle;
	public LinearLayout mItemLayout;

	public MemoViewHolder(View view) {
	    super(view);
	    mImg = (ImageView) view.findViewById(R.id.memo_image);
	    mTitle = (TextView) view.findViewById(R.id.memo_title);
	    mItemLayout = (LinearLayout) view
		    .findViewById(R.id.memo_item_layout);
	}

    }

}
