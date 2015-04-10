package com.example.nancy.safety.Clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Nancy on 2014/10/18.
 */
public class AlarmAfternoon extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Intent i=new Intent(context, AlarmActivity.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(i);
        intent = new Intent(context, EveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}