package com.example.dell.classschedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.Calendar;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    protected TextView weekTextView;//标题栏周数
    static int[][] b=new int[20][2];
    static String[] a=new String[20];
    static String c;
    static String  d;
    String sod="";
    static String sjson;

    private void initView() {
        weekTextView = (TextView)findViewById(R.id.week);
        weekTextView.setText("第" + getweek.getWeeks("2019-2-25") + "周(本周)");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        int[] ids = {R.id.lesson1_1, R.id.lesson1_2, R.id.lesson1_3, R.id.lesson1_4,
                R.id.lesson2_1, R.id.lesson2_2, R.id.lesson2_3, R.id.lesson2_4,
                R.id.lesson3_1, R.id.lesson3_2, R.id.lesson3_3, R.id.lesson3_4,
                R.id.lesson4_1, R.id.lesson4_2, R.id.lesson4_3, R.id.lesson4_4,
                R.id.lesson5_1, R.id.lesson5_2, R.id.lesson5_3, R.id.lesson5_4};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent= getIntent();
        Bundle bundle=intent.getExtras();
         sjson=bundle.getString("jsor");
        JSONArray jsor= null;
        try {
            jsor = new JSONArray(sjson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int len=jsor.length();
        try {
           for (int i = 0; i < len; i++) {

                JSONObject item = jsor.getJSONObject(i);
                b[i][0] = Integer.valueOf(item.get("day_of_week").toString());
                b[i][1] = Integer.valueOf(jsor.getJSONObject(i).get("class_of_day").toString());
                a[i] = jsor.getJSONObject(i).get("ccourse_name").toString() + "\n" + jsor.getJSONObject(i).get("teacher") + "\n@" + jsor.getJSONObject(i).get("lesson").toString();

              c="课程号:\t"+jsor.getJSONObject(i).get("course_id").toString()
              +"\n课程名:\t"+jsor.getJSONObject(i).get("ccourse_name").toString()
              +"\n学分:\t"+jsor.getJSONObject(i).get("sore").toString()+
                      "\n上课地点:\t"+     jsor.getJSONObject(i).get("lesson").toString()+
                      "\n单双周:\t"+jsor.getJSONObject(i).get("single_or_double").toString()
              +        "\n开始周:\t"+ jsor.getJSONObject(i).get("start_week").toString()
              +         "\n结束周:\t"+jsor.getJSONObject(i).get("end_week").toString();
            }
        }catch(Exception e){ e.printStackTrace();}
        for (int i = 0; i < ids.length; i++) {
            final TextView tv = (TextView) findViewById(ids[i]);
            int x = 2131427400;            int ai = tv.getId();
            ai=ai-ids[1]+2;

            int week = 0, l = 0;
            switch (ai) {
                case 1:
                    week = 1;
                    l = 1;
                    break;
                case 2:
                    week = 1;
                    l = 2;
                    break;
                case 3:
                    week = 1;
                    l = 3;
                    break;
                case 4:
                    week = 1;
                    l = 4;
                    break;
                case 5:
                    week = 2;
                    l = 1;
                    break;
                case 6:
                    week = 2;
                    l = 2;
                    break;
                case 7:
                    week = 2;
                    l = 3;
                    break;
                case 8:
                    week = 2;
                    l = 4;
                    break;
                case 9:
                    week = 3;
                    l = 1;
                    break;
                case 10:
                    week = 3;
                    l = 2;
                    break;
                case 11:
                    week = 3;
                    l = 3;
                    break;
                case 12:
                    week = 3;
                    l = 4;
                    break;
                case 13:
                    week = 4;
                    l = 1;
                    break;
                case 14:
                    week = 4;
                    l = 2;
                    break;
                case 15:
                    week = 4;
                    l = 3;
                    break;
                case 16:
                    week = 4;
                    l = 4;
                    break;
                case 17:
                    week = 5;
                    l = 1;
                    break;
                case 18:
                    week = 5;
                    l = 2;
                    break;
                case 19:
                    week = 5;
                    l = 3;
                    break;
                case 20:
                    week = 5;
                    l = 4;
                    break;
                default:
                    week = 0;
                    l = 0;
            }
            for (int j = 0; j < len; j++) {
                if (b[j][0] == week && b[j][1]== l) {
                    tv.setText(a[j]);
                    Log.i("Http", a[j]);
                }
            }

            if (tv.getText().toString().equals("")) {
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog();
                    }
                });
            } else {
                Random myRandom = new Random();
                int ranColor = 0x33000000 | myRandom.nextInt(0x00ffffff);
                tv.setBackgroundColor(ranColor);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog1();
                    }
                });
            }
        }
        initView();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu); //通过getMenuInflater()方法得到MenuInflater对象，再调用它的inflate()方法就可以给当前活动创建菜单了，第一个参数：用于指定我们通过哪一个资源文件来创建菜单；第二个参数：用于指定我们的菜单项将添加到哪一个Menu对象当中。
        return true; // true：允许创建的菜单显示出来，false：创建的菜单将无法显示。
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.personal:
                Intent intent = new Intent(MainActivity.this,PersonalCenter.class);
                Intent intent1 = getIntent();//获取intent对象
                Bundle bundle = intent1.getExtras();//获取传递的数据包
                String username=bundle.getString("id");//获取输入
                String password=bundle.getString("password");//获取输入
                intent.putExtra("id",username);
                intent.putExtra("password",password);
                startActivity(intent);
                break;
            case R.id.add_course:
                Intent intent2 = new Intent(MainActivity.this, AddCourse.class);
                startActivity(intent2);
                break;
            case R.id.select_all_course:
                Intent intent3 = new Intent(MainActivity.this, SelectCourse.class);
                Bundle bundle1 = new Bundle() ;
                bundle1.putString("jsor",sjson.toString());
                intent3.putExtras(bundle1) ;
                startActivity(intent3);
                break;
            default:
                break;
        }
        return true;
    }

    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.jq);
        builder.setTitle("添加课程");
        builder.setMessage("您这节没课，请问是否需要添加课程？");
        builder.setPositiveButton("添加",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, AddCourseID.class);
                        Intent intent1=getIntent();		//获取Intent对象
                        Bundle bundle=intent1.getExtras();	//获取传递的数据包
                        String username=bundle.getString("id");		//获取输入的用户名
                        intent.putExtra("id", username);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();

    }
    private void showDialog1(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.jq);
        builder.setTitle("课程信息");

        builder.setMessage(c);
        builder.setPositiveButton("删除",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, DeleteCourse.class);
                        Intent intent1=getIntent();		//获取Intent对象
                        Bundle bundle=intent1.getExtras();	//获取传递的数据包
                        String username=bundle.getString("id");		//获取输入的用户名
                        intent.putExtra("id", username);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();

    }
}
