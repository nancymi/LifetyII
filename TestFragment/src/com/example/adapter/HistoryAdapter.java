package com.example.adapter;

import java.util.List;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class HistoryAdapter extends PagerAdapter {
	private List<View> mList;

	public HistoryAdapter(List<View> mList) {
		// TODO Auto-generated constructor stub
		this.mList = mList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(mList.get(position), 0);
		return mList.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(mList.get(position));
	}

}









	HsitoryAdapter：

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(mList.get(position), 0);
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView(mList.get(position));
    }


    java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.nancy.safety/com.example.nancy.safety.LifeActivity}: android.view.
    InflateException: Binary XML file line #7: Error inflating class com.example.view.VerticalViewPager
            