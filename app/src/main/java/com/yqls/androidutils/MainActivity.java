package com.yqls.androidutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        imgFileUtils.imgToGallery(this,"测试",convertImg.drawable2Bitmap(getResources().getDrawable(R.drawable.ic_img)));

        DisplayUtils.printDisplayMetrics(this);
    }
}
