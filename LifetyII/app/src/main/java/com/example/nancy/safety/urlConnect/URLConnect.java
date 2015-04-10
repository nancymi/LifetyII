package com.example.nancy.safety.urlConnect;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;


/**
 * Created by Nancy on 2014/10/5.
 * 用HttpClient连接服务器
 */
public class URLConnect {
    public static String strResult;
    public static void URLConnect(final String httpUrl, final List<NameValuePair> params) {

                try {
                    System.out.println("wtf1");
                    //1.HttpPost
                    HttpPost httpRequest = new HttpPost(httpUrl);
                    //2.HttpEntity
                    HttpEntity httpEntity = null;
                    try {
                        System.out.println("wtf2");
                        httpEntity = new UrlEncodedFormEntity(params, "utf8");
                    } catch (Exception e) {
                        System.out.println("wtf21");
                        e.printStackTrace();
                       // System.out.println("WTF!!!");
                    }
                    System.out.println("wtf3");
                    httpRequest.setEntity(httpEntity);
                    //3.HttpClient
                    HttpClient httpClient = new DefaultHttpClient();
                    //4.HttpResponse
                    HttpResponse httpResponse = null;
                    try {
                        System.out.println("wtf4");
                        httpResponse = httpClient.execute(httpRequest);
                        System.out.println("wtf5");
                    } catch (ClientProtocolException e) {
                        System.out.println("wtf41");
                        e.printStackTrace();
                    } catch (IOException e) {
                        System.out.println("wtf42");
                        e.printStackTrace();
                    }
                    //判断是否连接
                    System.out.println("已连接？"+ httpResponse.getStatusLine().getStatusCode());
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        try {
                            strResult = EntityUtils.toString(httpResponse.getEntity());
                            System.out.println("strResult=" + strResult);
                        } catch (ClientProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("wtf11");
                    e.printStackTrace();
                }


            }


}
