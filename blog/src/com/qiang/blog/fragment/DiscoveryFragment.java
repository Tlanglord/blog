package com.qiang.blog.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.qiang.blog.R;
import com.qiang.blog.adapter.DiscoveryListAdapter;
import com.qiang.blog.discovery.entity.DiscoveryContentResponse;
import com.qiang.blog.discovery.entity.Newslist;
import com.qiang.blog.requestbase.BaseV4Fragment;
import com.qiang.blog.requestbase.RequestWrapper;
import com.qiang.blog.ui.DividerGridItemDecoration;
import com.qiang.blog.utils.UIUtils;

public class DiscoveryFragment extends BaseV4Fragment {

    private List<String> mDatas;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<Newslist> mList = new ArrayList<Newslist>();
    private DiscoveryContentResponse mDiscoveryResponse;
    private DiscoveryListAdapter mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.activity_discovery,
		container, false);
	mRecyclerView = (RecyclerView) rootView
		.findViewById(R.id.discovery_recyclerview);
	mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext,
		Color.parseColor("#E3E3E3"), UIUtils.dp2px(mContext, 3f)));
	return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
		StaggeredGridLayoutManager.VERTICAL));
	mListAdapter = new DiscoveryListAdapter(mContext);
	mRecyclerView.setAdapter(mListAdapter);
	requestData();
	super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
    }

    private void requestData() {
	RequestWrapper reqWrp = new RequestWrapper();
	reqWrp.setUrl("http://apis.baidu.com/txapi/keji/keji?num=10&page=1");
	reqWrp.setHeader("apikey", "94ccbb921e67bc436571409285e183d9");
	requestConnection(reqWrp);
    }

    @Override
    public void onResponse(Object tag, String response) {
	super.onResponse(tag, response);
	if (!TextUtils.isEmpty(response)) {
	    mDiscoveryResponse = JSON.parseObject(response,
		    DiscoveryContentResponse.class);

	    if (mDiscoveryResponse.getCode() == 200) {
		mList = mDiscoveryResponse.getNewslist();
		mListAdapter.setData(mList);
	    }
	}
    }

}
