package com.qiang.blog.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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

    public DBOpenHelper(Context context, int version) {
	this(context, null, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
	db.execSQL("create table " + "memo" + "(" + "_id integer primary key autoincrement,"+ "memo_type" + " int,"
		+ "memo_createtime" + " varchar(20)," + "memo_content"
		+ " longtext," + "memo_overdue" + " boolean," + "memo_date"
		+ " varchar(20)" + ")" + ";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	if (oldVersion < newVersion) {
	    db.execSQL("drop memo");
	    onCreate(db);
	    //db.execSQL("ALTER TABLE memo ADD _id integer primary key autoincrement");
	    // db.execSQL("update table " + "memo" + "(" + "_id"
	    // + " integer primary key autoincrement," + "memo_type"
	    // + " int," + "memo_createtime" + " varchar(20),"
	    // + "memo_content" + " longtext," + "memo_overdue"
	    // + " boolean," + "memo_date" + " varchar(20)" + ")" + ";");
	    // onCreate(db);
	}

    }

}
