package com.sunxiulei.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：sunxiulei on 2019/3/4 09:48
 * 邮箱：649238059@qq.com
 * 一个图片加载框架
 */

public class ImageLoader {
    //图片缓存
    LruCache<String, Bitmap> mImageCache;
    //线程池线程数量为cpu数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageLoader() {
        initImageCache();
    }

    //缓存相关内容
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

    //显示图片
    public void displayImage(final String url, final ImageView imageView) {

        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downLoadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url, bitmap);
            }
        });
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
