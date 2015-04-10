package com.example.login;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.example.testfragment.MainActivity;
import com.example.testfragment.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import MD5.MD5;




public class RegisterActivity extends Activity {

    private String userid = null;
    private String password = null;
    private EditText username1;
    private EditText password1;
    private Button register;
    private String result;
    private String message;
    private TextView textView;
    ActionBar actionBar;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    Handler handler = new Handler() {
        public void handleMessage (Message msg) {
            if (msg.what == 1) {
                textView.setText(result + ":" + message);
                result = null; message = null;}
            if (msg.what == 0) {
                textView.setText("璇风◢鍊�..");
            }
        };

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("娉ㄥ唽椤甸潰");

        username1 = (EditText) findViewById(R.id.username);
        password1 = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        textView = (TextView) findViewById(R.id.foodName);

        preferences = getSharedPreferences("user",MODE_PRIVATE);
        editor = preferences.edit();

        actionBar = getActionBar();

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
                userid = username1.getText().toString();
                password = password1.getText().toString();
                editor.putString("userid",userid);
                editor.commit();
                if (userid.equals(""))
                    Toast.makeText(getApplicationContext(), "鐢ㄦ埛鍚嶄笉鑳戒负绌猴紒", Toast.LENGTH_LONG).show();
                if (password.equals(""))
                    Toast.makeText(getApplicationContext(), "瀵嗙爜涓嶈兘涓虹┖", Toast.LENGTH_LONG).show();
                if (password.length() > 16)
                    Toast.makeText(getApplicationContext(), "瀵嗙爜闀垮害涓嶈兘澶т簬16浣�", Toast.LENGTH_LONG).show();
                password = MD5.getMD5(password.getBytes());

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String httpUrl = "http://1.assistantoflife.sinaapp.com/Register.php";
                            //1.HttpPost
                            HttpPost httpRequest = new HttpPost(httpUrl);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("userid", userid));
                            params.add(new BasicNameValuePair("password", password));
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
                            //鍒ゆ柇鏄惁杩炴帴
                            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                                String strResult = null;
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
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            } else
                                Toast.makeText(getApplicationContext(), "璇锋眰閿欒", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.register, menu);
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
                startActivity(new Intent(RegisterActivity.this,R_LActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
