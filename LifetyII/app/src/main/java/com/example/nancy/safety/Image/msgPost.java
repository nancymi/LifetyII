package com.example.nancy.safety.Image;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nancy on 2014/10/17.
 * 发送用户id，用户姓名，用户头像
 */
public class msgPost {
    static String message = null;
    public static String msgPost(String url, String bitmap, String userid, String username, String tabs) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        MultipartEntity reqEntity = new MultipartEntity();
        if (!bitmap.equals("")) {
            FileBody file = new FileBody(new File(bitmap));
            reqEntity.addPart("picture", file);
        }

        StringBody userid1 = new StringBody(userid);
        StringBody username1 = new StringBody(username);
        StringBody tabs1 = new StringBody(tabs);
        reqEntity.addPart("userid", userid1);
        reqEntity.addPart("username",username1);
        reqEntity.addPart("tabid",tabs1);
        httpPost.setEntity(reqEntity);

        //httpClient和httpResponse
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断是否连接

        System.out.println(httpResponse.getStatusLine().getStatusCode());
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            message = EntityUtils.toString(httpResponse.getEntity());
        }
        return message;
    }
}
