package org.techtown.capstoneproject.service.api;

import org.json.JSONObject;
import org.techtown.capstoneproject.service.dto.InquiryDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServiceEmail {
    @Headers(
            {
                    "Accept: application/json",
                    "Content-Type: application/json; charset=utf-8"
            })
    @POST("sendRequest")
    Call<JSONObject> getPostCommentStr(@Body InquiryDTO inquiryDTO);
}
