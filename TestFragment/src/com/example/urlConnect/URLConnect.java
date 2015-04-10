package com.example.urlConnect;


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
 * 閻⑩剦ttpClient鏉╃偞甯撮張宥呭閸ｏ拷
 */
public class URLConnect {
    public static String strResult = new String();
    public static String URLConnect(final String httpUrl, final List<NameValuePair> params) {

            try {
               // String strResult = null;
                //strResult = "haha";
                //1.HttpPost
                HttpPost httpRequest = new HttpPost(httpUrl);
                //2.HttpEntity
                HttpEntity httpEntity = null;
                try {
                    httpEntity = new UrlEncodedFormEntity(params, "utf8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                httpRequest.setEntity(httpEntity);
                //3.HttpClient
                HttpClient httpClient = new DefaultHttpClient();
                //4.HttpResponse
                HttpResponse httpResponse = null;
                try {
                    httpResponse = httpClient.execute(httpRequest);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //閸掋倖鏌囬弰顖氭儊鏉╃偞甯�
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    try {
                    	URLConnect.strResult = EntityUtils.toString(httpResponse.getEntity());
                        System.out.println("strResult200:" + URLConnect.strResult);
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (URLConnect.strResult.equals("hehe") ||URLConnect.strResult.equals("haha")){
                    URLConnect.strResult = "......";
                    System.out.println("strResult:" + URLConnect.strResult);
                } else {
                	URLConnect.strResult = "Cannot connect!";
                    System.out.println("strResult:" + URLConnect.strResult);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("TryException" + URLConnect.strResult);
            }

        return strResult;
    }


}
