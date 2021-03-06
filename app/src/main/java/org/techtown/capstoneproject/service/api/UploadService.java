package org.techtown.capstoneproject.service.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadService {
    @Multipart
    @POST("uploadimage/imageUpload.do")
    Call<ResponseBody> uploadFileProduct(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);

    @Multipart
    @POST("uploadimage/getingradientimage")
    Call<ResponseBody> uploadFileChemical(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);
}
