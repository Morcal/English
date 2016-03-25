package com.tekinarslan.material.sample.ui.module.community;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
    @Bind(R.id.playpause)
    PlayPauseButton pauseButton;
    @Bind(R.id.playpausewrapper)
    View playWraper;
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
        Intent intent = getIntent();
        String id = intent.getStringExtra("AUDIO");
        ViewUtils.showToastShort(this, "ID:" + id);
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
                String audioUrl = playAudio.getAudioUrl();
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
                UIUtil.setAvatar(attachedImg, conver);
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
    }

    private final View.OnClickListener mPlayPauseExpandedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!pauseButton.isPlayed()) {
                pauseButton.setPlayed(true);
                pauseButton.startAnimation();
            } else {
                pauseButton.setPlayed(false);
                pauseButton.startAnimation();
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 200);

        }
    };
}
