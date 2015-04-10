package com.example.nancy.safety.event;

/**
 * Created by Nancy on 2014/10/23.
 */
public class eventPackage {
    //public static Event[] event = new Event[10];
    public static int NUM;
    public static Event[] eventBack(String events) {
        //Java的数组都是引用数组，不是实例数组，赋值前需要实例化
        Event[] event = new Event[100];
        int a,b,i = 0;
        while (!(events.equals(""))) {
            a = events.indexOf("-");
            a += events.substring(a+1,events.length()).indexOf("-")+1;
            b = events.indexOf("|");
            if (b == -1) {
                event[i] = new Event();
                event[i].setTime(events.substring(0, a));
                event[i].setContent(events.substring(a + 1, events.length()));
                System.out.println("b == -1");
                System.out.println("NUM="+eventPackage.NUM);
                eventPackage.NUM = i;
                return event;
            } else {
                //System.out.println(events.substring(0, a));
                //System.out.println(events.substring(a + 1, b));
                event[i] = new Event();
                event[i].setTime(events.substring(0, a));
                event[i].setContent(events.substring(a + 1, b));
                events = events.substring(b + 1);
                ++i;
            }
        }
        return event;
    }
}
