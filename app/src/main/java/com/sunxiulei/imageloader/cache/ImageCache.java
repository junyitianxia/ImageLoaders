package com.sunxiulei.imageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 作者：sunxiulei on 2019/3/4 10:38
 * 邮箱：649238059@qq.com
 * 缓存接口缓存相关的直接与他交互
 */

public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url ,Bitmap bitmap);
   /* LruCache<String, Bitmap> mImageCache;
    public ImageCache(){
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
    //存储图片到缓存中
    public void put (String url,Bitmap bitmap){
        mImageCache.put(url,bitmap);
    }
    //村缓存中获取图片
    public Bitmap get(String url){
       return mImageCache.get(url);
    }*/
}
