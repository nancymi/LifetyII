package com.example.nancy.safety.Image;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Nancy on 2014/10/17.
 * 保存用户头像
 */
public class saveBitmap {
    public static void saveBitmap(Bitmap bm, String picName) {
       // Log.e(TAG, "保存图片");
        File f = new File("/storage/emulated/0/", picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);

            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            //Log.i(TAG, "已经保存");
        } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
    // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
