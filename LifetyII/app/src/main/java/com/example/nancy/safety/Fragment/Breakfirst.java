package com.example.nancy.safety.Fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nancy.safety.Food.GetFoodInfo;
import com.example.nancy.safety.R;
import com.example.nancy.safety.dataBase.dataBase;

public class Breakfirst extends Fragment {
	private TextView textView;
	Handler handler = new Handler() {
		public void handleMessage (Message msg) {
			if (msg.what == 0 ) {
				textView.setText("dieawf:"+GetFoodInfo.foodids[0]);
			}
		}
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.check_one_item, container, false);
		
		try {
			SQLiteDatabase db = dataBase.createDatabase("user");
		} catch (SQLiteException e ) {
			e.printStackTrace();
		}

		textView = (TextView) view.findViewById(R.id.foodname);
        textView.setText("wtww");
		//GetFoodInfo.getFoodInfo(getActivity());
		
		//while(GetFoodInfo.bitmaps[0] == null) ; //��һѭ��
		
		//BitmapDrawable bd;
		//view.setBackgroundDrawable(new BitmapDrawable(GetFoodInfo.bitmaps[0]));
        view.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.background));
		//while (GetFoodInfo.foodids[0] == null);
		//Log.d("test",GetFoodInfo.foodids[0]);
 		//Message msg = new Message();
 		//msg.what = 0;
 		//handler.sendMessage(msg);
 		
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
