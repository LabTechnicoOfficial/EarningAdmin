package com.example.earningadmin.View.Fragment;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.earningadmin.Model.UploadVideo.Server_response;
import com.example.earningadmin.R;
import com.example.earningadmin.ViewModel.VideoViewModel;

import java.util.concurrent.TimeUnit;

public class Add_video_fragment extends Fragment {
    ImageView backButton;
    TextView noVideoText, durationText;
    VideoView videoView;
    AppCompatButton pickButton, uploadButton;
    final int REQUEST_CODE = 1;
    Uri video;
    String videoPath;
    private int mCurrentPosition = 0;
    MediaController controller;
    Dialog loaderDialog;
    VideoViewModel uploadVideoViewModel;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == REQUEST_CODE) {
                if (data != null) {
                    video = data.getData();
                    //videoPath = video.getPath();
                    noVideoText.setVisibility(View.GONE);
                    videoPath = getPath(video);

                    initializePlayer(video);

                    MediaPlayer mp = MediaPlayer.create(getActivity(), Uri.parse(videoPath));
                    int duration = mp.getDuration();
                    mp.release();
                    /*convert millis to appropriate time*/
                    String time = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(duration), TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
                    durationText.setText(time);
                }
            }
        } else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(getActivity(), "Sorry, there was an error!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_video_fragment, container, false);

        uploadVideoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.fade_in,  // enter
                R.anim.fade_out // popExit
        ).replace(R.id.frame_container, new Video_fragment()).commit());

        noVideoText = (TextView) view.findViewById(R.id.noVideoTextID);
        durationText = (TextView) view.findViewById(R.id.durationTextID);
        videoView = (VideoView) view.findViewById(R.id.videoViewID);
        pickButton = (AppCompatButton) view.findViewById(R.id.pickButtonID);
        uploadButton = (AppCompatButton) view.findViewById(R.id.uploadButtonID);

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickVideoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                //pickVideoIntent.setType("video/*");
                startActivityForResult(pickVideoIntent, REQUEST_CODE);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video != null) {
                    uploadFile();
                } else {
                    Toast.makeText(getActivity(), "Please select a video", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loaderDialog = new Dialog(getActivity());
        loaderDialog.setContentView(R.layout.loader_alert);
        loaderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loaderDialog.setCancelable(false);

        return view;
    }

    @SuppressLint("Range")
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            //Log.d("jdskj", String.valueOf(cursor.getString(column_index)));
            return cursor.getString(column_index);
        } else {
            //Log.d("jdskj", "1");
            return null;
        }
    }

    private void initializePlayer(Uri uri) {

        videoView.setVisibility(View.VISIBLE);
        if (uri != null) {
            videoView.setVideoURI(uri);
        }


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                if (mCurrentPosition > 0) {
                    videoView.seekTo(mCurrentPosition);
                } else {
                    // Skipping to 1 shows the first frame of the video.
                    videoView.seekTo(1);
                }
                //videoView.start();

                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                        controller = new MediaController(getActivity());
                        videoView.setMediaController(controller);
                        controller.setAnchorView(videoView);
                    }
                });

                // Hide buffering message.
                //mBufferingTextView.setVisibility(VideoView.INVISIBLE);


            }
        });
        // Listener for onCompletion() event (runs after media has finished
        // playing).
        videoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        //Toast.makeText(getActivity(), "The End", Toast.LENGTH_SHORT).show();

                        // Return the video position to the start.
                        videoView.seekTo(0);
                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();

        // Media playback takes a lot of resources, so everything should be
        // stopped and released at this time.
        releasePlayer();
    }

    private void releasePlayer() {
        videoView.stopPlayback();
    }

    private void uploadFile() {
        if (video == null || video.equals("")) {
            Toast.makeText(getActivity(), "please select an image ", Toast.LENGTH_LONG).show();
            return;
        } else {

            loaderDialog.show();
            uploadVideoViewModel.uploadVideo(videoPath).observe(this, new Observer<Server_response>() {
                @Override
                public void onChanged(Server_response server_response) {
                    loaderDialog.dismiss();
                    String message = server_response.getMessage();
                    //Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    if (message.equals("success")) {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.fade_in,  // enter
                                R.anim.fade_out // popExit
                        ).replace(R.id.frame_container, new Video_fragment()).commit();

                    } else {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                        //Toast.makeText(getActivity(), "Upload failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}