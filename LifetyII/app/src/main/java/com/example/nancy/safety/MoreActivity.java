package com.example.nancy.safety;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.nancy.safety.Image.RoundBitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MoreActivity extends Activity {

    private String username1;
    private String age1;
    private String sex1;
    private String status1;
    private String userid;

    private Button username;
    private Button age;
    private Button sex;
    private Button status;
    private EditText usernameform;

    private Button uploadPicture;
    private Button nextStep;
    ActionBar actionBar;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private Bitmap bitmap;
    private String filename;
    private final static String path = Environment.getExternalStorageDirectory() + "/Safety/icon/";

    Handler handler = new Handler() {
        public void handleMessage (Message msg) {
            if (msg.what == 0) {

                username.setText(username1);
            }
            if (msg.what == 1) {
                age.setText(age1);
            }
            if (msg.what == 2) {
                sex.setText(sex1);
            }
            if (msg.what == 3) {
                status.setText(status1);
            }
            if (msg.what == 4) {
                uploadPicture.setBackgroundResource(R.drawable.btn_hyaline);
            }
        };

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        this.setTitle("个人信息完善");

        preferences = getSharedPreferences("user",MODE_PRIVATE);
        editor = preferences.edit();

        username = (Button) findViewById(R.id.username);
        age = (Button) findViewById(R.id.age);
        sex = (Button) findViewById(R.id.sex);
        status = (Button) findViewById(R.id.status);

        uploadPicture = (Button) findViewById(R.id.UploadPicture);
        nextStep = (Button) findViewById(R.id.NextStep);
        actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final TableLayout usernameForm = (TableLayout) getLayoutInflater().inflate(R.layout.username,null);

        final String[] ages = {"0~10","11~15","16~20","21~25","26~30","30~40","41~50","51~60","61~70"};
        final String[] sexs = {"男","女"};
        final String[] statuss = {"学生","上班族","孕妇","健身者","幼儿"};
        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Message msg = new Message();
                msg.what = 4;
                handler.sendMessage(msg);
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);


                //startActivityForResult(intent, 1);
            }
        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("username",username1);
                editor.putString("age",age1);
                editor.putString("sex",sex1);
                editor.putString("status",status1);
                editor.commit();

                Intent intent = new Intent(MoreActivity.this,ClockActivity.class);
                startActivity(intent);
            }
        });
        username1 = new String();

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //startActivity(new Intent(MoreActivity.this,UsernameActivity.class));
                //写入username 并记录在button上（用handler设置）
                new AlertDialog.Builder(MoreActivity.this).setIcon(R.drawable.icon_small).
                        setTitle("自定义昵称").setView(usernameForm).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //这里一定要加usernameForm，不然程序不知道你的usernameform在哪里，会默认以为在MoreActivity里
                        usernameform = (EditText) usernameForm.findViewById(R.id.usernameform);
                        username1 = usernameform.getText().toString();
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    }
                }).create().show();
            }
        });

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MoreActivity.this).setTitle("选择年龄")
                        .setSingleChoiceItems(ages, 1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                age1 = ages[item];
                                Message msg = new Message();
                                msg.what = 1;
                                handler.sendMessage(msg);
                                dialog.cancel();
                            }
                        }).show();//显示对话框
            }
        });

        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MoreActivity.this).setTitle("选择性别")
                        .setSingleChoiceItems(sexs, 1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                sex1 = sexs[item];
                                Message msg = new Message();
                                msg.what = 2;
                                handler.sendMessage(msg);
                                dialog.cancel();
                            }
                        }).show();//显示对话框
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MoreActivity.this).setTitle("选择身份信息")
                        .setSingleChoiceItems(statuss, 1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                status1 = statuss[item];
                                Message msg = new Message();
                                msg.what = 3;
                                handler.sendMessage(msg);
                                dialog.cancel();
                            }
                        }).show();//显示对话框
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            //Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap result = BitmapFactory.decodeStream(cr.openInputStream(uri));
                //saveBitmap.saveBitmap(result,"icon");
                Bitmap bitmap = RoundBitmap.RoundBitmap(result);

                int bitWidth = bitmap.getWidth();
                int bitHeight = bitmap.getHeight();
                int standard = 200;
                Matrix matrix = new Matrix();
                float m = (float) standard/bitWidth;
                matrix.postScale(m,m);
                Bitmap bm = Bitmap.createBitmap(bitmap,0,0,bitWidth,bitHeight,matrix,true);
                saveFile(bm);

                ImageView imageView = (ImageView) findViewById(R.id.icon);
                /* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void saveFile(Bitmap bitmap) throws IOException{

        String userid = preferences.getString("userid","");

        File dirFile = new File(path);
        if(!dirFile.exists())
            dirFile.mkdirs();
        File myFile = new File(path + userid + ".jpg");
        System.out.println(myFile);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myFile));
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,bos);
        bos.flush();
        bos.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.more, menu);
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
