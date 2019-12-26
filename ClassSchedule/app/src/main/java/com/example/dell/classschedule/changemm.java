package com.example.dell.classschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class changemm extends AppCompatActivity implements View.OnClickListener{
    protected Button change;//修改按钮
    protected String pwd1;//保存密码
    protected EditText password;//xml密码
    protected String username;//保存id
    protected Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changemm);
        password = (EditText) findViewById(R.id.password);
        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(this);
    }
    @Override
    public void onClick(final View v) {
        new Thread(){public void run() {

            switch (v.getId()) {
                case R.id.change:
                    Intent intent1 = getIntent();//获取intent对象
                    Bundle bundle = intent1.getExtras();//获取传递的数据包
                    username = bundle.getString("id");//获取输入
                    pwd1 = password.getText().toString();//获取输入
                    try {
                        //设置路径
                        String path="http://10.0.2.2:1819/api/lesson/password";
                        //创建URL对象
                        URL url=new URL(path);
                        //创建一个HttpURLconnection对象
                        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
                        //设置请求方法
                        conn.setRequestMethod("POST");
                        //设置从主机读取数据超时
                        conn.setReadTimeout(5000);
                        // 设置连接超时时间
                        conn.setConnectTimeout(5000);
                        //Post方式不能设置缓存，需要手动设置
                        conn.setUseCaches(false);
                        //准备要发送的数据
                        String data ="xh="+URLEncoder.encode(username,"utf-8")+"&mm="+URLEncoder.encode(pwd1,"utf-8");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//使用的是表单请求类型
                        conn.setRequestProperty("Content-Length", data.length()+"");
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        //连接
                        conn.connect();
                        //获得返回的状态码
                        conn.getOutputStream().write(data.getBytes());
                        int code=conn.getResponseCode();
                        if(code==200){
                            //更新UI
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                default:
                    break;
            }
        };}.start();
    }
}

