package com.sunxiulei.imageloader;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.sunxiulei.imageloader.cache.ImageCache;
import com.sunxiulei.imageloader.cache.MemoryCache;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：sunxiulei on 2019/3/4 09:48
 * 邮箱：649238059@qq.com
 * 一个图片加载框架
 */

public class ImageLoader {
    private static final String TAG = "ImageLoader";
    private ImageLoaderConfig config;
    //imageloader实例
    private static ImageLoader sImageLoader;
    //图片内存缓存
    //自己设置缓存默认只做内存缓存
    //ImageCache imageCache = new MemoryCache();

    //设置线程下载图片
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //图片加载中显示的id
    int mLoadingImageId;
    //图片加载失败是显示的图片id
    int mLoadingFailedImageId;
    //图片加载策略

    private ImageLoader() {
        //initImageCache();
    }

    public void init(ImageLoaderConfig loaderConfig){
        config = loaderConfig;

    }
//    //设置要使用的缓存
//    public void setImageCache(ImageCache cache) {
//        imageCache = cache;
//    }

    //设置加载是图片
    public void setLoadingImage(int imageId) {
        mLoadingImageId = imageId;
    }

    //设置加载失败图片
    public void setLoadingFailedImageId(int failedImageId) {
        mLoadingFailedImageId = failedImageId;
    }

    /*//设置线程数量
    public void setThreadCount(int count) {
        mExecutorService.shutdown();
        mExecutorService = null;
        mExecutorService = Executors.newFixedThreadPool(count);
    }*/

    private void submitLoadRequest(final String imageUrl, final ImageView imageView) {
        //设置加载中的图片
        imageView.setImageResource(mLoadingImageId);
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downLoadImage(imageUrl);
                Log.i(TAG, "run1: " + bitmap.getRowBytes());
                if (bitmap == null) {
                    imageView.setImageResource(mLoadingFailedImageId);
                }
                if (imageView.getTag().equals(imageUrl)) {
                    imageView.setImageBitmap(bitmap);
                }
                config.imageCache.put(imageUrl, bitmap);
            }
        });
    }

    //获取imageloader实例
    public static ImageLoader getInstance() {
        if (sImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (sImageLoader == null) {
                    sImageLoader = new ImageLoader();
                }
            }
        }
        return sImageLoader;
    }


    //显示图片
    public void displayImage(final String url, final ImageView imageView) {
        Log.i(TAG, "run0:");
        imageView.setTag(url);
        Bitmap bitmap = config.imageCache.get(url);
        if (bitmap == null) {
           /* mExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = downLoadImage(url);
                    Log.i(TAG, "run1: " + bitmap.getRowBytes());
                    if (bitmap == null) {
                        return;
                    }
                    if (imageView.getTag().equals(url)) {
                        imageView.setImageBitmap(bitmap);
                    }
                    imageCache.put(url, bitmap);

                }
            });*/
            submitLoadRequest(url, imageView);
        }
    }

    //下载图片
    private Bitmap downLoadImage(String url) {
        Bitmap bitmap = null;
        try {
            URL mUrl = new URL(url);
            final HttpURLConnection conne = (HttpURLConnection) mUrl.openConnection();
            bitmap = BitmapFactory.decodeStream(conne.getInputStream());
            conne.disconnect();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return bitmap;
    }


}
