/**
 * 
 */
package com.qiang.blog.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiang.blog.R;
import com.qiang.blog.adapter.MemoTaskAdapter.MemoViewHolder;

/**
 * @author qiangqiang.dong
 * 
 */
public class MemoTaskAdapter extends RecyclerView.Adapter<MemoViewHolder> {

    private Context mContext;
    private List<String> mList = new ArrayList<String>();

    public MemoTaskAdapter(Context context) {
	mContext = context;
	for (int i = 0; i < 10; i++) {
	    mList.add(i + "");
	}
    }

    @Override
    public int getItemCount() {
	return mList.size();
    }

    @Override
    public void onBindViewHolder(final MemoViewHolder holder, int position) {
	holder.memoTaskCancle.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		// notifyDataSetChanged();
		mList.remove(holder.getAdapterPosition());
		notifyItemRemoved(holder.getAdapterPosition());

	    }
	});
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	MemoViewHolder holder = new MemoViewHolder(LayoutInflater
		.from(mContext).inflate(R.layout.memo_task_item, parent, false));
	return holder;
    }

    public class MemoViewHolder extends ViewHolder {

	public TextView memoTaskCancle;

	public MemoViewHolder(View view) {
	    super(view);
	    memoTaskCancle = (TextView) view
		    .findViewById(R.id.memo_task_cancle);
	}

    }

}
