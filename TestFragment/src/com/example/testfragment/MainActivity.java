package com.example.testfragment;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.HistoryAdapter;
import com.example.adapter.VerticalPagerAdapter;
import com.example.view.VerticalViewPager;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity{
	private List<View> baseList;
	private LayoutInflater inflater;
	private View view1, view2, view3;
	private ViewPager viewPager;
	private VerticalViewPager checkOne, checkTwo, checkThree;
	private List<Fragment> oneListFragments, twoListFragments,
			threeListFragments;
	
	private ImageView imageView;// ����ͼƬ
	private TextView textView1,textView2,textView3;
	private List<View> views;// Tabҳ���б�
	private int offset = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int bmpW;// ����ͼƬ���
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitImageView();
		InitTextView();
		InitViewPager();
	}
	
	private void InitTextView() {
		textView1 = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);
		textView3 = (TextView) findViewById(R.id.text3);

		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
	}

	private void InitImageView() {
		imageView= (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// ��ȡͼƬ���
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
		offset = (screenW / 3 - bmpW) / 2;// ����ƫ����
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// ���ö�����ʼλ��
	}

	/*
	private void InitViewPager() {
		viewPager = (ViewPager) findViewById(R.id.check_list);
				
		baseList = getList();
		oneListFragments = getFragmentList();

		HistoryAdapter adapter = new HistoryAdapter(baseList);
		VerticalPagerAdapter fragmentAdapter = new VerticalPagerAdapter(
				getSupportFragmentManager(), oneListFragments);
		
		viewPager.setAdapter(adapter);	
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		checkOne.setAdapter(fragmentAdapter);

	}
	*/
	private void InitViewPager() {
		viewPager = (ViewPager) findViewById(R.id.check_list);
        baseList = getList();	
		HistoryAdapter adapter = new HistoryAdapter(baseList);
		viewPager.setAdapter(adapter);	
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		oneListFragments = getFragmentList();
		VerticalPagerAdapter fragmentAdapter = new VerticalPagerAdapter(
				getSupportFragmentManager(), oneListFragments);
		checkOne.setAdapter(fragmentAdapter);	
	}
	
	public List<View> getList() {
		List<View> mList;
		getLayoutInflater();
		inflater = LayoutInflater.from(this);
		//inflater = getLayoutInflater().from(this);
		view1 = inflater.inflate(R.layout.check_one, null);
		checkOne = (VerticalViewPager) view1.findViewById(R.id.check_one);
		view2 = inflater.inflate(R.layout.firstxml, null);
		view3 = inflater.inflate(R.layout.check_three, null);
		mList = new ArrayList<View>();
		mList.add(view1);
		mList.add(view2);
		mList.add(view3);
		return mList;
	}
	
	public List<String> getData() {
	    List<String> list = new ArrayList<String>();
	    for (int i = 0; i < 3; i++) {
	      list.add("unknown" + i);
	    }
	    return list;
	 }
	
	private class MyOnClickListener implements OnClickListener{
        private int index=0;
        public MyOnClickListener(int i){
        	index=i;
        }
		public void onClick(View v) {
			viewPager.setCurrentItem(index);			
		}
	}

	public List<Fragment> getFragmentList() {
		List<Fragment> listFragments = new ArrayList<Fragment>();
		
		Breakfirst breakfirst = new Breakfirst();
		listFragments.add(breakfirst);
		
		Lunch lunch = new Lunch();
		listFragments.add(lunch);
		
		Dinner dinner = new Dinner();
		listFragments.add(dinner);

		return listFragments;
	}

	public class MyOnPageChangeListener implements OnPageChangeListener{

    	int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
		int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����
		public void onPageScrollStateChanged(int arg0) {
			
			
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
			
		}

		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
			animation.setDuration(300);
			imageView.startAnimation(animation);
			Toast.makeText(MainActivity.this, "��ѡ����"+ viewPager.getCurrentItem()+"ҳ��", Toast.LENGTH_SHORT).show();
		}
    	
    }

}
