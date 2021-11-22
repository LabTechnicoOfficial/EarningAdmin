package com.example.earningadmin.Model.UploadVideo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Video_api {

    //upload
    @Multipart
    @POST("upload_video.php")
    Call<Server_response> uploadVideo(@Part MultipartBody.Part file);

    //fetch
    @GET("getVideo_url.php")
    Call<List<Video_response>> getVideo();

    //delete
    @FormUrlEncoded
    @POST("delete_video.php")
    Call<Server_response> deleteVideo(@Field("id") String id);
}
