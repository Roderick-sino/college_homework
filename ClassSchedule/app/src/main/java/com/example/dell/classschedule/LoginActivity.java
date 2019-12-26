package com.example.dell.classschedule;

import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


public class LoginActivity extends Activity implements OnClickListener {
    private EditText id;
    private EditText password;
    private Button login;
    private Button register;
    private String username;
    private String pwd;
    private int RequestCode = 1;
    static int len;

    public Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    try{
                        JSONObject jsonObject=new JSONObject(msg.obj.toString());
                        String jsonArray=jsonObject.getString("alist");
                        JSONArray jsor=new JSONArray(jsonArray);
                        len=jsor.length();
                        String login=jsonObject.getString("msg");
                        if(login.equals("success")){
                            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle() ;
                            bundle.putString("id", username) ;
                            bundle.putString("password", pwd) ;
                            bundle.putString("jsor",jsor.toString());
                            intent.putExtras(bundle) ;
                            startActivity(intent);
                            finish();
                        }
                        else{Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();}
                    }catch(Exception e){ e.printStackTrace();}
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                username = id.getText().toString().trim();
                pwd = password.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread() {
                                public void run() {
                                    String path = "http://10.0.2.2:1819/api/select/allcourse";
                                    Map<String,String> data=new HashMap<String,String>();
                                    data.put("xh",username);
                                    data.put("mm",pwd);
                                    HttpUtil.requestPost(handler,path,data,0);
                        }
                    }.start();
                }
                break;
            case R.id.register:
                //跳转到注册页面
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivityForResult(intent,RequestCode);
                break;
            default:
                break;
        }

    }
}