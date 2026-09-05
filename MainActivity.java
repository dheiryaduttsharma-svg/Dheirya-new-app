package com.nkashoka.fleet;
import android.app.*;import android.os.*;import android.graphics.*;import android.content.*;import android.view.*;import android.widget.*;import java.util.*;
public class MainActivity extends Activity{
 LinearLayout root,list; SharedPreferences db; String cat="ALL",role="ADMIN";
 String[] cats={"GREASE","SERVICE","TYRE","GPS","DIESEL","REPAIR","OTHER","ALL RECORDS"};
 String[] icons={"🛠️","🔧","🛞","📡","⛽","🔩","📋","📚"};
 TextView txt(String s,int z){TextView t=new TextView(this);t.setText(s);t.setTextSize(z);t.setPadding(16,14,16,14);return t;}
 Button button(String s){Button b=new Button(this);b.setText(s);return b;}
 public void onCreate(Bundle b){super.onCreate(b);db=getSharedPreferences("nkfleet",0);home();}
 void base(String title){root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(12,12,12,12);TextView h=txt(title,20);h.setTextColor(Color.WHITE);h.setBackgroundColor(Color.rgb(32,32,32));root.addView(h);setContentView(root);}
 void home(){base("🚌 NK ASHOKA BUS SERVICE GAGRET");root.addView(txt("Fleet Maintenance & Records",16));
 Button roleBtn=button("Current user: "+role+"  •  Change user");root.addView(roleBtn);roleBtn.setOnClickListener(v->{role=role.equals("ADMIN")?"STAFF":"ADMIN";home();});
 GridLayout g=new GridLayout(this);g.setColumnCount(2);root.addView(g);for(int i=0;i<cats.length;i++){final int k=i;Button b=button(icons[i]+"  "+cats[i]);g.addView(b,new ViewGroup.LayoutParams(-1,150));b.setOnClickListener(v->open(cats[k]));}
 root.addView(txt("Staff records should sync to Admin through the online backend in the production version.",13));}
 void open(String c){cat=c;base("📁 "+c);Button back=button("← Home");root.addView(back);back.setOnClickListener(v->home());
 EditText bus=new EditText(this);bus.setHint("Bus No. / Registration");root.addView(bus);
 EditText date=new EditText(this);date.setHint("Date (DD-MM-YYYY)");root.addView(date);
 EditText amount=new EditText(this);amount.setHint("Amount ₹");amount.setInputType(2);root.addView(amount);
 EditText details=new EditText(this);details.setHint("Details / Work done / Notes");root.addView(details);
 Button save=button("💾 SAVE RECORD");root.addView(save);
 Button pdf=button("📄 SHARE PDF (production backend/export)");root.addView(pdf);
 save.setOnClickListener(v->{if(bus.getText().toString().trim().isEmpty()||date.getText().toString().trim().isEmpty()){Toast.makeText(this,"Bus number and date required",Toast.LENGTH_SHORT).show();return;}
 String r=cat+"|"+role+"|"+bus.getText()+"|"+date.getText()+"|"+amount.getText()+"|"+details.getText()+"\n";db.edit().putString("data",r+db.getString("data","")).apply();Toast.makeText(this,"Record saved by "+role,Toast.LENGTH_SHORT).show();amount.setText("");details.setText("");showRecords();});
 pdf.setOnClickListener(v->Toast.makeText(this,"PDF export is reserved for the online/export build.",Toast.LENGTH_LONG).show());
 list=new LinearLayout(this);list.setOrientation(LinearLayout.VERTICAL);root.addView(list);showRecords();}
 void showRecords(){list.removeAllViews();for(String r:db.getString("data","").split("\\n")){if(r.trim().isEmpty())continue;String[] a=r.split("\\|",-1);if(cat.equals("ALL RECORDS")||a[0].equals(cat)){list.addView(txt("📅 "+a[3]+"   🚌 "+a[2]+"\\n👤 "+a[1]+"\\n"+a[5]+"\\n💰 ₹"+a[4],15));}}}
 public void onBackPressed(){home();}
}