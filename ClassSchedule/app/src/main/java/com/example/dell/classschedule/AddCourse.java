package com.example.dell.classschedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AddCourse extends AppCompatActivity implements View.OnClickListener {
    private EditText CourseID, CourseName, Score, Teacher, WeekOfDay, WhichLesson, SingleOrDouble, StartWeek, EndWeek, Location;
    private Button add, back;
    private String CI,CN,T,L,WOD,WL,SOD,SW,EW,S;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        CourseID = (EditText) findViewById(R.id.CourseID);
        Score = (EditText) findViewById(R.id.Score);
        Teacher = (EditText) findViewById(R.id.Teacher);
        WeekOfDay = (EditText) findViewById(R.id.WeekOfDay);
        WhichLesson = (EditText) findViewById(R.id.WhichLesson);
        SingleOrDouble = (EditText) findViewById(R.id.SingleOrDouble);
        StartWeek = (EditText) findViewById(R.id.StartWeek);
        CourseName = (EditText) findViewById(R.id.CourseName);
        EndWeek = (EditText) findViewById(R.id.EndWeek);
        Location = (EditText) findViewById(R.id.Location);
        add = (Button) findViewById(R.id.add);
        back = (Button) findViewById(R.id.back);
        add.setOnClickListener(this);
        back.setOnClickListener(this);
}


    @Override
    public void onClick(final View v) {
        new Thread() {
            public void run() {

                switch (v.getId()) {
                    case R.id.add:

                        CI = CourseID.getText().toString().trim();
                        CN = CourseName.getText().toString().trim();
                        S =Score.getText().toString().trim();
                        T = Teacher.getText().toString().trim();
                        WOD = WeekOfDay.getText().toString().trim();
                        WL = WhichLesson.getText().toString().trim();
                        SOD = SingleOrDouble.getText().toString().trim();
                        SW = StartWeek.getText().toString().trim();
                        EW = EndWeek.getText().toString().trim();
                        L = Location.getText().toString().trim();

                        try {
                            //设置路径
                            String path = "http://10.0.2.2:1819/api/course/insert";
                            //创建URL对象
                            URL url = new URL(path);
                            //创建一个HttpURLconnection对象
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            //设置请求方法
                            conn.setRequestMethod("POST");
                            //设置从主机读取数据超时
                            conn.setReadTimeout(5000);
                            // 设置连接超时时间
                            conn.setConnectTimeout(5000);
                            //Post方式不能设置缓存，需要手动设置
                            conn.setUseCaches(false);
                            //准备要发送的数据

                            String data =
                                    "kch=" + URLEncoder.encode(CI, "utf-8")
                                    + "&kcm=" + URLEncoder.encode(CN, "utf-8")
                                    + "&xf=" + URLEncoder.encode(S, "utf-8")
                                    + "&ls=" + URLEncoder.encode(T, "utf-8")
                                    + "&d_o_w=" + URLEncoder.encode(WOD, "utf-8")
                                    + "&c_o_d=" + URLEncoder.encode(WL, "utf-8")
                                    + "&s_o_d=" + URLEncoder.encode(SOD, "utf-8")
                                    + "&s_w=" + URLEncoder.encode(SW, "utf-8")
                                    + "&e_w=" + URLEncoder.encode(EW, "utf-8")
                                    + "&js=" + URLEncoder.encode(L, "utf-8");

                            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//使用的是表单请求类型
                            conn.setRequestProperty("Content-Length", data.length() + "");
                            conn.setDoInput(true);
                            conn.setDoOutput(true);
                            //连接
                            conn.connect();
                            //获得返回的状态码
                            conn.getOutputStream().write(data.getBytes());
                            int code = conn.getResponseCode();
                            if (code == 200) {
                                //更新UI
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "请求失败，失败原因: " + code,Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case R.id.back:
                        finish();
                        break;

                    default:
                        break;
                }
            }
        }.start();
    }
}
