package com.example.earningadmin.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.earningadmin.Adapter.Video_adapter;
import com.example.earningadmin.Model.UploadVideo.Server_response;
import com.example.earningadmin.Model.UploadVideo.Video_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.VideoViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Video_fragment extends Fragment implements Video_adapter.OnItemDelete, Video_adapter.OnItemPlay {

    ImageView backButton;
    RecyclerView videoView;
    FloatingActionButton addVideoButton;
    VideoViewModel videoViewModel;
    private List<Video_response> videoList;
    private Video_adapter adapter;
    Dialog loaderDialog, videoAlert;
    VideoView videoPlayerView;
    private int mCurrentPosition = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.fade_in,  // enter
                        R.anim.fade_out // popExit
                ).replace(R.id.frame_container, new Add_video_fragment()).addToBackStack(null).commit();
            }
        });

        fetch_videos();
    }

    private void fetch_videos() {

        videoViewModel.getVideo().observe(getViewLifecycleOwner(), new Observer<List<Video_response>>() {
            @Override
            public void onChanged(List<Video_response> video_responses) {
                videoList = new ArrayList<>();
                videoList = video_responses;
                adapter = new Video_adapter(videoList);
                adapter.setOnClickListener(Video_fragment.this::OnItemDelete, Video_fragment.this::OnItemPlay);
                videoView.setAdapter(adapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.video_fragment, container, false);

        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.fade_in,  // enter
                R.anim.fade_out // popExit
        ).replace(R.id.frame_container, new Main_fragment()).commit());

        videoView = (RecyclerView) view.findViewById(R.id.videoViewID);
        videoView.setHasFixedSize(true);
        videoView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        addVideoButton = (FloatingActionButton) view.findViewById(R.id.addVideoButtonID);

        loaderDialog = new Dialog(getActivity());
        loaderDialog.setContentView(R.layout.loader_alert);
        loaderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loaderDialog.setCancelable(false);


        return view;
    }

    @Override
    public void OnItemDelete(int position) {
        Video_response response = videoList.get(position);
        String id = response.getId();

        Dialog deleteDialog = new Dialog(getActivity());
        deleteDialog.setContentView(R.layout.delete_alert);
        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deleteDialog.setCancelable(false);

        TextView yesButton = deleteDialog.findViewById(R.id.yesButtonID);
        TextView noButton = deleteDialog.findViewById(R.id.noButtonID);

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaderDialog.show();
                videoViewModel.deleteVideo(id).observe(getViewLifecycleOwner(), new Observer<Server_response>() {
                    @Override
                    public void onChanged(Server_response server_response) {
                        String message = server_response.getMessage();
                        loaderDialog.dismiss();

                        if (message.equals("success")) {
                            deleteDialog.dismiss();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            fetch_videos();
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    @Override
    public void OnItemPlay(int position) {
        Video_response response = videoList.get(position);

        String path = response.getUrl();
        Uri videoUri = Uri.parse(path);

        videoAlert = new Dialog(getActivity());
        videoAlert.setContentView(R.layout.video_player_alert);
        videoAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        videoAlert.setCancelable(false);
        videoAlert.show();

        ImageView closeButton = videoAlert.findViewById(R.id.closeButtonID);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoAlert.dismiss();
            }
        });

        videoPlayerView = videoAlert.findViewById(R.id.videoViewID);

        initializePlayer(videoUri);
    }

    private void initializePlayer(Uri uri) {

        videoPlayerView.setVisibility(View.VISIBLE);
        if (uri != null) {
            videoPlayerView.setVideoURI(uri);
        }


        videoPlayerView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                if (mCurrentPosition > 0) {
                    videoPlayerView.seekTo(mCurrentPosition);
                } else {
                    // Skipping to 1 shows the first frame of the video.
                    videoPlayerView.seekTo(1);
                }
                videoPlayerView.start();

                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                        MediaController controller = new MediaController(getActivity());
                        videoPlayerView.setMediaController(controller);
                        controller.setAnchorView(videoPlayerView);
                    }
                });

                // Hide buffering message.
                //mBufferingTextView.setVisibility(VideoView.INVISIBLE);


            }
        });
        // Listener for onCompletion() event (runs after media has finished
        // playing).
        videoPlayerView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        //Toast.makeText(getActivity(), "The End", Toast.LENGTH_SHORT).show();

                        videoAlert.dismiss();
                        // Return the video position to the start.
                        videoPlayerView.seekTo(0);
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();

        // Media playback takes a lot of resources, so everything should be
        // stopped and released at this time.
        //releasePlayer();
    }

    private void releasePlayer() {
        videoPlayerView.stopPlayback();
    }
}