package com.qiang.blog.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiang.blog.R;
import com.qiang.blog.activity.MemoActivity;
import com.qiang.blog.activity.MovieActivity;

public class OwnerFragment extends Fragment implements OnClickListener {

	private TextView mOwerSets;
	private TextView mOwerChat;
	private TextView mOwnerMemo;
	private TextView mOwnerMovie;
	private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity().getApplicationContext();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = initView(inflater, container);
		return rootView;
	}

	private View initView(LayoutInflater inflater, ViewGroup container) {
		View rootView = inflater.inflate(R.layout.activity_owner, container,
				false);
		mOwerSets = (TextView) rootView.findViewById(R.id.ower_set);
		mOwnerMemo = (TextView) rootView.findViewById(R.id.owner_memo);
		mOwerChat = (TextView) rootView.findViewById(R.id.ower_chat);
		mOwnerMovie = (TextView) rootView.findViewById(R.id.ower_movie);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initEvent();
		super.onActivityCreated(savedInstanceState);
	}

	private void initEvent() {
		mOwerSets.setOnClickListener(this);
		mOwerChat.setOnClickListener(this);
		mOwnerMemo.setOnClickListener(this);
		mOwnerMovie.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ower_chat:
			break;
		case R.id.owner_memo:
			enterMemo();
			break;
		case R.id.ower_movie:
			enterMovie();
			break;
		default:
			break;
		}
	}

	private void enterMemo() {
		Intent intent = new Intent(mContext, MemoActivity.class);
		startActivity(intent);
	}
	
	private void enterMovie() {
		Intent intent = new Intent(mContext, MovieActivity.class);
		startActivity(intent);
	}
}
