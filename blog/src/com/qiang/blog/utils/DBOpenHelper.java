package com.qiang.blog.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.qiang.blog.entity.ContentData;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	public DBOpenHelper(Context context, String name, int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + ContentData.UserTableData.TABLE_NAME + "("
				+ ContentData.UserTableData.USER_ID
				+ " INTEGER PRIMARY KEY autoincrement,"
				+ ContentData.UserTableData.USER_NAME + " varchar(20),"
				+ ContentData.UserTableData.USER_TITLE + " varchar(20),"
				+ ContentData.UserTableData.USER_IMAGE + " varchar(200),"
				+ ContentData.UserTableData.USER_DATE_ADDED + " long,"
				+ ContentData.UserTableData.USER_SEX + " boolean)" + ";");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
