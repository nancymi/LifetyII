package com.example.nancy.safety;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import MD5.MD5;
/**
 * 登录，进入主界面的入口
 * **/

public class LoginActivity extends Activity {

    private Button login;
    private String userid;
    private String password;
    private EditText userid1;
    private EditText password1;
    private String result;
    private String message;
    private TextView textView;
    private String strResult = null;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ActionBar actionBar;

    Handler handler = new Handler() {
        public void handleMessage (Message msg) {
            if (msg.what == 1) {
                textView.setText(message);
            result = null; message = null;}
            if (msg.what == 0) {
                textView.setText("请稍候...");
            }
            if (msg.what == 3) {
                textView.setText("请求错误！");
            }
        };

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("登录界面");

        userid1 = (EditText) findViewById(R.id.userid);
        password1 = (EditText) findViewById(R.id.password);
        textView = (TextView) findViewById(R.id.wait);
        login =(Button) findViewById(R.id.login);

        actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setIcon(R.drawable.icon_small);//
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
        dialog.setTitle("正在登录");
        // dismiss监听

        dialog.setMessage("请稍候...");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);

                userid = userid1.getText().toString();
                password = password1.getText().toString();
                if (userid.equals(""))
                    Toast.makeText(getApplicationContext(), "用户名不能为空！", Toast.LENGTH_LONG).show();
                if (password.equals(""))
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_LONG).show();
                if (password.length() > 16)
                    Toast.makeText(getApplicationContext(), "密码长度不能大于16位", Toast.LENGTH_LONG).show();
                password = MD5.getMD5(password.getBytes());

                new Thread() {
                    public void run() {
                        try {
                            String httpUrl = "http://1.assistantoflife.sinaapp.com/Login.php";
                            //1.HttpPost
                            HttpPost httpRequest = new HttpPost(httpUrl);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            while (userid == null || password == null);
                            params.add(new BasicNameValuePair("userid", userid));
                            params.add(new BasicNameValuePair("password", password));
                            preferences = getSharedPreferences("user",MODE_PRIVATE);
                            editor = preferences.edit();
                            editor.putString("userid",userid);
                            editor.commit();
                            //2.HttpEntity
                            HttpEntity httpEntity = null;
                            try {
                                httpEntity = new UrlEncodedFormEntity(params, "utf8");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            httpRequest.setEntity(httpEntity);
                            //3.HttpClient
                            HttpClient httpClient = new DefaultHttpClient();
                            //4.HttpResponse
                            HttpResponse httpResponse = null;
                            try {
                                httpResponse = httpClient.execute(httpRequest);
                            } catch (ClientProtocolException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //判断是否连接
                            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                                dialog.cancel();
                                try {
                                    strResult = EntityUtils.toString(httpResponse.getEntity());

                                } catch (ClientProtocolException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    JSONObject jsonResult = new JSONObject(strResult);
                                    result = jsonResult.getString("result");
                                    message = jsonResult.getString("message");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                if (result.equals("-1")){
                                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    Message msg = new Message();
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                }
                                if (result.equals("0")) {
                                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    result = null; message = null;
                                    Intent intent = new Intent(LoginActivity.this, NewsActivity.class);
                                    startActivity(intent);
                                }
                            } else {
                                //Toast.makeText(getApplicationContext(), "请求错误", Toast.LENGTH_SHORT).show();
                                Message msg = new Message();
                                msg.what = 3;
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
                //startActivity(new Intent(MoreActivity.this,R_LActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
