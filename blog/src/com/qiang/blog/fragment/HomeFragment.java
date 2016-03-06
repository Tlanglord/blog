package com.qiang.blog.fragment;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qiang.blog.R;
import com.qiang.blog.entity.BlogData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class HomeFragment extends Fragment implements OnClickListener {
	private ListView mResultList;
	private BlogData mBlogData;
	private TextView mTest;
	private com.alibaba.fastjson.JSONObject user;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_home, container, false);
		//mResultList = (ListView) rootView.findViewById(R.id.home_listview);
		mTest = (TextView)rootView.findViewById(R.id.test);
		mTest.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.test:
			//testJsonVolly();
			testVolly();
			break;

		default:
			break;
		}
		
	}
	
	private void testVolly() {

		RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
		StringRequest stringRequest = new StringRequest("http://www.wpdqq.com//wp-json/wp/v2/categories/1",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d("TAG", response);
						user = JSON.parseObject(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});

		queue.add(stringRequest);
	}
	
	
	private void testJsonVolly() {

		RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
		JsonObjectRequest json = new JsonObjectRequest("http://www.wpdqq.com/wp-json/wp/v2/posts", null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject object) {
						//mBlogData = JSON.parseObject(object.toString(), BlogData.class);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.d("TAG", arg0.getMessage());
					}
				});

		queue.add(json);
	}


}
