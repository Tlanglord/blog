package com.qiang.blog.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qiang.blog.R;
import com.qiang.blog.activity.BlogScannerActivity;
import com.qiang.blog.adapter.BlogListAdapter;
import com.qiang.blog.constant.BlogApi;
import com.qiang.blog.entity.BlogData;

public class HomeFragment extends Fragment implements OnClickListener {
	private ListView mBlogListView;
	private BlogData mBlogData;
	private TextView mTest;
	private JSONArray mBlogJsonArray;
	private BlogListAdapter mBlogListAdapter;
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
		View rootView = inflater.inflate(R.layout.activity_home, container,
				false);
		mBlogListView = (ListView) rootView.findViewById(R.id.home_listview);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		testVolly();
		initEvent();
	}

	private void initEvent() {
		mBlogListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(mContext, BlogScannerActivity.class);
				intent.putExtra("title", mBlogJsonArray.getJSONObject(position)
						.getJSONObject("title").getString("rendered"));
				intent.putExtra(
						"content",
						mBlogJsonArray.getJSONObject(position)
								.getString("link"));
				//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.test:
			testVolly();
			break;
		default:
			break;
		}

	}

	private void testVolly() {

		RequestQueue queue = Volley.newRequestQueue(mContext);
		StringRequest stringRequest = new StringRequest(
				BlogApi.getBlogContent.getAction(),
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d("TAG", response);
						mBlogJsonArray = JSON.parseArray(response);

						if (mBlogJsonArray == null) {
							Toast.makeText(mContext, "获取数据失败",
									Toast.LENGTH_SHORT).show();
							return;
						}

						if (mBlogListAdapter == null) {
							mBlogListAdapter = new BlogListAdapter(mContext,
									mBlogJsonArray);
							mBlogListView.setAdapter(mBlogListAdapter);
						}
						mBlogListAdapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});

		queue.add(stringRequest);
	}

	// private void testJsonVolly() {
	//
	// RequestQueue queue =
	// Volley.newRequestQueue(getActivity().getApplicationContext());
	// JsonObjectRequest json = new
	// JsonObjectRequest("http://www.wpdqq.com/wp-json/wp/v2/posts", null,
	// new Listener<JSONObject>() {
	//
	// @Override
	// public void onResponse(JSONObject object) {
	// //mBlogData = JSON.parseObject(object.toString(), BlogData.class);
	// }
	// }, new Response.ErrorListener() {
	//
	// @Override
	// public void onErrorResponse(VolleyError arg0) {
	// Log.d("TAG", arg0.getMessage());
	// }
	// });
	//
	// queue.add(json);
	// }

}
