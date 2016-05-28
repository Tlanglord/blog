package com.qiang.blog.entity;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.qiang.blog.utils.DBOpenHelper;

public class FriendListContentProvider extends ContentProvider {

	private DBOpenHelper mDbOpenHelper;
	private SQLiteDatabase mDB;

	@Override
	public boolean onCreate() {
		mDbOpenHelper = new DBOpenHelper(this.getContext(),
				ContentData.DATABASE_NAME, ContentData.DATABASE_VERSION);
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		mDB = mDbOpenHelper.getWritableDatabase();
		switch (ContentData.UserTableData.uriMatcher.match(uri)) {
		case ContentData.UserTableData.USERS:
			return mDB.query("User", projection, selection, selectionArgs,
					null, null, sortOrder);
		case ContentData.UserTableData.USER:
			// 进行解析，返回值为10
			long userId = ContentUris.parseId(uri);
			String where = "userId=" + userId;// 获取指定id的记录
			where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")"
					: "";// 把其它条件附加上
			return mDB.query("User", projection, where, selectionArgs, null,
					null, sortOrder);
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public String getType(Uri uri) {
		switch (ContentData.UserTableData.uriMatcher.match(uri)) {
		case ContentData.UserTableData.USERS:
			return ContentData.UserTableData.CONTENT_TYPE;
		case ContentData.UserTableData.USER:
			return ContentData.UserTableData.CONTENT_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		mDB = mDbOpenHelper.getWritableDatabase();
		long rowId;
		if (ContentData.UserTableData.uriMatcher.match(uri) != ContentData.UserTableData.USERS) {
			throw new IllegalArgumentException("Unknown URI" + uri);
		}
		rowId = mDB.insert(ContentData.UserTableData.TABLE_NAME,
				ContentData.UserTableData.USER_ID, values);
		if (rowId > 0) {
			Uri noteUri = ContentUris.withAppendedId(
					ContentData.UserTableData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(noteUri, null);
			return noteUri;
		}else{
			return null;
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		mDB = mDbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (ContentData.UserTableData.uriMatcher.match(uri)) {
		case ContentData.UserTableData.USER:
			count = mDB.delete(ContentData.UserTableData.TABLE_NAME, selection,
					selectionArgs);
			break;
		case ContentData.UserTableData.USERS:
			String id = uri.getPathSegments().get(1);
			count = mDB
					.delete(ContentData.UserTableData.TABLE_NAME,
							ContentData.UserTableData.USER_ID
									+ "="
									+ id
									+ (!TextUtils
											.isEmpty(ContentData.UserTableData.USER_ID = "?") ? "AND("
											+ selection + ')'
											: ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		mDB = mDbOpenHelper.getWritableDatabase();
        int count = 0;  
        switch (ContentData.UserTableData.uriMatcher.match(uri)) {  
       case ContentData.UserTableData.USER:
            count = mDB.update(ContentData.UserTableData.TABLE_NAME, values, selection, selectionArgs);  
            break;  
       case ContentData.UserTableData.USERS:
            // 下面的方法用于从URI中解析出id，对这样的路径content://com.ljq.provider.personprovider/person/10  
            // 进行解析，返回值为10  
            long personid = ContentUris.parseId(uri);  
            String where = "_ID=" + personid;// 获取指定id的记录  
            where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";// 把其它条件附加上  
            count = mDB.update(ContentData.UserTableData.TABLE_NAME, values, where, selectionArgs);  
            break;  
        default:  
            throw new IllegalArgumentException("Unknown URI " + uri);  
        }  
        mDB.close();  
        return count;  
	}
}
