package com.sunxiulei.imageloader.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 作者：sunxiulei on 2019/3/5 08:42
 * 邮箱：649238059@qq.com
 * 统一关闭
 */

public class CloseUtil {

    //通过统一的关闭接口关闭 一个单利实现
    private CloseUtil(){}
    //所以的关闭都是通过这个接口实现那么我们关闭的时候将子类传过来自然就关闭了
    public static void closeQuietly(Closeable closeable){
        if (closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
