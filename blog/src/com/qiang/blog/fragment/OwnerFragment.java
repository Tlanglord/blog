package com.qiang.blog.fragment;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiang.blog.R;
import com.qiang.blog.activity.ChatActivity;
import com.qiang.blog.activity.MemoActivity;
import com.qiang.blog.activity.MovieActivity;
import com.qiang.blog.app.AppContext;
import com.qiang.blog.app.BlogApplication;
import com.qiang.blog.chat.listener.ChatSendMessageListener;
import com.qiang.blog.utils.NetUtils;

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

	private void login() {

		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {

				Map<String, String> requestParameter = new HashMap<String, String>();

				requestParameter.put("email", "732427480@qq.com");
				requestParameter.put("password", "2215509899");

				String result = NetUtils.sendPostRequest("email_login",
						requestParameter);
				return result;
			}

			@Override
			protected void onPostExecute(String result) {

				getToken();
			}
		}.execute();
	}

	private String token;

	/**
	 * 获得token
	 */
	private void getToken() {

		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {

				String result = NetUtils.sendGetRequest("token");
				return result;
			}

			@Override
			protected void onPostExecute(String result) {

				try {
					if (result != null) {
						JSONObject object = new JSONObject(result);
						JSONObject jobj = object.getJSONObject("result");

						if (object.getInt("code") == 200) {
							token = jobj.getString("token");
							token = "pw6929WRyNSJ4J/mWDlky2J73jch6ruDV+FEuLZmyhTrVeR8mKOZV6yeu0pm+TBApC0OVaknpbBTcqIrJIrYIC2KJTFPTWyQC/Ko0tAvKwA=";
							connect(token);
							SharedPreferences.Editor edit = AppContext
									.getInstance().getSharedPreferences()
									.edit();
							edit.putString("DEMO_TOKEN", token);
							edit.apply();

						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.execute();
	}

	/**
	 * 建立与融云服务器的连接
	 * 
	 * @param token
	 */
	private void connect(String token) {

		if (getActivity().getApplicationInfo().packageName
				.equals(BlogApplication.getCurProcessName(getActivity()
						.getApplicationContext()))) {

			/**
			 * IMKit SDK调用第二步,建立与服务器的连接
			 */
			RongIM.connect(token, new RongIMClient.ConnectCallback() {

				/**
				 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的
				 * Token
				 */
				@Override
				public void onTokenIncorrect() {

					Log.d("LoginActivity", "--onTokenIncorrect");
				}

				/**
				 * 连接融云成功
				 * 
				 * @param userid
				 *            当前 token
				 */
				@Override
				public void onSuccess(String userid) {

					Log.d("LoginActivity", "--onSuccess" + userid);
					startActivity(new Intent(mContext, ChatActivity.class));
					RongIM.getInstance().setSendMessageListener(
							new ChatSendMessageListener());
				}

				/**
				 * 连接融云失败
				 * 
				 * @param errorCode
				 *            错误码，可到官网 查看错误码对应的注释 http://www.rongcloud.cn
				 *            /docs/android.html#常见错误码
				 */
				@Override
				public void onError(RongIMClient.ErrorCode errorCode) {

					Log.d("LoginActivity", "--onError" + errorCode);
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ower_chat:
			login();
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
