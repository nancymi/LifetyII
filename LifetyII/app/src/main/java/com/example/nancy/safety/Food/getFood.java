package com.example.nancy.safety.Food;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nancy on 2014/10/18.
 * 获取食物图片
 */
public class getFood {

    public static Bitmap bitmap;

    public static void getFoodPic(final String url, final String foodid) {

        Bitmap bitmap;

        DefaultHttpClient httpclient = new DefaultHttpClient();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("foodid",foodid));

        try {
            //1.HttpPost
            HttpPost httpRequest = new HttpPost(url);
            //2.HttpEntity
            HttpEntity httpEntity = null;
            try {
                httpEntity = new UrlEncodedFormEntity(params, "utf8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        httpRequest.setEntity(httpEntity);
        //HttpGet httpget = new HttpGet(url);

        try {
            HttpResponse resp = httpclient.execute(httpRequest);
            //判断是否正确执行
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
                //将返回内容转换为bitmap
                HttpEntity entity = resp.getEntity();
                InputStream in = entity.getContent();
                bitmap = BitmapFactory.decodeStream(in);
                getFood.bitmap = bitmap;

                //System.out.println("getFoodBitmap:" + bitmap);
                in.close();
            } else {
                System.out.println("网络连接错误");
            }

        } catch (Exception e) {
            e.printStackTrace();
            //setTitle(e.getMessage());
            System.out.println("HttpResponse错误");
        } finally {
            httpclient.getConnectionManager().shutdown();
            //System.out.println("Finally莫块");
        }
        } catch (Exception e) {
            e.printStackTrace();
           // System.out.println("HttpPost莫块");
        }
    }
}
