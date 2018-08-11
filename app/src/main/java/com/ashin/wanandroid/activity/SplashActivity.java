package com.ashin.wanandroid.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ashin.wanandroid.R;

/**
 * author：ashin
 * create time：2018/8/10 0010
 * desc: SplashActivity
 */
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SystemClock.sleep(2000);

        startActivity(new Intent(this,MainActivity.class));
        finish();

    }
}
