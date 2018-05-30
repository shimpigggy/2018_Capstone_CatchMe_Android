package org.techtown.capstoneproject.service.api;

import org.json.JSONObject;
import org.techtown.capstoneproject.service.dto.InquiryDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/*
 * Created by ShimPiggy on 2018-05-21.
 */

public interface ApiServiceEmail {
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
