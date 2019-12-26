package com.example.dell.classschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonalCenter extends AppCompatActivity {
    private Button changemm;
    private TextView Date;
    private TextView id;
    private Button back;
    private Button logout;

    private void initView() //设置显示当前时间，id
    {
        Date = (TextView)findViewById(R.id.Menu_main_textDate);
        Date.setText(getweek.getDate()+"   周"+ getweek.getDayStr(getweek.getWeekDay()));
        id = (TextView)findViewById(R.id.Menu_main_name);
        Intent intent1 = getIntent();//获取intent对象
        Bundle bundle = intent1.getExtras();//获取传递的数据包
        String username=bundle.getString("id");//获取输入
        id.setText(username+"欢迎您");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        changemm = (Button) findViewById(R.id.changemm);
        //给btn1绑定监听事件
        changemm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给bnt1添加点击响应事件
                Intent intent = new Intent(PersonalCenter.this,changemm.class);
                Intent intent1 = getIntent();//获取intent对象
                Bundle bundle = intent1.getExtras();//获取传递的数据包
                String username=bundle.getString("id");//获取输入
                String password=bundle.getString("password");//获取输入
                intent.putExtra("id",username);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });

        back = (Button) findViewById(R.id.button2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenter.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setTitle("个人中心");
        initView();
    }
}
