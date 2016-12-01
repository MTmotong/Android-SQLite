package com.example.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private MyDatabaseHelper dbHelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ͨ�����캯���Ĳ����������ݿ���ָ��ΪBookstore��db���汾��ָ��Ϊ1
        dbHelper = new MyDatabaseHelper(this, "Bookstore.db", null, 2);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		dbHelper.getWritableDatabase(); 
        		//��һ�ε����ťʱ�����⵽��ǰ�����в�û�д����ݿ⣬���ǻᴴ�����ݿⲢ����MyDatabaseHelper�е�onCreate����
        	}
        });
        
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				//��ʼ��װ��һ������
				values.put("name", "The Da Vinci Code");
				values.put("author", "Dan Brown");
				values.put("pages", 454);
				values.put("price", 16.90);
				db.insert("Book", null, values);
				values.clear();
				//����ڶ�������
				values.put("name", "The Lost Symbol");
				values.put("author", "Dan Brown");
				values.put("pages", 788);
				values.put("price", 89.9);
				db.insert("Book", null, values);
			}
        });
        
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("price", 543.9);
				db.update("Book", values, "name = ?", new String[] { "The DaVinci Code" });
			}
        	
        });
        
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.delete("Book", "pages > ?", new String[] { "454" });
			}
        	
        });
        
    }

}
