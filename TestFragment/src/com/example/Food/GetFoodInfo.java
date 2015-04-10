package com.example.Food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.dataBase.bitData;
import com.example.dataBase.dataBase;
import com.example.urlConnect.URLConnect;


/**
 * Created by Nancy on 2014/10/19.
 * 閼惧嘲褰囧В蹇旀）妞嬬喓澧块崝銊︼拷
 */
public class GetFoodInfo {
    static SharedPreferences sharedPreferences;
    static String msg;
    static String message;
    static String answer;
    static SharedPreferences.Editor editor;
    public static Bitmap[] bitmaps = new Bitmap[3];
    public static String[] foodids = new String[3];
    public static SQLiteDatabase db;


      public static void getFoodInfo(Context context) {

          //KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(context.KEYGUARD_SERVICE);
          //KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("");
          //keyguardLock.disableKeyguard();

          final String infoURL = "http://1.assistantoflife.sinaapp.com/GetFoodInfo.php";
          final String picURL = "http://1.assistantoflife.sinaapp.com/GetFoodPic.php";

          sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
          //editor = sharedPreferences.edit();
          //并入总工程时需要修改userid，此处仅作test使用
          String userid = sharedPreferences.getString("userid", "test1");
          //System.out.println(userid);
          final List<NameValuePair> params = new ArrayList<NameValuePair>();
          params.add(new BasicNameValuePair("userid", userid));
          new Thread() {
              public void run() {
                  msg = URLConnect.URLConnect(infoURL, params);
                  if (msg == "Cannot connect!" || msg == "......") {
                      System.out.println("msg:" + msg);
                  } else {
                      String foodid1 = null;
                      String food1info = null;
                      String foodid2 = null;
                      String food2info = null;
                      String foodid3 = null;
                      String food3info = null;
                      String foodname1 = null;
                      String foodname2 = null;
                      String foodname3 = null;
                      try {
                          JSONObject food = new JSONObject(msg).getJSONObject("message");
                          try {
                        	  getFoodMsg.getFoodMsg(food);
                              String[][] foods = getFoodMsg.foods;
                              foodid1 = foods[0][0];
                              foodname1 = foods[0][1];
                              food1info = foods[0][2];
                              foodid2 = foods[1][0];
                              foodname2 = foods[1][1];
                              food2info = foods[1][2];
                              foodid3 = foods[2][0];
                              foodname3 = foods[2][1];
                              food3info = foods[2][2];

                              GetFoodInfo.foodids[0] = foodid1;
                              GetFoodInfo.foodids[1] = foodid2;
                              GetFoodInfo.foodids[2] = foodid3;
                              
                              Log.d("test234",GetFoodInfo.foodids[0]);

                          } catch (JSONException e) {
                        	  Log.d("test432",GetFoodInfo.foodids[0]);
                              e.printStackTrace();
                              System.out.println("e in :" + e.getMessage().toString());
                          }
                      } catch (JSONException e) {
                    	  Log.d("test444",GetFoodInfo.foodids[0]);
                          e.printStackTrace();
                          System.out.println("e out :" + e.getMessage().toString());
                      }

                      GetFoodInfo.db = dataBase.createDatabase("user");

                      Calendar c = Calendar.getInstance();
                      //测试，先不加1
                      //int year = c.get(Calendar.YEAR) + 1;
                      int year = c.get(Calendar.YEAR);
                      int month = c.get(Calendar.MONTH);
                      int date = c.get(Calendar.DATE);
                      String day = year + "-" + month + "-" + date;
                      
                      Log.d("test","insertData!@!!!!!!");
                      
                      dataBase.insertFoodData(db,foodid1,foodname1,food1info,day);
                      dataBase.insertFoodData(db,foodid2,foodname2,food2info,day);
                      dataBase.insertFoodData(db,foodid3,foodname3,food3info,day);
                      
                      Log.d("test","f8uck");
                      
                      new Thread() {
                          public void run() {
                              while (GetFoodInfo.foodids[0] == null);
                              getFood.getFoodPic(picURL,GetFoodInfo.foodids[0]);
                        	  Log.d("test5","inserteafwef");
                        	  while (getFood.bitmap == null);
                        	  Log.d("test6","getfoodmap");
                              GetFoodInfo.bitmaps[0] = getFood.bitmap;
                              while (GetFoodInfo.bitmaps[0] == null);
                              byte[] bmp1 = bitData.createData(GetFoodInfo.bitmaps[0]);
                              dataBase.insertFoodPicData(db, foodids[0], bmp1.toString());
                          }
                      }.start();

                      /*
                      new Thread() {
                          public void run() {
                              while (GetFoodInfo.foodids[1] == null);
                              getFood.getFoodPic(picURL,GetFoodInfo.foodids[1]);
                              new Thread (){
                                  public void run() {
                                      GetFoodInfo.bitmaps[1] = getFood.bitmap;
                                      // System.out.println("bitmaps[0] = " + bitmaps[0]);
                                      //System.out.println("bitmap1:" + bitmaps[0]);
                                      while (GetFoodInfo.bitmaps[1] == null);
                                      byte[] bmp1 = bitData.createData(GetFoodInfo.bitmaps[1]);
                                      dataBase.insertFoodPicData(db, foodids[1], bmp1.toString());
                                  }
                              }.start();
                          }
                      }.start();

                      new Thread() {
                          public void run() {
                              while (GetFoodInfo.foodids[2] == null);
                              getFood.getFoodPic(picURL,GetFoodInfo.foodids[2]);
                              new Thread (){
                                  public void run() {
                                      GetFoodInfo.bitmaps[2] = getFood.bitmap;
                                      // System.out.println("bitmaps[0] = " + bitmaps[0]);
                                      //System.out.println("bitmap1:" + bitmaps[0]);
                                      while (GetFoodInfo.bitmaps[2] == null);
                                      byte[] bmp1 = bitData.createData(GetFoodInfo.bitmaps[2]);
                                      dataBase.insertFoodPicData(db, foodids[0], bmp1.toString());
                                  }
                              }.start();
                          }
                      }.start();
                      
                      */

                  }
              }
          }.start();

      }
}