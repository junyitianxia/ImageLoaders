package com.sunxiulei.imageloader.cache;

import android.graphics.Bitmap;

/**
 * 作者：sunxiulei on 2019/3/4 11:59
 * 邮箱：649238059@qq.com
 * 双缓存
 */

public class DoubleCache implements ImageCache{
    MemoryCache mImageCache = new MemoryCache();
    DiskCache mDiskCache = new DiskCache();
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap==null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mDiskCache.put(url,bitmap);
        mImageCache.put(url,bitmap);
    }


}
