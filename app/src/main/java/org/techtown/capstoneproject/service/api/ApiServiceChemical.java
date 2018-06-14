package org.techtown.capstoneproject.service.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceChemical {

    //화학성분
    //화학성분 이름 list
    @GET("chemical/getnamelist")
    Call<ResponseBody> getNameList(@Query("postId") String postId);

    //화학성분 하나 -> 정보 얻기
    @GET("chemical/getinfo/{name}")
    Call<ResponseBody> getInfo(@Path("name") String name);

    //제품명: 화학성분 여러개
    @GET("product/ingradient/{productname}")
    Call<ResponseBody> getProductChemicalList(@Path("productname") String productname);

    //화학성분: 화학성분 여러개
    @GET("chemical/getinfolist")
    Call<ResponseBody> getChemicalList(@Query("list") List<String> list);
}
