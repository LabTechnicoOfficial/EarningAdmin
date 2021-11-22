package com.example.earningadmin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.earningadmin.Model.UploadVideo.Server_response;
import com.example.earningadmin.Model.UploadVideo.VideoRepositories;
import com.example.earningadmin.Model.UploadVideo.Video_response;

import java.util.List;

public class VideoViewModel extends AndroidViewModel {
    public VideoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Server_response> uploadVideo(String path) {
        return VideoRepositories.getInstance().uploadVideo(path);
    }

    public LiveData<List<Video_response>> getVideo() {
        return VideoRepositories.getInstance().fetchVideo();
    }

    public LiveData<Server_response> deleteVideo(String id) {
        return VideoRepositories.getInstance().deleteVideo(id);
    }
}
