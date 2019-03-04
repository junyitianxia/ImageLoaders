package com.sunxiulei.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        ImageLoader imageLoader = new ImageLoader();
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551678389044&di=3fbb989d4c2b533a89aaeff1c44ef750&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201410%2F02%2F20141002100803_ndjUZ.jpeg";
        imageLoader.displayImage(url,iv);
    }
}
