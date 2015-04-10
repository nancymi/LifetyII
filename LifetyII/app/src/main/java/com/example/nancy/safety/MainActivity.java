package com.example.nancy.safety;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nancy.safety.Clock.NoonActivity;
import com.example.nancy.safety.Food.GetFoodInfo;
import com.example.nancy.safety.R;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {

    private AlarmManager aManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
        //Calendar currentTime = Calendar.getInstance(Locale.getDefault());
        //指定启动AlarmActivity组件
        Intent intent = new Intent(MainActivity.this,GetFoodInfo.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        if(c.getTimeInMillis()> c.getTimeInMillis()) {
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
        }

        //aManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
        aManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),60000*60*24,pi);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
