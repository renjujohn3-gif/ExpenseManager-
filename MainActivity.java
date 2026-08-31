package com.example.expensemanager;

import android.app.*;import android.os.*;import android.content.*;import android.graphics.Color;import android.view.*;import android.widget.*;import java.text.*;import java.util.*;

public class MainActivity extends Activity {
 ExpenseDb db; LinearLayout root,list; TextView total;
 int dp(float x){return (int)(x*getResources().getDisplayMetrics().density+.5f);}
 TextView tv(String s,int size){TextView t=new TextView(this);t.setText(s);t.setTextSize(size);t.setPadding(dp(8),dp(8),dp(8),dp(8));return t;}
 public void onCreate(Bundle b){super.onCreate(b);db=new ExpenseDb(this);build();}
 void build(){root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(dp(16),dp(16),dp(16),dp(8));
  TextView h=tv("Expense Manager",26);h.setTextColor(Color.rgb(40,50,100));root.addView(h);
  total=tv("Total recorded: ₹"+db.total(),20);root.addView(total);
  LinearLayout buttons=new LinearLayout(this);Button add=new Button(this);add.setText("+ Add Expense");add.setOnClickListener(v->addDialog());Button scan=new Button(this);scan.setText("📷 Scan QR / GPay");scan.setOnClickListener(v->startActivityForResult(new Intent(this,ScannerActivity.class),100));Button share=new Button(this);share.setText("Paste GPay text");share.setOnClickListener(v->pasteGpay());buttons.addView(add,new LinearLayout.LayoutParams(0,dp(52),1));buttons.addView(scan,new LinearLayout.LayoutParams(0,dp(52),1));buttons.addView(share,new LinearLayout.LayoutParams(0,dp(52),1));root.addView(buttons);
  root.addView(tv("Recent expenses",20));ScrollView sv=new ScrollView(this);list=new LinearLayout(this);list.setOrientation(LinearLayout.VERTICAL);sv.addView(list);root.addView(sv,new LinearLayout.LayoutParams(-1,0,1));setContentView(root);refresh(); }
 void refresh(){list.removeAllViews();for(String s:db.recent())list.addView(tv(s,15));total.setText("Total recorded: ₹"+db.total());}
 void addDialog(){LinearLayout l=new LinearLayout(this);l.setOrientation(LinearLayout.VERTICAL);EditText amount=e("Amount (₹)");EditText merchant=e("Merchant");EditText cat=e("Category");EditText note=e("Note");l.addView(amount);l.addView(merchant);l.addView(cat);l.addView(note);new AlertDialog.Builder(this).setTitle("Add Expense").setView(l).setPositiveButton("Save",(d,w)->{try{db.add(new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(new Date()),Double.parseDouble(amount.getText().toString()),cat.getText().toString(),merchant.getText().toString(),note.getText().toString(),"Manual");refresh();}catch(Exception ex){Toast.makeText(this,"Enter a valid amount",Toast.LENGTH_SHORT).show();}}).setNegativeButton("Cancel",null).show();}
 EditText e(String hint){EditText e=new EditText(this);e.setHint(hint);e.setInputType(1);return e;}
 void pasteGpay(){EditText e=e("Paste GPay transaction text");new AlertDialog.Builder(this).setTitle("GPay transaction").setMessage("Android does not allow apps to directly read your GPay payment history. You can paste/share the transaction text here.").setView(e).setPositiveButton("Save",(d,w)->{String s=e.getText().toString();String digits=s.replaceAll("[^0-9.]"," ").trim();String[] a=digits.split("\\s+");double amt=0;try{if(a.length>0)amt=Double.parseDouble(a[0]);}catch(Exception x){}db.add(new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(new Date()),amt,"GPay",s,"","GPay/Pasted");refresh();}).setNegativeButton("Cancel",null).show();}
}
