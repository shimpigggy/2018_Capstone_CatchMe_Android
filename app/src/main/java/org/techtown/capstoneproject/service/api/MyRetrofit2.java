package org.techtown.capstoneproject.service.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hahav on 2018-05-24.
 */

public class MyRetrofit2 {

    public static final String URL = ApiService.ADDRESS;

    static Retrofit mRetrofit;

    public static Retrofit getRetrofit2() {
        if (mRetrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS);

            httpClient.addInterceptor(logging);

            mRetrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()).build();
        }
        return mRetrofit;
    }

}
