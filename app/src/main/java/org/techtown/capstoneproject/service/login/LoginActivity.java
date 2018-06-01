package org.techtown.capstoneproject.service.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.capstoneproject.MainActivity;
import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity {
    static final int KAKAO_LOGIN = 1;
    static final int FACEBOOK_LOGIN = 2;

    private SessionCallback callback;      //콜백
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionbarRemove();
        setContentView(R.layout.activity_login);


        callbackManager = CallbackManager.Factory.create();
        LoginButton button = (LoginButton) findViewById(R.id.facebook);
        button.setReadPermissions("email");
        button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                try {
                                    String email = object.getString("email");
                                    Log.d("email", email);

                                    SharedPreferencesUtil.saveEmailPreferences(getApplicationContext(), email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
                Intent intent = new Intent();
                intent.putExtra("type", FACEBOOK_LOGIN);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onCancel() {
                setContentView(R.layout.activity_login); // 세션 연결이 실패했을때
            }

            @Override
            public void onError(FacebookException error) {
                if (error != null) {
                    Logger.e(error);
                }
                setContentView(R.layout.activity_login); // 세션 연결이 실패했을때
            }
        });

        callback = new SessionCallback();                  // 이 두개의 함수 중요함
        Session.getCurrentSession().addCallback(callback);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
                return;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.activity_login); // 세션 연결이 실패했을때
        }                                            // 로그인화면을 다시 불러옴
    }

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        final Intent intent = new Intent(this, KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    public void actionbarRemove() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
