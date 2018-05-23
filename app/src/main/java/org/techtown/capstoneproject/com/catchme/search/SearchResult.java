package org.techtown.capstoneproject.com.catchme.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.capstoneproject.R;
import org.techtown.capstoneproject.server.ApiService_Chemical;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by sj-kalin on 2018-05-07.
 * Modified by sj-kalin on 2018-05-22.
 * Modified by ShimPiggy on 2018-05-23. - receive info from intent
 */

public class SearchResult extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    RelativeLayout typeView;
    RelativeLayout typeViewResult;
    ImageView typeImage;

    //server
    Retrofit retrofit;
    ApiService_Chemical apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();

        String word = intent.getStringExtra("gradiant");
        Toast.makeText(this, word, Toast.LENGTH_SHORT).show();

        getIntentInfo(intent);


        typeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int visible = typeViewResult.getVisibility();
                if (visible == View.VISIBLE) {
                    typeViewResult.setVisibility(View.GONE);
                    typeImage.setImageResource(R.drawable.arrow_down);
                } else {
                    typeViewResult.setVisibility(View.VISIBLE);
                    typeImage.setImageResource(R.drawable.arrow_up);

                }
            }
        });
    }

    public void init() {
        intent = getIntent();

        typeView = (RelativeLayout) findViewById(R.id.type);
        typeViewResult = (RelativeLayout) findViewById(R.id.type_result);
        typeImage = (ImageView) findViewById(R.id.type_image);

        //server
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService_Chemical.API_URL).build();

        apiService = retrofit.create(ApiService_Chemical.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.alergy || v.getId() == R.id.functionfor || v.getId() == R.id.product) {
            int visible = v.getVisibility();
            if (visible == View.VISIBLE) {
                v.setVisibility(View.GONE);
            } else {
                v.setVisibility(View.VISIBLE);
            }
        }
    }//onClick

    public void getIntentInfo(Intent intent) {
        String type = intent.getStringExtra("type");

        if (type.equals("result_check")) {
            //result_check 온 경우

            Toast.makeText(this, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();

            /*Call<ResponseBody> getInfo = apiService.getInfo(intent.getStringExtra("name"));
            getInfo.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    //데이터가 받아지면 호출
                    try {
                        String result = response.body().string();
                        Log.e(">>>>>TEST", result);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }//IO
                }//onResponse

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Fail", call.toString());
                    Log.e("Fail", "Fail");
                    //데이터가 받아지는 것이 실패
                }//onFailure
            });//enqueue*/
        }//if_ result_check

    }//getIntentInfo
}//SearchResult