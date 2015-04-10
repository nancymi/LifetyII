package com.example.nancy.safety.Image;

import android.app.Activity;
import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by Nancy on 2014/10/5.
 * 采用URL与服务器连接并封装数据进行发送
 */
public class Image extends Activity {
    public void ImageHandle() {

    }

    public static String sendUserInfo(String url, String info)
    {
        BufferedReader in = null;
        String result = "";
        PrintWriter out = null;
        try
        {
            URL realUrl = new URL(url);
            //打开连接
            URLConnection con = realUrl.openConnection();
            //设置报文头参数
            con.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
            con.setRequestProperty("content-type","application/x-www-form-urlencoded");
            con.setRequestProperty("accept","*/*");
            //设置输入输出流
            con.setDoOutput(true);
            con.setDoInput(true);
            //获取输出流
            out = new PrintWriter(con.getOutputStream());
            //发送请求参数
            out.print(info);
            out.flush();
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while((line = in.readLine()) != null)
                result += "\n" + line;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //info字符串信息,filename图片文件路径
    public static String UploadPicture(final String userid, final String tabid, final String username, final String filename)
    {
        new Thread() {
            public void run() {

                BufferedReader in = null;
                String result = "";
                PrintWriter out = null;
                try {
                    //页面url
                    URL realUrl = new URL("http://1.assistantoflife.sinaapp.com/UploadUserInfo.php");
                    //连接对象
                    //URLConnection con = realUrl.openConnection();
                    HttpURLConnection con = (HttpURLConnection)realUrl.openConnection();
                    //tp报文体边界字符串
                    //后面那个是当前时间的秒数
                    //用于保证这个字符串不会与别的相同，用来分割本报文的不同部分
                    //String boundary = "--boundary---" + DateTime.Now.Ticks.ToString("x");
                    String boundary = "--boundary---" + new Date(System.currentTimeMillis());
                    //设置报文头参数
                    con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");
                    con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                    con.setRequestProperty("Accept", "*/*");
                    con.setRequestMethod("POST");
                    //模拟http请求报文体数据
                    //这个stringbuilder用于构建一个长字符串，java没有就换一个
                    StringBuilder sb = new StringBuilder();
                    sb.append("--" + boundary);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"username\"");
                    sb.append("\r\n\r\n");
                    sb.append(username);
                    sb.append("\r\n");
                    sb.append("--" + boundary);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"userid\"");
                    sb.append("\r\n\r\n");
                    sb.append(userid);
                    sb.append("\r\n");
                    sb.append("--" + boundary);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"tabid\"");
                    sb.append("\r\n\r\n");
                    sb.append(tabid);
                    sb.append("\r\n");
                    sb.append("--" + boundary);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"picture\"; filename=\"" + filename + "\"");
                    sb.append("\r\n");
                    sb.append("Content-Type: imagepeg");
                    sb.append("\r\n\r\n");
                    //头部先构造为字符串
                    byte[] head = sb.toString().getBytes();
                    //构造报文结尾
                    byte[] tail = new String("\r\n--" + boundary + "--\r\n").getBytes();
                    try {
                        //获取文件内容
                        //二进制格式
                        //并转化为字符串形式
                        //Context context = null;
                        //FileInputStream fileStream = this.openFileInput(filename);
                        FileInputStream fileStream = new FileInputStream(filename);

                        //报文总长度
                        //假设转换后字符串为file

                        //计算报文总长度
                        long fileStreamLength = fileStream.available();
                        long length = head.length + tail.length + fileStreamLength;
                        //发送总长度
                        con.setRequestProperty("Content-Length", String.valueOf(length));
                        //设置输入输出流
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        //获取输出流
                        DataOutputStream out2 = new DataOutputStream(con.getOutputStream());
                        //发送报文头
                        out2.write(head);
                        //发送文件内容
                        int count;
                        //System.out.println("filelength:" + fileStreamLength);
                        byte[] temp = new byte[(int) fileStreamLength];
                        while ((count = fileStream.read(temp)) > 0) {
                            out2.write(temp, 0, count);
                        }
                        //发送报文结尾
                        out2.write(tail);
                        //刷新
                        out2.flush();
                        //获取响应流
                        //String result2 = ReadAsString(con.getInputStream(),"UTF-8");
                        if (con.getResponseCode() == 200) {
                            BufferedReader result2 = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                            fileStream.close();
                            System.out.println("result:" + result2);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();
        return null;
    }


}
