package com.sunxiulei.imageloader.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sunxiulei.imageloader.utils.CloseUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 作者：sunxiulei on 2019/3/4 11:25
 * 邮箱：649238059@qq.com
 * 硬盘缓存
 */

public class DiskCache implements ImageCache{
    static String cacheDir = "sdcard/cache/";
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                CloseUtil.closeQuietly(fileOutputStream);
            }
        }
    }


}
