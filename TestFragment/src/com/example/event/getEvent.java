package com.example.event;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Nancy on 2014/10/22.
 */
public class getEvent {
    public static String answer = null;
    public static String getEvent(final String url) {

        new Thread() {
          public void run() {
              try {
                  HttpGet httpGet = new HttpGet(url);
                  HttpClient httpClient = new DefaultHttpClient();
                  HttpResponse httpResponce = null;
                  try {
                      httpResponce = httpClient.execute(httpGet);
                  } catch (ClientProtocolException e) {
                      e.printStackTrace();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }

                  if (httpResponce.getStatusLine().getStatusCode() == 200) {
                      answer = EntityUtils.toString(httpResponce.getEntity());
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
        }.start();
        return answer;
    }
}
