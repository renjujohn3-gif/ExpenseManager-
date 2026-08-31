package com.example.expensemanager;

import android.content.*;import android.database.sqlite.*;import android.database.*;import java.util.*;

public class ExpenseDb extends SQLiteOpenHelper {
 public ExpenseDb(Context c){super(c,"expenses.db",null,1);}
 public void onCreate(SQLiteDatabase db){db.execSQL("CREATE TABLE expenses(id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT,amount REAL,category TEXT,merchant TEXT,note TEXT,source TEXT)");}
 public void onUpgrade(SQLiteDatabase db,int oldV,int newV){}
 public void add(String date,double amount,String category,String merchant,String note,String source){ContentValues v=new ContentValues();v.put("date",date);v.put("amount",amount);v.put("category",category);v.put("merchant",merchant);v.put("note",note);v.put("source",source);getWritableDatabase().insert("expenses",null,v);}
 public List<String> recent(){List<String> out=new ArrayList<>();Cursor c=getReadableDatabase().rawQuery("SELECT date,amount,category,merchant,source FROM expenses ORDER BY id DESC LIMIT 30",null);while(c.moveToNext())out.add(c.getString(0)+" • ₹"+c.getDouble(1)+" • "+c.getString(2)+" • "+c.getString(3)+" ["+c.getString(4)+"]");c.close();return out;}
 public double total(){Cursor c=getReadableDatabase().rawQuery("SELECT COALESCE(SUM(amount),0) FROM expenses",null);c.moveToFirst();double x=c.getDouble(0);c.close();return x;}
}
