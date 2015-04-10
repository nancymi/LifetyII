package com.example.nancy.clock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MyActivity extends Activity {

    private Button btn=null;
    private AlarmManager alarmManager=null;
    Calendar cal= Calendar.getInstance();
    final int DIALOG_TIME = 0;    //设置对话框id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        btn = (Button) findViewById(R.id.set);
        //获取AlarmManager对象
        alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MyActivity.this,0,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        Intent intent = new Intent(MyActivity.this,AlarmReciver.class);
                        PendingIntent pi = PendingIntent.getActivity(MyActivity.this,0,intent,0);
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        //设置calendar对象
                        c.set(Calendar.HOUR,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        //设置AlarmManager将在Calendar对应的时间启动指定组件
                        alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
                        Toast.makeText(MyActivity.this,"success.",Toast.LENGTH_SHORT).show();
                    }
                },cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
        return super.onOptionsItemSelected(item);
    }
}
