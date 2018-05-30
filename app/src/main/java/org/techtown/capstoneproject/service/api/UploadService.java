package org.techtown.capstoneproject.service.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by hahav on 2018-05-24.
 */

public interface UploadService {
    @Multipart
    @POST("catchme/uploadimage/imageUpload.do")
    Call<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);
}
