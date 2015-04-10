package com.example.nancy.safety;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nancy.safety.Clock.EveActivity;
import com.example.nancy.safety.Clock.MorActivity;
import com.example.nancy.safety.Clock.NoonActivity;

import java.util.Calendar;
import java.util.Locale;

/**
 * 设置闹钟时间
 * 早餐时间，午餐时间，晚餐时间
 * **/
public class ClockActivity extends Activity {

    private Button mTime;
    private Button nTime;
    private Button eTime;
    private TextView mTimec;
    private TextView nTimec;
    private TextView eTimec;
    private Button next;
    private int hours = 0;
    private int minutes = 0;
    private AlarmManager aManager;
    ActionBar actionBar;

    Handler handler = new Handler() {
        public void handleMessage (Message msg) {
            if (msg.what == 0) {
                mTimec.setText(hours + ":" + minutes);
            }
            if (msg.what == 1) {
                nTimec.setText(hours + ":" + minutes);
            }
            if (msg.what == 2) {
                eTimec.setText(hours + ":" + minutes);
            }
        };

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        this.setTitle("设置时间");

        mTime = (Button) findViewById(R.id.mtime);
        nTime = (Button) findViewById(R.id.ntime);
        eTime = (Button) findViewById(R.id.etime);
        mTimec = (TextView) findViewById(R.id.mtimec);
        nTimec = (TextView) findViewById(R.id.ntimec);
        eTimec = (TextView) findViewById(R.id.etimec);

        next = (Button) findViewById(R.id.finish);

        actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClockActivity.this,More2Activity.class));
            }
        });

        aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);

        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance(Locale.getDefault());
                new TimePickerDialog(ClockActivity.this,0,new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                        //指定启动AlarmActivity组件
                        Intent intent = new Intent(ClockActivity.this,MorActivity.class);
                        //创建PendingIntent对象
                        PendingIntent pi = PendingIntent.getActivity(ClockActivity.this, 0, intent, 0);
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);

                        aManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),60000*60*24,pi);
                        hours = hourOfDay;
                        minutes = minute;
                        System.out.println("current=" + hours + ":" + minutes);

                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);

                       // System.out.println("current=" + hours + ":" + minutes);
                        //Toast.makeText(ClockActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                },currentTime.get(Calendar.HOUR_OF_DAY),currentTime.get(Calendar.MINUTE),false).show();

                //while (hours == 0 && minutes == 0);
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });

        nTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance(Locale.getDefault());
                new TimePickerDialog(ClockActivity.this,0,new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                        //指定启动AlarmActivity组件
                        Intent intent = new Intent(ClockActivity.this,NoonActivity.class);
                        //创建PendingIntent对象
                        PendingIntent pi = PendingIntent.getActivity(ClockActivity.this, 0, intent, 0);
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);

                            aManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),60000*60*24,pi);
                            hours = hourOfDay;
                            minutes = minute;
                            System.out.println("current=" + hours + ":" + minutes);
                            //Toast.makeText(ClockActivity.this, "success", Toast.LENGTH_SHORT).show();

                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }

                },currentTime.get(Calendar.HOUR_OF_DAY),currentTime.get(Calendar.MINUTE),false).show();
                //while (hours == 0 && minutes == 0);

            }
        });

        eTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance(Locale.getDefault());
                new TimePickerDialog(ClockActivity.this,0,new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                        //指定启动AlarmActivity组件
                        Intent intent = new Intent(ClockActivity.this, EveActivity.class);
                        //创建PendingIntent对象
                        PendingIntent pi = PendingIntent.getActivity(ClockActivity.this, 0, intent, 0);
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);

                            //aManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                            aManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),60000*60*24,pi);

                            hours = hourOfDay;
                            minutes = minute;
                            System.out.println("current=" + hours + ":" + minutes);

                        Message msg = new Message();
                        msg.what = 2;
                        handler.sendMessage(msg);


                    }

                },currentTime.get(Calendar.HOUR_OF_DAY),currentTime.get(Calendar.MINUTE),false).show();
                //while (hours == 0 && minutes == 0);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.clock, menu);
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
