package com.example.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
        //通过构造函数的参数，将数据库名指定为Bookstore。db，版本号指定为1
        dbHelper = new MyDatabaseHelper(this, "Bookstore.db", null, 2);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		dbHelper.getWritableDatabase(); 
        		//第一次点击按钮时，会检测到当前程序中并没有此数据库，于是会创建数据库并调用MyDatabaseHelper中的onCreate方法
        	}
        });
        
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				//开始组装第一个数据
				values.put("name", "The Da Vinci Code");
				values.put("author", "Dan Brown");
				values.put("pages", 454);
				values.put("price", 16.90);
				db.insert("Book", null, values);
				values.clear();
				//插入第二条数据
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
        
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				Cursor cursor = db.query("Book", null, null, null, null, null, null);
				//调用cursor的moveToFirst方法，将数据的指针移动到第一行的位置	
				if(cursor.moveToFirst()) {
					do { // 循环中通过cursor的getColumnindex的方法获取到某一列在表中对应的位置索引，
					     //然后将索引传到相应的取值方法中，得到读取的数据
						String name = cursor.getString(cursor.getColumnIndex("name"));
						String author = cursor.getString(cursor.getColumnIndex("author"));
						int pages = cursor.getInt(cursor.getColumnIndex("pages"));
						double price = cursor.getDouble(cursor.getColumnIndex("price"));
						Log.d("MainActivity", "Book name is " + name);
						Log.d("MainActivity", "Book author is " + author);
						Log.d("MainActivity", "Book pages are " + pages);
						Log.d("MainActivity", "Book price is " + price);
						
					} while (cursor.moveToNext());
				}
				cursor.close();
			}
        	
        });
        
        Button replaceData = (Button) findViewById(R.id.replace_data);
        replaceData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				//开启一个事务
				db.beginTransaction();
				try {
					db.delete("Book", null, null);
					if(true) {
						throw new NullPointerException();
					}
					@SuppressWarnings("unused")
					ContentValues values = new ContentValues();
					values.put("name", "Game of Thrones");
					values.put("author", "George Martin");
					values.put("pages", 720);
					values.put("price", 43.9);
					db.insert("Book", null, values);
					db.setTransactionSuccessful();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.endTransaction();
				}
			}
        	
        });
    }

}
