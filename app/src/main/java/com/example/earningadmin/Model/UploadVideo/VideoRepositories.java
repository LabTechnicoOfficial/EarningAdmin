package com.example.earningadmin.Model.UploadVideo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.earningadmin.Model.ApiUtilize;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoRepositories {

    Video_api upload_api;
    private MutableLiveData<Server_response> data;
    private static VideoRepositories uploadRepositories;
    private MutableLiveData<List<Video_response>> videoData;

    private VideoRepositories() {
        upload_api = ApiUtilize.upload_api();
        data = new MutableLiveData<>();
        videoData = new MutableLiveData<>();
    }

    public synchronized static VideoRepositories getInstance() {
        if (uploadRepositories == null)
            return new VideoRepositories();
        return uploadRepositories;
    }

    public MutableLiveData<Server_response> uploadVideo(String path) {

        Map<String, RequestBody> map = new HashMap<>();
        File file = new File(path);
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Call<Server_response> call = upload_api.uploadVideo(fileToUpload);
        call.enqueue(new Callback<Server_response>() {
            @Override
            public void onResponse(Call<Server_response> call, Response<Server_response> response) {
                if (response.isSuccessful()) {
                    Log.d("errrorxxx", "success");
                    data.postValue(response.body());
                } else {
                    Log.d("errrorxxx", "else success");
                    Server_response response1 = new Server_response();
                    response1.setMessage("invalid");
                    data.postValue(response1);
                }
            }

            @Override
            public void onFailure(Call<Server_response> call, Throwable t) {
                Server_response response = new Server_response();
                response.setMessage(t.getMessage());
                data.postValue(response);

                Log.d("errrorxxx", t.getMessage());
            }
        });

        return data;
    }

    public MutableLiveData<List<Video_response>> fetchVideo() {

        Call<List<Video_response>> call = upload_api.getVideo();
        call.enqueue(new Callback<List<Video_response>>() {
            @Override
            public void onResponse(Call<List<Video_response>> call, Response<List<Video_response>> response) {
                if (response.isSuccessful()) {
                    videoData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Video_response>> call, Throwable t) {
                List<Video_response> response = new ArrayList<>();
                videoData.postValue(response);

                Log.d("errrorxxx", t.getMessage());
            }
        });

        return videoData;
    }

    public MutableLiveData<Server_response> deleteVideo(String id) {

        Call<Server_response> call = upload_api.deleteVideo(id);
        call.enqueue(new Callback<Server_response>() {
            @Override
            public void onResponse(Call<Server_response> call, Response<Server_response> response) {
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Server_response> call, Throwable t) {
                Server_response response = new Server_response();
                response.setMessage(t.getMessage());
                data.postValue(response);
            }
        });

        return data;
    }
}
