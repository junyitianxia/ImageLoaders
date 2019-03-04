package com.sunxiulei.imageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 作者：sunxiulei on 2019/3/4 12:13
 * 邮箱：649238059@qq.com
 * 内存缓存
 */

public class MemoryCache implements ImageCache{

      LruCache<String, Bitmap> mImageCache;
    public MemoryCache(){
        initImageCache();
    }
    private void initImageCache() {

        //计算最大可用内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        //取四分之一用作内存缓存
        final int cache = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cache) {
            //计算图片大小
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };

    }


    @Override
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url,bitmap);
    }
}
