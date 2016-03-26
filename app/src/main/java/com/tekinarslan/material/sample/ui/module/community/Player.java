package com.tekinarslan.material.sample.ui.module.community;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lyqdhgo on 2016/3/26.
 */
public class Player implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private int playPosition;
    private boolean pause;
    public MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Timer timer = new Timer();

    public Player(SeekBar seekBar) {
        this.seekBar = seekBar;
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            Log.e("MediaPlayer", "error", e);
        }
        timer.schedule(mTimerTask, 0, 1000);
    }

    // 通过定时器和Handler来完成更新进度条
    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (mediaPlayer == null) {
                return;
            }
            if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
                handleProgress.sendEmptyMessage(0);
            }
        }
    };

    Handler handleProgress = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (position > 0) {
                long pos = seekBar.getMax() * position / duration;
                seekBar.setProgress((int) pos);
            }
        }
    };

    public void play() {
        mediaPlayer.start();
    }

    public void playUrl(String videoUrl) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(videoUrl);
            mediaPlayer.prepareAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            pause = true;
        } else {
            if (pause) {
                mediaPlayer.start();
                pause = false;
            }
        }
        return pause;
    }

    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 来电话了
     */
    public void callIsComing() {
        if (mediaPlayer.isPlaying()) {
            playPosition = mediaPlayer.getCurrentPosition();// 获得当前播放位置
            mediaPlayer.stop();
        }
    }

    /**
     * 通话结束
     */
    public void callIsDown() {
        if (playPosition > 0) {
//            playNet(playPosition);
            playPosition = 0;
        }
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setSecondaryProgress(percent);
        int currentProgress = seekBar.getMax() * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
        Log.i("currentProgress:" + currentProgress + " %play", "percent:" + percent + " %buffer");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i("mediaPlayer", "onCompletion");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        Log.i("mediaPlayer", "onPrepared");
    }
}
