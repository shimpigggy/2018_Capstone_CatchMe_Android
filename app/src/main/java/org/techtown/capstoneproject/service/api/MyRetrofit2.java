package org.techtown.capstoneproject.service.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hahav on 2018-05-24.
 */

public class MyRetrofit2 {

    public static final String URL = "http://192.168.137.1:8080/";

    static Retrofit mRetrofit;

    public static Retrofit getRetrofit2() {
        if (mRetrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            mRetrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()).build();
        }
        return mRetrofit;
    }

}
