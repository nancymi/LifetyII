package com.example.nancy.safety.Fragment;

import java.util.Calendar;

import com.example.nancy.safety.alarm.AlarmReceiver;
import com.example.nancy.safety.R;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class ContentFragment extends Fragment {
	Button mButton1;
    Button mButton2;

    TextView mTextView;

    Calendar calendar;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_content, container, false);
		calendar = Calendar.getInstance();

        mTextView = (TextView) view.findViewById(R.id.textView);
        mButton1 = (Button) view.findViewById(R.id.button1);
        mButton2 = (Button) view.findViewById(R.id.button2);
        
        mButton1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				calendar.setTimeInMillis(System.currentTimeMillis());
				int mHour = calendar.get(Calendar.HOUR_OF_DAY);
			    int mMinute = calendar.get(Calendar.MINUTE);
			    
			    
			    new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						calendar.setTimeInMillis(System
                                .currentTimeMillis());
		                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		                calendar.set(Calendar.MINUTE, minute);
		                calendar.set(Calendar.SECOND, 0);
		                calendar.set(Calendar.MILLISECOND, 0);
		                /* ����Intent��PendingIntent��������Ŀ����� */
	                    Intent intent = new Intent(getActivity(),AlarmReceiver.class);
	                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,intent, 0);
	                    
	                    AlarmManager am;
						/* ��ȡ���ӹ����ʵ�� */
	                    am = (AlarmManager)getActivity().getSystemService(android.content.Context.ALARM_SERVICE);
	                    /* �������� */
	                    am.set(AlarmManager.RTC_WAKEUP, calendar
	                                    .getTimeInMillis(), pendingIntent);
	                    /* ���������� */
	                    am.setRepeating(AlarmManager.RTC_WAKEUP, System
	                                    .currentTimeMillis()
	                                    + (10 * 1000), (24 * 60 * 60 * 1000),
	                                    pendingIntent);
	                    String tmpS = "��������ʱ��Ϊ" + format(hourOfDay)
	                                    + ":" + format(minute);
	                    mTextView.setText(tmpS);
					}
				}, mHour, mMinute, true).show();
			}
		});

        mButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    		getActivity(), 0, intent, 0);
                    AlarmManager am;
                    /* ��ȡ���ӹ����ʵ�� */
                    am = (AlarmManager)getActivity().getSystemService(android.content.Context.ALARM_SERVICE);
                    /* ȡ�� */
                    am.cancel(pendingIntent);
                    mTextView.setText("������ȡ����");
                }
        });
        
		return view;  
	}
	
	/* ��ʽ���ַ���(7:3->07:03) */
    private String format(int x) {
            String s = "" + x;
            if (s.length() == 1)
                    s = "0" + s;
            return s;
    }

}
