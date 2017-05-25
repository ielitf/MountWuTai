package com.bupt.mountwutai.ui.activity.main;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseActivity;
import com.bupt.mylibrary.utils.ViewUtils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LivingActivity extends BaseActivity implements MediaController.OnControllerListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener{


    private String title;
//    private RelativeLayout livingTop;
//    private TextView livingName;
//    private ImageButton livingbutton;
    private Uri uri;
    MediaController mc;
    private VideoView mVideoView;
    private TextView downloadRateView, loadRateView;
    private ProgressBar pb;
    private FrameLayout fl_controller;
    boolean isPortrait = true;
    private int videoHeight;
    private boolean isLock = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //初始化视频
    private void initvideo() {
        mVideoView = (VideoView) findViewById(R.id.buffer);
        fl_controller = (FrameLayout) findViewById(R.id.fl_controller);
        pb = (ProgressBar) findViewById(R.id.probar);

        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mVideoView && mVideoView.isPlaying()) {
//            mPosition = mVideoView.getCurrentPosition();
            mVideoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.resume();
    }

    @Override
    protected void initView() {
        initvideo();
//        livingTop = (RelativeLayout) findViewById(R.id.living_top);
//        livingName = (TextView) findViewById(R.id.top_name_text);
//        livingbutton= (ImageButton) findViewById(R.id.top_back_btn);
//        livingbutton.setVisibility(View.VISIBLE);
//        livingbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        livingTop.setVisibility(View.VISIBLE);
//        livingName.setText(title+"直播");

        title=getIntent().getStringExtra("livingtitle");
        setResouce();
    }

    /**
     * 设置HLS流源文件地址
     */
    public void setResouce() {

        LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                videoHeight
        );
        fl_controller.setLayoutParams(fl_lp);
        uri = Uri.parse("http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8");

        mVideoView.setVideoURI(uri);
        mc = new MediaController(this, true, fl_controller);
        mVideoView.setMediaController(mc);
        mc.setVisibility(View.VISIBLE);
        mc.setGone(true);
        mc.setTitle(title);
        mc.setOnControllerListener(this);
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // state==3播放出错
                Log.e(TAG, "what = " + what + "  extra" + extra);
                Toast.makeText(LivingActivity.this,"此播放地址无法播放",Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }



    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }

    @Override
    protected void setLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_living);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoHeight = ViewUtils.getWidthPixel(LivingActivity.this) * 9 / 16;
        Vitamio.isInitialized(getApplication());
    }



    //以下关于视频
    @Override
    public void OnPortraitClick() {
        if (isPortrait) {
            mc.setLockShow(true);
            LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
                    ViewUtils.getHeightPixel(LivingActivity.this),
//                    ViewUtils.getWidthPixel(LivingActivity.this) - ViewUtils.getStatusBarHeight(LivingActivity.this)
                    ViewUtils.getWidthPixel(LivingActivity.this)
            );

            fl_controller.setLayoutParams(fl_lp);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
            isPortrait = false;
        } else {

            halfScreen();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isPortrait) {
                if (!isLock) {
                    halfScreen();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void OnBackClick() {
        if (isPortrait) {
            LivingActivity.this.finish();
        } else {
            halfScreen();
        }
    }

    private void halfScreen() {
        mc.setLockShow(false);
        LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                videoHeight
        );
        fl_controller.setLayoutParams(fl_lp);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        isPortrait = true;
    }

    @Override
    public void OnLockClick() {
        mc.setLockVisible(isLock);
        isLock = !isLock;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mc.show(4000);
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVideoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }
}
