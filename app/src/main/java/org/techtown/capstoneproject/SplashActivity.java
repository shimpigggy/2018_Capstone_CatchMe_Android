package org.techtown.capstoneproject;

/*
 * Created by ShimPiggy on 2018-05-04.
 * Modified by ShimPiggy on 2018-05-23. -image
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import org.techtown.capstoneproject.service.login.LoginActivity;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new splashhandler(), SPLASH_DISPLAY);
    }

    private class splashhandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난후 이동할 Activity
            //startActivity(new Intent(getApplication(), LoginActivity.class)); // after loading, go LoginActivity
            SplashActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
        }
    }
}//SplashActivity
