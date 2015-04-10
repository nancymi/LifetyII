package com.example.nancy.safety.dataBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Nancy on 2014/10/19.
 * foodPic鐨刡yte[]涓嶣itmap鐨勭浉浜掕浆鎹�
 */
public class bitData {
    /**鍒涘缓鏁版嵁**/
    public static byte[] createData(Bitmap bmp) {
        int size  = bmp.getWidth() * bmp.getHeight() * 4;
//鍒涘缓涓�釜瀛楄妭鏁扮粍杈撳嚭娴�娴佺殑澶у皬涓簊ize
        ByteArrayOutputStream baos=new ByteArrayOutputStream(size);
//璁剧疆浣嶅浘鐨勫帇缂╂牸寮忥紝璐ㄩ噺涓�00%锛屽苟鏀惧叆瀛楄妭鏁扮粍杈撳嚭娴佷腑 bitmap1.compress(Bitmap.CompressFormat.PNG, 100, baos);
//灏嗗瓧鑺傛暟缁勮緭鍑烘祦杞寲涓哄瓧鑺傛暟缁刡yte[]
        byte[] imagedata = baos.toByteArray();
        System.out.println(imagedata.toString());
        return imagedata;
    }

    public static Bitmap returnData(byte[] bmp) {
        if (bmp.length != 0) {
             return BitmapFactory.decodeByteArray(bmp, 0, bmp.length);
        } else {
             return null;
        }
    }
}
