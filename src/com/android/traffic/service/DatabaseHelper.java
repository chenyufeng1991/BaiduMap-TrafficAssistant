package com.android.traffic.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	static String name = "user.db";
	static int dbVersion = 1;

	public DatabaseHelper(Context context) {
		super(context, name, null, dbVersion);
	}

	// 只在创建的时候用一次
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table user(id INTEGER primary key autoincrement,用户名 TEXT,密码 TEXT,性别 TEXT,生日 TEXT,注册日期 TEXT,手机 TEXT,邮箱 TEXT)";
		db.execSQL(sql);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
