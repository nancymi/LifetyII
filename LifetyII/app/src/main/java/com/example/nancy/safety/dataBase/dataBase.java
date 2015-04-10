package com.example.nancy.safety.dataBase;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Nancy on 2014/10/15.
 * 閸掓稑缂撻弫鐗堝祦鎼存搫绱濋幓鎺戝弳閺佺増宓�
 */
public class dataBase {

    private static final int OK = 1;
    private static final int ERROR = 0;
    //濞夈劍鍓伴敍渚婄磼閿涗礁婀�灞惧灇娑擄拷顐奸弫鐗堝祦娣囨繂鐡ㄩ崥搴＄箑妞ゅ锟介崙鍝勫晙闁插秵鏌婇惂璇插弳閺佺増宓侀敍灞芥儊閸掓瑧鈻兼惔蹇庣窗娑擄拷娲挎径鍕躬SQLiteException闁插矉绱濈粙瀣碍閹笛嗩攽婢惰精瑙�
    public static SQLiteDatabase createDatabase(String dbname) {
        String path1 = Environment.getExternalStorageDirectory() + "/Safety/";
        File filesDir = new File(path1);
        if (!filesDir.exists())
            filesDir.mkdirs();
        String path2 = Environment.getExternalStorageDirectory() + "/Safety/databases/";
        File filesDir2 = new File(path2);
        if (!filesDir2.exists())
            filesDir2.mkdirs();
        SQLiteDatabase db;
        //System.out.println(path2 + dbname + ".db3");
        db = SQLiteDatabase.openOrCreateDatabase(path2 + dbname + ".db3", null);
        return db;
    }

    public static void insertUserData (SQLiteDatabase db, String userid, String username) {
        try {
            db.execSQL("insert into user values(?,?)", new String[]{userid, username});
        } catch (SQLiteException e) {
            db.execSQL("create table user ("
                   + " userid char(20) primary key,"
                   + "username char(50))");
            db.execSQL("insert into user values(?,?)", new String[]{userid, username});
        }
    }

    public static void insertTabData (SQLiteDatabase db, String userid, String tabid, String tabname) {
        try {
            db.execSQL("insert into tab values(?,?,?)", new String[]{userid, tabid, tabname});
        } catch (SQLiteException e) {
            db.execSQL("create table tab (" +
                    "userid char(20)," +
                    "tabid char(20) primary key," +
                    "tabname char(50)," +
                    "foreign key (userid) references user(userid))"
                    );
            db.execSQL("insert into tab values(?,?,?)", new String[]{userid, tabid, tabname});
        }
    }

    public static void insertFoodData(SQLiteDatabase db, String foodid, String foodname, String foodInfo, String date) throws SQLiteException{
        try {
            db.execSQL("insert into food values(?,?,?,?)",new String[] {foodid, foodname, foodInfo, date});
        } catch (SQLiteException e) {
        	System.out.println("InsertFoodData = " + e.getMessage().toString());
        	Log.d("tableMessage", e.getMessage());
            db.execSQL("create table food (" +
                    "foodid char(20) primary key," +
                    "foodname char(20)," +
                    "foodInfo char(200)," +
                    "date char(20)" +
                    ");");
            db.execSQL("insert into food values(?,?,?,?)",new String[] {foodid, foodname, foodInfo, date});
        }

    }

    public static void insertFoodPicData(SQLiteDatabase db, String foodid, String foodPic) throws SQLiteException{
        try {
            db.execSQL("insert into foodPic values('"+ foodid +"','" + foodPic + "');");
        } catch (SQLiteException e) {
            db.execSQL("create table foodPic (" +
                    "foodid char(20) primary key," +
                    "foodpic char(100)," +
                    "foreign key (foodid) references food(foodid)" +
                    ");");
            db.execSQL("insert into foodPic values('"+ foodid +"','" + foodPic + "');");
        }
    }

    public static void insertEventData (SQLiteDatabase db, String userid, String day, String time, String event) {
        try {
            db.execSQL("insert into event values(?,?,?,?)", new String[]{userid, day, time, event});
        } catch (SQLiteException e) {
            db.execSQL("create table event (" +
                    "userid char(20) primary key," +
                    "day char(10)," +
                    "time char(10)," +
                    "event char(20)," +
                    "foreign key(userid) references user(userid)" +
                    ")");
            db.execSQL("insert into event values(?,?,?,?)", new String[]{userid, day, time, event});
        }
    }

}
