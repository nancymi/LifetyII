package com.example.nancy.safety.adapter;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

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
    public void startUpdate(View view) {

    }

    @Override
    public Object instantiateItem(View view, int i) {
        return null;
    }

    @Override
    public void destroyItem(View view, int i, Object o) {

    }

    @Override
    public void finishUpdate(View view) {

    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {

    }



}
