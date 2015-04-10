package com.example.nancy.safety.event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nancy on 2014/10/22.
 */
//字符串处理
public class stringManipulation {
    //protected static String[] userid;
    //protected static String[] plancontent;
    public static String[][] answer = new String[10][2];
    public static void stringManipulation(JSONObject events, int num) throws JSONException {
        int i;
            try {
                JSONArray jsonArray = events.getJSONArray("plan");
                for (i = 0; i < num; i++) {
                     JSONObject json = (JSONObject) jsonArray.opt(i);
                    //System.out.println(json.getString("userid"));
                     answer[i][0] = new String();
                     stringManipulation.answer[i][0] = json.getString("userid");
                     stringManipulation.answer[i][1] = json.getString("plancontent");

                }
            }catch(JSONException e){
                e.printStackTrace();
                System.out.println("JSONstring:" + e.getMessage().toString());
            }
        //return answer;
    }
}
