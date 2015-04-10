package com.example.event;

import android.database.Cursor;

/**
 * Created by Nancy on 2014/10/23.
 */
public class eventPackage {
    public static Event[] event;
    public static void eventBack(String events) {
        int a,b,i = 0;
        while (!(events.equals(""))) {
            a = events.indexOf("-");
            b = events.indexOf("|");
            eventPackage.event[i].setTime(events.substring(0,a));
            eventPackage.event[i].setContent(events.substring(a + 1, b));
            events = events.substring(b+1,events.length());
            ++i;
        }
        //return event;
    }
}
