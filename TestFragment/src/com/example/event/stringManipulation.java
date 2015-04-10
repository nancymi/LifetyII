package com.example.event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nancy on 2014/10/22.
 */
//瀛楃涓插鐞�
public class stringManipulation {
    //protected static String[] userid;
    //protected static String[] plancontent;
    protected static String[][] answer;
    private static int num;
    public static String[][] stringManipulation(JSONObject events) throws JSONException {
        int i;
        String result = events.getString("result");
        num = Integer.getInteger(result);
            try {
                JSONArray jsonArray = events.getJSONArray("message");
                for (i = 0; i < num; i++) {
                    JSONObject json = (JSONObject) jsonArray.opt(i);
                    answer[i][0] = json.getString("userid");
                    answer[i][1] = json.getString("plancontent");
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        return answer;
    }
}
