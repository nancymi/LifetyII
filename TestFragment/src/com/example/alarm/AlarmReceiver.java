package com.example.alarm;

import com.example.testfragment.MainActivity;
import com.example.testfragment.ShowActivity;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//Toast.makeText(context, "你设置的闹钟时间到了", Toast.LENGTH_LONG).show();
		Log.d("fuck","fuck");
		wakeUpAndUnlock(context);
		
		
		Intent intent2 = new Intent(context,ShowActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
		//context.startActivity(intent);
		//Intent intent2 = new Intent(AlarmReceiver.this,MainActivity.class);
	}
	
	public static void wakeUpAndUnlock(Context context){  
        KeyguardManager km= (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);  
		KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");  
        //解锁  
        kl.disableKeyguard();  
        //获取电源管理器对象  
        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);  
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag  
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");  
        //点亮屏幕  
        wl.acquire();  
        //释放  
        wl.release();  
    }  

}
