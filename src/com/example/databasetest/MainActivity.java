package com.example.databasetest;

import android.app.Activity;
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
        //通过构造函数的参数，将数据库名指定为Bookstore。db，版本号指定为1
        dbHelper = new MyDatabaseHelper(this, "Bookstore.db", null, 1);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		dbHelper.getWritableDatabase(); 
        		//第一次点击按钮时，会检测到当前程序中并没有此数据库，于是会创建数据库并调用MyDatabaseHelper中的onCreate方法
        	}
        });
    }

}
