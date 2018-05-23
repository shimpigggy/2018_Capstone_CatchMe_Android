package org.techtown.capstoneproject.server;

import org.json.JSONObject;
import org.techtown.capstoneproject.tab.tab4_email.InquiryDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/*
 * Created by ShimPiggy on 2018-05-21.
 */

public interface ApiService_Email {
    public static final String PORT = "8080";
    public static final String IP = "192.168.137.1:" + PORT;

    // public static final String API_URL = "http://"+IP+"/catchme/";
    public static final String API_URL = "http://192.168.137.1:8080/catchme/";

    /*
    @GET("api주소")
     Call<ResponseBody>함수이름(@Query("변수이름")int 안드로이드에서 보낼 변수);

          @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

            @Path : 동적 바인딩
            @Query : 원하는 것 받기
            @Body : Http request body
     */

    @Headers(
            {
                    "Accept: application/json",
                    "Content-Type: application/json; charset=utf-8"
            })
    @POST("sendRequest")
    Call<JSONObject> getPostCommentStr(@Body InquiryDTO inquiryDTO);
}
