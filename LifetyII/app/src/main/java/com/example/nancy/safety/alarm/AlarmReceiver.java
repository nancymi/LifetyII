package com.example.nancy.safety.alarm;

import com.example.nancy.safety.LifeActivity;

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
		//Toast.makeText(context, "�����õ�����ʱ�䵽��", Toast.LENGTH_LONG).show();
		Log.d("fuck","fuck");
		wakeUpAndUnlock(context);
		
		//ShowActivity是闹钟？
		//Intent intent2 = new Intent(context,ShowActivity.class);
		//intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//context.startActivity(intent2);
		//context.startActivity(intent);
		//Intent intent2 = new Intent(AlarmReceiver.this,MainActivity.class);
	}
	
	public static void wakeUpAndUnlock(Context context){  
        KeyguardManager km= (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);  
		KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");  
        //����  
        kl.disableKeyguard();  
        //��ȡ��Դ����������  
        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);  
        //��ȡPowerManager.WakeLock����,����Ĳ���|��ʾͬʱ��������ֵ,������LogCat���õ�Tag  
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");  
        //������Ļ  
        wl.acquire();  
        //�ͷ�  
        wl.release();  
    }  

}
