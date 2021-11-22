package com.example.earningadmin.Adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earningadmin.Model.UploadVideo.Video_response;
import com.example.earningadmin.R;

import java.util.List;

public class Video_adapter extends RecyclerView.Adapter<Video_adapter.AppViewHolder> {

    private List<Video_response> videoList;
    private int mCurrentPosition = 0;

    public Video_adapter(List<Video_response> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_video, parent, false);
        return new Video_adapter.AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        Video_response response = videoList.get(position);
        //holder.videoView.setVideoPath(response.url);

        /*Bitmap thumb = ThumbnailUtils.createVideoThumbnail(response.url, MediaStore.Images.Thumbnails.MINI_KIND);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(thumb);
        holder.videoView.setBackgroundDrawable(bitmapDrawable);
        holder.videoView.seekTo(100);*/
        //holder.videoView.setVideoURI(Uri.parse(response.getUrl()));
        Uri uri = Uri.parse(response.getUrl());

        if (uri != null) {
            holder.videoView.setVideoURI(uri);
            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {

                    if (mCurrentPosition > 0) {
                        holder.videoView.seekTo(mCurrentPosition);
                    } else {
                        // Skipping to 1 shows the first frame of the video.
                        holder.videoView.seekTo(100);
                    }
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    Video_adapter.OnItemDelete deleteListener;
    Video_adapter.OnItemPlay playListener;

    public interface OnItemDelete {
        void OnItemDelete(int position);
    }

    public interface OnItemPlay {
        void OnItemPlay(int position);
    }

    public void setOnClickListener(Video_adapter.OnItemDelete deleteListener, Video_adapter.OnItemPlay playListener) {
        this.deleteListener = deleteListener;
        this.playListener = playListener;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        ImageView playButton, deleteButton;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            videoView = (VideoView) itemView.findViewById(R.id.videoViewID);
            //videoView.seekTo(1);
            playButton = itemView.findViewById(R.id.playButtonID);
            deleteButton = itemView.findViewById(R.id.deleteButtonID);

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (playListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            playListener.OnItemPlay(position);
                        }
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deleteListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            deleteListener.OnItemDelete(position);
                        }
                    }
                }
            });
        }
    }

}
