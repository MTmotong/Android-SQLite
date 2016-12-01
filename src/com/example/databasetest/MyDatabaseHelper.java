package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	
	public static final String GREATE_BOOK = "create table Book ("
			+ "id integer primary key autoincrement, "
			+ "author text, "
			+ "price real, "
			+ "pages integer, "
			+ "name text, "
			+ "category_id integer)";
	
	public static final String GREATE_CATEGORY = "create table Category (" 
			+ "id integer primary key autoincrement, "
			+ "category_name text, " 
			+ "category_code integer)";
	
	private Context mContext;
	
	//自己建的帮助类，继承自SQLiteOpenHelper，在这里重写onCreate和onUpgrate，在这两个方法中实现创建、升级数据库的逻辑
	public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(GREATE_BOOK); //调用execSQL方法去执行建表语句
		db.execSQL(GREATE_CATEGORY);
		Toast.makeText(mContext, "Create successed", Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
			case 1:
				db.execSQL(GREATE_CATEGORY);
			case 2:
				db.execSQL(GREATE_CATEGORY);
			default:
		
		}
	}
	
}
