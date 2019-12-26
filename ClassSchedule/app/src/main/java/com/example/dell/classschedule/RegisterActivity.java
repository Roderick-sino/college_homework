package com.example.dell.classschedule;

import android.app.Activity;
import android.view.View;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements View.OnClickListener{
    private Button register,back;
    private EditText id;
    private EditText pwd_1,pwd_2;
    private String username;
    private String pwd1,pwd2;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button) findViewById(R.id.register_do);
        back=(Button)findViewById(R.id.back_do);
        register.setOnClickListener(this);
        back.setOnClickListener(this);
        id = (EditText) findViewById(R.id.id_edit);
        pwd_1 = (EditText) findViewById(R.id.password_edit);
        pwd_2 = (EditText) findViewById(R.id.password_edit_1);
    }

    @Override
    public void onClick(final View v) {
        new Thread(){public void run() {

            switch (v.getId()) {
                case R.id.register_do:
                    username = id.getText().toString().trim();
                    pwd1 = pwd_1.getText().toString().trim();
                    pwd2 = pwd_2.getText().toString().trim();


                    if(!pwd1.equals(pwd2)){
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "两次输入密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        try {
                            //设置路径
                            String path="http://10.0.2.2:1819/api/register";
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
                                            Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            intent.putExtra("id", username);
                                            intent.putExtra("password", pwd1);
                                            startActivity(intent);
                                            finish();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case R.id.back_do:
                    //返回登录页面
                    finish();
                    break;
                default:
                    break;
            }
        };}.start();
    }
}
