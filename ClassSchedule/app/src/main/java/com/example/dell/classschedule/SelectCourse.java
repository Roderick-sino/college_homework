package com.example.dell.classschedule;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SelectCourse extends AppCompatActivity {
    static String sjson1;
    static String c;
    private int[] ids = {R.id.item1,R.id.item2,R.id.item3,R.id.item4,R.id.item5,R.id.item6,R.id.item7,R.id.item8,R.id.item9,R.id.item10,R.id.item11};
//    private String[] data=new String[100];


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            try{

                JSONObject jsonObject=new JSONObject(msg.obj.toString());
                String jsonArray=jsonObject.getString("list");
                JSONArray jsor=new JSONArray(jsonArray);
                for(int i=0;i<jsor.length();i++){
                    JSONObject js=jsor.getJSONObject(i);
                    TextView item=(TextView)findViewById(ids[i]);
                    item.setText("课程ID:"+js.get("课程id").toString()+"\t"+"课程名:"+js.get("课程名").toString()+"\t"+"学分:"+js.get("学分").toString()+"\t"+"任课教师:"+js.get("老师").toString()+"\t"+"教室:"+js.get("教室名").toString()+"\t"+"星期:"+js.get("星期").toString()+"\t"+"节课:"+js.get("节课").toString()+"\t"+"单双周:"+js.get("单双周").toString()+"\t"+"起始周:"+js.get("起始周").toString()+"\t"+"结束周:"+js.get("结束周").toString()+"\n");
                    item.setTextSize(15);
                }
//                ListView lvinfo = (ListView) findViewById(R.id.lvinaccountinfo);
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SelectCourse.this, android.R.layout.simple_list_item_1, data);
//                lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
            }
            catch(Exception e){ e.printStackTrace();}
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);
        new Thread() {
            public void run() {
                String path = "http://10.0.2.2:1819/api/select/course";
                Map<String,String> data=new HashMap<String,String>();
                HttpUtil.requestGet(handler,path,data);
            }
        }.start();



//        Intent intent= getIntent();
//        Bundle bundle=intent.getExtras();
//        sjson1=bundle.getString("sjson");
//        JSONArray jsor= null;
//        try {
//            jsor = new JSONArray(sjson1);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        try {
//            for (int i = 0; i < jsor.length(); i++) {
//                c+="课程号:\t"+jsor.getJSONObject(i).get("course_id").toString()
//                        +"课程名:\t"+jsor.getJSONObject(i).get("ccourse_name").toString()
//                        +"学分:\t"+jsor.getJSONObject(i).get("sore").toString()
//                        + "上课地点:\t"+jsor.getJSONObject(i).get("lesson").toString()
//                        + "单双周:\t"+jsor.getJSONObject(i).get("single_or_double").toString()
//                        + "开始周:\t"+ jsor.getJSONObject(i).get("start_week").toString()
//                        + "结束周:\t"+jsor.getJSONObject(i).get("end_week").toString()+"\n";
//            }
//        }catch(Exception e){ e.printStackTrace();}
//        TextView tv=(TextView) findViewById(R.id.l1);
//        tv.setText(c);
    }
}
