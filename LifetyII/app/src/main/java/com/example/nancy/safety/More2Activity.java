package com.example.nancy.safety;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nancy.safety.Image.Image;
import com.example.nancy.safety.Image.msgPost;
import com.example.nancy.safety.urlConnect.URLConnect;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * 添加标签，上传用户头像，userid，username，tabid
 * **/

public class More2Activity extends Activity {

    private Button tab1;
    private Button tab2;
    private Button tab3;
    private Button tab4;
    private Button tab5;
    private Button tab6;
    private Button tab7;
    private Button tab8;
    private Button tab9;
    private Button tab10;
    private Button tab11;
    private Button tab12;
    private Button tab13;
    private Button tab14;
    private Button tab15;
    private Button tab16;
    private Button tab17;
    private Button tab18;
    private Button tab19;
    private Button tab20;
    private Button finish;
    private String tabs;
    private String tabid;
    private TextView wait;
    private String result;
    private String message;
    ActionBar actionBar;
    private int i = 0;

    android.os.Handler handler = new android.os.Handler() {
        public void handleMessage (Message msg) {
            if (msg.what == 0) {
                wait.setText("请稍候...");
            }
            if (msg.what == 1) {
                if (result.equals("0")) {
                    wait.setText("上传成功！");
                    startActivity(new Intent(More2Activity.this,NewsActivity.class));
                } else {
                    wait.setText(message);
                }
            }
        };

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more2);
        this.setTitle("添加标签");
        //List<NameValuePair> params = new ArrayList<NameValuePair>();


        tab1 = (Button) findViewById(R.id.tab1);
        tab2 = (Button) findViewById(R.id.tab2);
        tab3 = (Button) findViewById(R.id.tab3);
        tab4 = (Button) findViewById(R.id.tab4);
        tab5 = (Button) findViewById(R.id.tab5);
        tab6 = (Button) findViewById(R.id.tab6);
        tab7 = (Button) findViewById(R.id.tab7);
        tab8 = (Button) findViewById(R.id.tab8);
        tab9 = (Button) findViewById(R.id.tab9);
        tab10 = (Button) findViewById(R.id.tab10);
        tab11 = (Button) findViewById(R.id.tab11);
        tab12 = (Button) findViewById(R.id.tab12);
        tab13 = (Button) findViewById(R.id.tab13);
        tab14 = (Button) findViewById(R.id.tab14);
        tab15 = (Button) findViewById(R.id.tab15);
        tab16 = (Button) findViewById(R.id.tab16);
        tab17 = (Button) findViewById(R.id.tab17);
        tab18 = (Button) findViewById(R.id.tab18);
        tab19 = (Button) findViewById(R.id.tab19);
        tab20 = (Button) findViewById(R.id.tab20);

        finish = (Button) findViewById(R.id.finish);
        wait = (TextView) findViewById(R.id.wait);
        actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setIcon(R.drawable.icon_small);//
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
        dialog.setTitle("正在提交");
        // dismiss监听

        dialog.setMessage("请稍候...");
        //final int finalI = i;
        /*for (i = 1;i <= 20;i ++) {

            tab[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //final int finalI = i;
                    tab[].setBackgroundResource(R.drawable.tab_pressed);
                    tabs += "2000"+ i + "-";
                }
            });
        }*/

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab1.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20001-";
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab2.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20002-";
            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab3.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20003-";
            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab4.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20004-";
            }
        });

        tab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab5.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20005-";
            }
        });

        tab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab6.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20006-";
            }
        });

        tab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab7.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20007-";
            }
        });

        tab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab8.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20008-";
            }
        });

        tab9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab9.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20009-";
            }
        });

        tab10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab10.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20010-";
            }
        });

        tab11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab11.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20011-";
            }
        });

        tab12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab12.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20012-";
            }
        });

        tab13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab13.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20013-";
            }
        });

        tab14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab14.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "200014-";
            }
        });

        tab15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab15.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20015-";
            }
        });

        tab16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab16.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20016-";
            }
        });

        tab17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab17.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20017-";
            }
        });

        tab18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab18.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20018-";
            }
        });

        tab19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab19.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20019-";
            }
        });

        tab20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab20.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20020-";
            }
        });

        tab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab8.setBackgroundResource(R.drawable.tab_pressed);
                tabs += "20008-";
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Message msg = new Message();
                //msg.what = 0;
                //handler.sendMessage(msg);
                dialog.show();

                SharedPreferences sharedPreferences= getSharedPreferences("user",
                        MODE_PRIVATE);
                final String userid = sharedPreferences.getString("userid", "");
                final String username = sharedPreferences.getString("username", "");
                String sex = sharedPreferences.getString("sex", "");
                String status = sharedPreferences.getString("status", "");
                String age = sharedPreferences.getString("age", "");

                if (tabs != null) {
                    tabid = tabs.substring(4,tabs.length()-1);
                    System.out.println(tabid);
                }


                File dirFile = Environment.getExternalStorageDirectory();
                final String fileName = dirFile + "/Safety/icon/" + userid + ".jpg";
                final String url = "http://1.assistantoflife.sinaapp.com/UploadUserInfo.php";

                new Thread() {
                    public void run() {
                        String send = null;
                        try {
                            send = msgPost.msgPost(url, fileName, userid, username, tabid);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            JSONObject json = new JSONObject(send);
                            result = json.getString("result");
                            message = json.getString("message");
                            System.out.println(result + message);
                            dialog.cancel();
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();



                //System.out.println(send);

                /** JSONObject jsonResult = null;
                 try {
                 jsonResult = new JSONObject(send);
                 String result = jsonResult.getString("result");
                 String message = jsonResult.getString("message");
                 System.out.println(result + ":" + message);
                 } catch (JSONException e) {
                 e.printStackTrace();
                 }**/


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.more2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                //startActivity(new Intent(RegisterActivity.this,R_LActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
