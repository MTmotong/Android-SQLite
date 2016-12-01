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
			+ "name text)";
	
	private Context mContext;
	
	//�Լ����İ����࣬�̳���SQLiteOpenHelper����������дonCreate��onUpgrate����������������ʵ�ִ������������ݿ���߼�
	public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(GREATE_BOOK); //����execSQL����ȥִ�н������
		Toast.makeText(mContext, "Create successed", Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
}