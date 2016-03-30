package com.tekinarslan.material.sample.ui.module.community;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.PlayAudio;
import com.tekinarslan.material.sample.bean.Topic;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.CircleImageView;
import com.tekinarslan.material.sample.weight.HWRatioImageView;
import com.tekinarslan.material.sample.weight.PlayPauseButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.next.tagview.TagCloudView;

/**
 * Created by lyqdhgo on 2016/3/18.
 */
public class PlayAudioActivity extends AppCompatActivity {
    private static final String TAG = PlayAudioActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_share)
    TextView tvShare;
    @Bind(R.id.playpause)
    PlayPauseButton pauseButton;
    @Bind(R.id.playpausewrapper)
    View playWraper;
    @Bind(R.id.skb_progress)
    SeekBar seekBar;
    @Bind(R.id.layout_playaudio)
    LinearLayout layoutPalyAudio;
    @Bind(R.id.tag_view)
    TagCloudView tagView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_body)
    TextView tvBody;
    @Bind(R.id.quanzi)
    TextView tvCircle;
    @Bind(R.id.nickname)
    TextView nickName;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.grade)
    TextView grade;
    @Bind(R.id.civ_avatar)
    CircleImageView avatar;
    @Bind(R.id.riv_image)
    HWRatioImageView conver;
    private PlayAudio.UserEntity user;
    private Player player;
    private String audioUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playaudio);
        ButterKnife.bind(this);
        iniData();
        initView();
        initEvent();
    }

    private void iniData() {
        seekBar.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        player = new Player(seekBar);
        // 监听电话来时的情况
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);

        Intent intent = getIntent();
        String id = intent.getStringExtra("AUDIO");
        String url = Contast.LISTENDETIAL + id + Contast.COMMONPARAM;

        ViewUtils.showDialog(this, "Loading");
        Dao.getEntity(url, new Dao.EntityListener() {
            @Override
            public void onError() {
                ViewUtils.showToastShort(PlayAudioActivity.this, getString(R.string.error));
                ViewUtils.hideDialog();
            }

            @Override
            public void onSuccess(String result) {
                ViewUtils.hideDialog();
                Log.i(TAG, "result: " + result);
                PlayAudio playAudio = new Gson().fromJson(result, PlayAudio.class);
                String title = playAudio.getTitle();
                String body = playAudio.getBody();
                audioUrl = playAudio.getAudioUrl();
                Log.i(TAG, "audioUrl:" + audioUrl);
                if (audioUrl == null || audioUrl.equals("")) {
                    layoutPalyAudio.setVisibility(View.GONE);
                }
                int length = playAudio.getAudioLength();
                List<String> tags = playAudio.getTags();

                String attachedImg = playAudio.getAttachedImg();
                String circle = playAudio.getCircle().getName();
                user = playAudio.getUser();
                nickName.setText(user.getNick());
                grade.setText("lv" + user.getLevel());
                grade.setVisibility(View.VISIBLE);
                user.getAvatar();
                tvCircle.setText(circle);
                tvTitle.setText(title);
                tvBody.setText(body);
                tagView.setTags(tags);
                UIUtil.setAvatar(user.getAvatar(), avatar);
                if (attachedImg == null || attachedImg.equals("")) {
                    conver.setVisibility(View.GONE);
                } else {
                    conver.setVisibility(View.VISIBLE);
                    UIUtil.setAvatar(attachedImg, conver);
                }
                initView();
            }
        });

    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);

        pauseButton.setColor(Color.WHITE);
        pauseButton.startAnimation();
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        playWraper.setOnClickListener(mPlayPauseExpandedListener);
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "分享。。。");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "分享内容测试。。。");
                startActivity(Intent.createChooser(intent, "分享到"));
            }
        });
    }

    private final View.OnClickListener mPlayPauseExpandedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!pauseButton.isPlayed()) {
                pauseButton.setPlayed(true);
                pauseButton.startAnimation();
                Log.i(TAG, "播放");
                Log.i(TAG, "audioUrl：" + audioUrl);
                player.playUrl(audioUrl);
            } else {
                pauseButton.setPlayed(false);
                pauseButton.startAnimation();

                Log.i(TAG, "暂停");
                player.pause();
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 200);

        }
    };

    // 有电话时暂停音乐
    private final class PhoneListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    player.callIsComing();
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    player.callIsDown();
                    break;
            }
        }
    }

    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            this.progress = progress * player.mediaPlayer.getDuration() / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            player.mediaPlayer.seekTo(progress);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
