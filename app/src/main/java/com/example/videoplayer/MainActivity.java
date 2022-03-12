package com.example.videoplayer;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.ui.AppBarConfiguration;

import com.bumptech.glide.Glide;
import com.example.videoplayer.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.HashMap;

//import bg.devlabs.fullscreenvideoview.FullscreenVideoView;
//import bg.devlabs.fullscreenvideoview.listener.OnVideoPreparedListener;
//import wseemann.media.FFmpegMediaMetadataRetriever;

public class MainActivity extends AppCompatActivity {
    ImageView videoThumbnail;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
//        videoThumbnail = findViewById(R.id.videoThumbnail);
//        FFmpegMediaMetadataRetriever med = new FFmpegMediaMetadataRetriever();
//        med.setDataSource(url);
//        Bitmap bmp = med.getFrameAtTime(19 * 1000 * 1000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
//        videoThumbnail.setImageBitmap(bmp);
//        FullscreenVideoView fullscreenVideoView = findViewById(R.id.fullscreenVideoView);
//        fullscreenVideoView.addOnVideoPreparedListener(new OnVideoPreparedListener() {
//            @Override
//            public void onPrepared() {
//                videoThumbnail.setVisibility(View.GONE);
//
//            }
//        });+
//        fullscreenVideoView.videoUrl(url)
//                .playDrawable(R.drawable.ic_play).enableAutoStart()
//                .pauseDrawable(R.drawable.ic_pause)
//                .fastForwardDrawable(R.drawable.ic_baseline_forward_10_24)
//                .rewindDrawable(R.drawable.ic_rew)
//                .enterFullscreenDrawable(R.drawable.ic_fullscreen)
//                .exitFullscreenDrawable(R.drawable.ic_fullscreenexit).addSeekBackwardButton().addPlaybackSpeedButton()
//                .addSeekForwardButton().play();


    }


}