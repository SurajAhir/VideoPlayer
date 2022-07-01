package com.example.videoplayer.VideoViewWithPause;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.videoplayer.R;

public class VideoPlayerWithPause extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    int stopPosition;
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4";
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player_with_pause);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoView=findViewById(R.id.VideoViewWithPause);
        checkStoragePermission();
        fetchVideo();
        findViewById(R.id.VideoViewWithPause_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    findViewById(R.id.VideoViewWithPause_tv).setBackgroundResource(0);
                    videoView.seekTo(stopPosition);
                    mediaPlayer.start();
                }else {
                    stopPosition = videoView.getCurrentPosition();//stopPosition is an int
                    findViewById(R.id.VideoViewWithPause_tv).setBackgroundResource(R.drawable.ic_pause_24);
                    mediaPlayer.pause();
                }
            }
        });
    }

    private void checkStoragePermission() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            fetchVideo();
        }
        else{
            ActivityCompat.requestPermissions(VideoPlayerWithPause.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1002);
        }
    }

    private void fetchVideo() {
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pause_video_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.pause_video_download:{
              newDownload(url);
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void newDownload(String url) {
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        String title= URLUtil.guessFileName(url,null,null);
        request.setTitle(title);
        request.setDescription("Downloading File please wait...");
        String cookie= CookieManager.getInstance().getCookie(url);
        request.addRequestHeader("cookie",cookie);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);
        DownloadManager downloadManager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
        Toast.makeText(VideoPlayerWithPause.this,"Downloading Started...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1002&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show();
            fetchVideo();
        }else{
            Toast.makeText(this,"Permission required!",Toast.LENGTH_SHORT).show();
            checkStoragePermission();
        }

    }
}