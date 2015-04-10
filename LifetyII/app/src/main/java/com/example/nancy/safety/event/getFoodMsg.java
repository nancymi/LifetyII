package com.example.nancy.safety.event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nancy on 2014/10/27.
 */
public class getFoodMsg {
   // protected static String[][] foods;
    public static int num = 3;
    //public static String[][] foods;


    public static String[][] getFoodMsg(JSONObject food) throws JSONException {
        int i;
       // String[][] foods = new String[3][];

        //String result = food.getString("");
        //int i;

            //不知道服务器端food定义成啥了


            JSONArray jsonArray = food.getJSONArray("food");
        String[][] foods = new String[3][3];
            for (i = 0;i < num;i ++) {
                int j = i+1;
                JSONObject json = (JSONObject) jsonArray.opt(i);
                //System.out.println(json.getString("foodid" + j));
                foods[i][0] = json.getString("foodid" + j);
                foods[i][1] = json.getString("foodname" + j);
                foods[i][2] = json.getString("foodintro" + j);
               // System.out.println(i + "-" + foods[i][0]);
            }

        return foods;
    }
}
