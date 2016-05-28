package com.qiang.blog.app;

import io.rong.imlib.ipc.RongExceptionHandler;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.qiang.blog.requestbase.VolleyRequestManager;

public class BlogApplication extends Application {

	private static BlogApplication mContext;

	// static {
	// mContext = new BlogApplication();
	// }

	// public static Context getContext(){
	// if(mContext == null){
	// mContext = this;
	// }
	// return mContext;
	// }
	@Override
	public void onCreate() {
		super.onCreate();
		VolleyRequestManager.init(this);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheSize(2 * 1024 * 1024)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.memoryCache(new WeakMemoryCache()).build();
		ImageLoader.getInstance().init(config);
		if (getApplicationInfo().packageName
				.equals(getCurProcessName(getApplicationContext()))
				|| "io.rong.push"
						.equals(getCurProcessName(getApplicationContext()))) {

			// RongIM.init(this);
			ChatRongIM.init(this);

			/**
			 * 融云SDK事件监听处理
			 * 
			 * 注册相关代码，只需要在主进程里做。
			 */
			if (getApplicationInfo().packageName
					.equals(getCurProcessName(getApplicationContext()))) {

				// RongCloudEvent.init(this);
				// DemoContext.init(this);
				AppContext.init(this);
				Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(
						this));

				try {
					// RongIM.registerMessageType(AgreedFriendRequestMessage.class);

					// RongIM.registerMessageTemplate(new
					// ContactNotificationMessageProvider());
					// RongIM.registerMessageTemplate(new
					// RealTimeLocationMessageProvider());
					// @ 消息模板展示
					// RongContext.getInstance().registerConversationTemplate(
					// new NewDiscussionConversationProvider());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获得当前进程的名字
	 * 
	 * @param context
	 * @return
	 */
	@SuppressLint("NewApi")
	public static String getCurProcessName(Context context) {

		int pid = android.os.Process.myPid();

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
				.getRunningAppProcesses()) {

			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return null;
	}

	public static String getCurProcessName() {
		return getCurProcessName(mContext);
	}

}