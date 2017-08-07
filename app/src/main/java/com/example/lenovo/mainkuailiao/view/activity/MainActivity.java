package com.example.lenovo.mainkuailiao.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lenovo.mainkuailiao.R;
import com.example.lenovo.mainkuailiao.view.iview.CustomVideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomVideoView videoview;
    private Button logbut;
    private Button logbut2;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
            initView();
    }
        private void initView() {
            linearLayout = (LinearLayout) findViewById(R.id.main_linear);
            linearLayout2 = (LinearLayout) findViewById(R.id.main_lindar2);
            logbut = (Button) findViewById(R.id.log_but);
            logbut2 = (Button) findViewById(R.id.main_log);
            logbut.setOnClickListener(this);
            logbut2.setOnClickListener(this);
            //加载视频资源控件
            videoview = (CustomVideoView) findViewById(R.id.videoview);
            //设置播放加载路径
            videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bg1));
            //播放
            videoview.start();
            //循环播放
            videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    videoview.start();
                }
            });
        }
    //返回重启加载
    @Override
    protected void onRestart() {
        initView();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        videoview.stopPlayback();
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){//View点击事件选择使用
            case R.id.log_but:
               /* if(WiFiUtils.isNetworkAvailable(MainActivity.this)){

                }*/
                Intent intent = new Intent(MainActivity.this,TowActivity.class);
                startActivity(intent);
            break;
            case R.id.main_log:
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                setAnimator(linearLayout);
                break;
            default :
            break;
        }
    }
    public static  void setAnimator(LinearLayout llLogins) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(llLogins, "alpha", 0f, 1f);
        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(llLogins, "translationY",llLogins.getY(),-500f);
        set.play(alpha).with(translationXAnimator);
        set.setDuration(1000);
        set.start();
    }
}
