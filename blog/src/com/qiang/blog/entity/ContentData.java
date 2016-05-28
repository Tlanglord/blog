package com.qiang.blog.entity;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContentData {
	public static final String AUTHORITY = "com.qiang.blog.friendProvider";
	public static final String DATABASE_NAME = "blog.db";
	public static final int DATABASE_VERSION = 4;
	public static final String TABLE_NAME = "user";

	public static final class UserTableData implements BaseColumns {
		public static final String TABLE_NAME = "user";
		// Uri，外部程序需要访问就是通过这个Uri访问的，这个Uri必须的唯一的。
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/user");
		// 数据集的MIME类型字符串则应该以vnd.android.cursor.dir/开头
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/hb.android.users";
		// 单一数据的MIME类型字符串应该以vnd.android.cursor.item/开头
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/hb.android.user";
		public static final int USERS = 1;
		public static final int USER = 2;

		public static String USER_ID = "id";
		public static final String USER_TITLE = "title";
		public static final String USER_NAME = "name";
		public static final String USER_DATE_ADDED = "date_added";
		public static final String USER_SEX = "sex";
		public static final String USER_IMAGE = "image_url";
		public static final String DEFAULT_SORT_ORDER = "name desc";

		public static final UriMatcher uriMatcher;
		static {
			// 常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
			uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
			// 如果match()方法匹配content://hb.android.teacherProvider/teachern路径,返回匹配码为TEACHERS
			uriMatcher.addURI(ContentData.AUTHORITY, "user", USERS);
			// 如果match()方法匹配content://hb.android.teacherProvider/teacher/230,路径，返回匹配码为TEACHER
			uriMatcher.addURI(ContentData.AUTHORITY, "user/#", USER);
		}
	}
}
