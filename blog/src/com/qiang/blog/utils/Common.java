package com.qiang.blog.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Common {

	public static SharedPreferences getSharedPreferences(Context context, String name, int mode) {
		return context.getSharedPreferences("app_guide_count", Context.MODE_PRIVATE);
	}

	public static SharedPreferences.Editor getSharedPreferencesEdit(Context context, String name, int mode) {
		SharedPreferences sharedPreferences = getSharedPreferences(context, name, mode);
		return sharedPreferences == null ? null : sharedPreferences.edit();
	}

	public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		if (reqWidth == 0 || reqHeight == 0) {
			return 1;
		}

		// 获取图片原生的宽和高
		final int height = options.outHeight;
		final int width = options.outWidth;
		//		Log.d(TAG, "origin, w= " + width + " h=" + height);
		int inSampleSize = 1;

		// 如果原生的宽高大于请求的宽高,那么将原生的宽和高都置为原来的一半 
		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// 主要计算逻辑 
			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
				inSampleSize *= 2;
			}
		}

//		Log.d(TAG, "sampleSize:" + inSampleSize);
		return inSampleSize;
	}
}
