package com.example.nancy.safety.event;

/**
 * Created by Nancy on 2014/10/23.
 */
public class Event {
    public static String time;
    public static String content;
    public static String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
