package org.techtown.capstoneproject;

/*
 * Created by ShimPiggy on 2018-05-04.
 * Modified by ShimPiggy on 2018-05-23. -image
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.kakao.auth.Session;

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
           /* //facebook 로그인상태
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

            //카카오 로그인 여부
            if (!Session.getCurrentSession().isOpened() || !isLoggedIn)
                startActivity(new Intent(getApplication(), LoginActivity.class)); // after loading, go LoginActivity
            else*/
                startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난후 이동할 Activity

            SplashActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
        }
    }
}//SplashActivity
