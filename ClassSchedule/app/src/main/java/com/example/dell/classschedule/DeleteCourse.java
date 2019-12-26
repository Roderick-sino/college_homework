package com.example.dell.classschedule;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class DeleteCourse extends AppCompatActivity {
    private Button delete,back;
    private EditText studentId,courseID;
    private String c_id;
    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    try{
                        JSONObject jsonObject=new JSONObject(msg.obj.toString());
                        String login=jsonObject.getString("msg");
                        if(login.equals("success")){
                            Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();}
                    }catch(Exception e){ e.printStackTrace();}
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);
        delete = (Button) findViewById(R.id.delete);
        back=(Button)findViewById(R.id.back);
        studentId = (EditText) findViewById(R.id.studentID);
        courseID = (EditText) findViewById(R.id.courseID);

        Intent intent1=getIntent();		//获取Intent对象
        Bundle bundle=intent1.getExtras();	//获取传递的数据包
        final String username=bundle.getString("id");		//获取输入的用户名
        studentId.setText(username);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_id = courseID.getText().toString().trim();
                new Thread() {
                    public void run() {
                        String path="http://10.0.2.2:1819/api/student/delete";
                        Map<String,String> data=new HashMap<String,String>();
                        data.put("xh",username);
                        data.put("kch",c_id);
                        HttpUtil.requestPost(handler,path,data,0);

                    }
                }.start();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
