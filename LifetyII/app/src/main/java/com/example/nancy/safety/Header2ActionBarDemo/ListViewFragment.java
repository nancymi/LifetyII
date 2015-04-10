/*
 * Copyright (C) 2013 AChep@xda <artemchep@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.nancy.safety.Header2ActionBarDemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.achep.header2actionbar.HeaderFragment;
import com.example.nancy.safety.Food.GetFoodInfo;
import com.example.nancy.safety.NewsActivity;
import com.example.nancy.safety.R;
import com.example.nancy.safety.event.Event;
import com.example.nancy.safety.event.eventPackage;
import com.example.nancy.safety.event.getEvent;
import com.example.nancy.safety.event.stringManipulation;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by Artem on 06.12.13.
 */
public class ListViewFragment extends HeaderFragment {

    private ListView mListView;
    private String[] mListViewTitles;
    private boolean mLoaded;
    private ImageView foods;
    private static String[] plancontent = new String[10];
    private static String[] userplan = new String[10];

    private AsyncLoadSomething mAsyncLoadSomething;
    private FrameLayout mContentOverlay;

    Handler handler = new Handler() {
        public void handleMessage (Message msg) {
            if (msg.what == 0)
               // foods.setImageResource(R.drawable.header_bg);
            //foods.setImageResource(R.drawable.header_bg);
            foods.setImageDrawable(getResources().getDrawable(R.drawable.header_bg));
        };

    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        setHeaderBackgroundScrollMode(HEADER_BACKGROUND_SCROLL_PARALLAX);
        setOnHeaderScrollChangedListener(new OnHeaderScrollChangedListener() {
            @Override
            public void onHeaderScrollChanged(float progress, int height, int scroll) {
                height -= getActivity().getActionBar().getHeight();

                progress = (float) scroll / height;
                if (progress > 1f) progress = 1f;

                // *
                // `*
                // ```*
                // ``````*
                // ````````*
                // `````````*
                progress = (1 - (float) Math.cos(progress * Math.PI)) * 0.5f;

                ((NewsActivity) getActivity())
                        .getFadingActionBarHelper()
                        .setActionBarAlpha((int) (255 * progress));
            }
        });

        cancelAsyncTask(mAsyncLoadSomething);
        mAsyncLoadSomething = new AsyncLoadSomething(this);
        mAsyncLoadSomething.execute();
    }

    @Override
    public void onDetach() {
        cancelAsyncTask(mAsyncLoadSomething);
        super.onDetach();
    }

    @Override
    public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
        foods = (ImageView) container.findViewById(R.id.background);
        //foods = new ImageView(foods);
        GetFoodInfo.getFoodInfo(getActivity());
        while(GetFoodInfo.bitmaps[0] == null) ; //��һѭ��
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessage(msg);

        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        mListView = (ListView) inflater.inflate(R.layout.fragment_listview, container, false);
        if (mLoaded) setListViewTitles(mListViewTitles);
        return mListView;
    }

    @Override
    public View onCreateContentOverlayView(LayoutInflater inflater, ViewGroup container) {
        ProgressBar progressBar = new ProgressBar(getActivity());
        mContentOverlay = new FrameLayout(getActivity());
        mContentOverlay.addView(progressBar, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        if (mLoaded) mContentOverlay.setVisibility(View.GONE);
        return mContentOverlay;
    }

    private void setListViewTitles(String[] titles) {
        mLoaded = true;
        mListViewTitles = titles;
        if (mListView == null) return;

        mListView.setVisibility(View.VISIBLE);
        setListViewAdapter(mListView, new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                mListViewTitles));
    }

    private void cancelAsyncTask(AsyncTask task) {
        if (task != null) task.cancel(false);
    }

    // //////////////////////////////////////////
    // ///////////// -- LOADER -- ///////////////
    // //////////////////////////////////////////

    private static class AsyncLoadSomething extends AsyncTask<Void, Void, String[]> {

        private static final String TAG = "AsyncLoadSomething";

        final WeakReference<ListViewFragment> weakFragment;

        public AsyncLoadSomething(ListViewFragment fragment) {
            this.weakFragment = new WeakReference<ListViewFragment>(fragment);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            final ListViewFragment audioFragment = weakFragment.get();
            if (audioFragment.mListView != null) audioFragment.mListView.setVisibility(View.INVISIBLE);
            if (audioFragment.mContentOverlay != null) audioFragment.mContentOverlay.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(Void... voids) {

            try {
                // Emulate long downloading
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           /** new Thread() {
                public void run() {
                    //获取生活轨迹的地址
                    final String getEventUrl = "http://1.assistantoflife.sinaapp.com/GetUserPlan.php";
                    getEvent.getEvent(getEventUrl);
                    System.out.println("step1");
                    while (getEvent.answer == null) ;
                    System.out.println(getEvent.answer);
                    String answer = getEvent.answer;
                    //System.out.println(answer);
                    try

                    {
                        JSONObject json = new JSONObject(answer);
                        JSONObject plan = new JSONObject(answer).getJSONObject("message");
                        String result = json.getString("result");
                        System.out.println("result:" + result);
                        int num = Integer.parseInt(result);
                        //System.out.println(num);
                        if (num == -1) {
                           // Message msg = new Message();
                           // msg.what = 0;
                           // handler.sendMessage(msg);
                            System.out.println("Wrong connecting");
                        } else {
                            try {
                                stringManipulation.stringManipulation(plan, num);
                                Event[][] event = new Event[10][100];
                                int j = 0;
                                for (int i = 0; i < num; i++) {
                                    //需要显示的userid和event
                                    while (stringManipulation.answer[i][0] == null ||
                                            stringManipulation.answer[i][1] == null) {
                                        System.out.println("waiting");
                                    }

                                    plancontent[i] = stringManipulation.answer[i][1];
                                    //System.out.println(plancontent[i]);
                                    if (eventPackage.eventBack(plancontent[i]) != null) {
                                        while (j < eventPackage.NUM) {
                                            System.out.println("testi" + i);
                                            event[i][j] = new Event();
                                            event[i][j].setUserid(stringManipulation.answer[i][0]);
                                            ++ j;
                                            System.out.println("testj" + j);
                                            System.out.println("List=" + event[i][j].getUserid() + ":"
                                                    + event[i][j].getTime()
                                                    + ":" + event[i][j].getContent());
                                        }
                                        //event[i] = new Event();
                                        event[i] = eventPackage.eventBack(plancontent[i]);

                                        userplan[i] = new String();
                                        userplan[i] = "userid:" + event[i][j].getUserid() + '\n' + "usercontent:";
                                        while (--j >= 0)
                                            userplan[i] += event[i][j].getTime() + event[i][j].getContent() + '\n';
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (
                            JSONException e
                            )

                    {
                        e.printStackTrace();
                        System.out.println("JSON:" + e.getMessage().toString());
                    }
                }
            }.start(); **/

            return new String[]{"Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder",
                    "Placeholder", "Placeholder", "Placeholder", "Placeholder"};
            //while (userplan[0] == null);
           // return userplan;
        }

        @Override
        protected void onPostExecute(String[] titles) {
            super.onPostExecute(titles);
            final ListViewFragment audioFragment = weakFragment.get();
            if (audioFragment == null) {
                if (Project.DEBUG) Log.d(TAG, "Skipping.., because there is no fragment anymore.");
                return;
            }

            if (audioFragment.mContentOverlay != null) audioFragment.mContentOverlay.setVisibility(View.GONE);
            audioFragment.setListViewTitles(titles);
        }
    }
}
