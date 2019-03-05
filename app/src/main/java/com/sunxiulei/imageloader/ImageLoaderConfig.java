package com.sunxiulei.imageloader;

import com.sunxiulei.imageloader.cache.ImageCache;
import com.sunxiulei.imageloader.cache.MemoryCache;

/**
 * 作者：sunxiulei on 2019/3/5 10:08
 * 邮箱：649238059@qq.com
 * imageloader 的builder类吧loader的构建操作放到这里
 */

public class ImageLoaderConfig {

    // 图片缓存配置
    ImageCache imageCache = new MemoryCache();
    //线程数量
    int threadCount = Runtime.getRuntime().availableProcessors()+1;
    //单利
    private ImageLoaderConfig(){}

    //配置类builder
    public static class Builder{
        //图片缓存配置
        ImageCache imageCache = new MemoryCache();
        //加载是加载失败配置

        //加载策略

        //线程数量
        int threadCount = Runtime.getRuntime().availableProcessors()+1;
        //设置线程数
        public Builder setThreadCount (int count){
            threadCount = Math.max(1,count);
            return this;
        }

        //设置缓存
        public Builder setCache(ImageCache cache){
            imageCache = cache;
            return this;
        }
        void applyConfig(ImageLoaderConfig config){
            config.imageCache = this.imageCache;
            config.threadCount = this.threadCount;
        }
        //根据属性创建对象
        public ImageLoaderConfig create(){
            ImageLoaderConfig imageLoaderConfig = new ImageLoaderConfig();
            applyConfig(imageLoaderConfig);
            return imageLoaderConfig;
        }

    }



}
