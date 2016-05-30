package com.tekinarslan.material.sample.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lyqdhgo on 2016/4/30.
 */
public class CourseCetEntity extends BmobObject {


    /**
     * id : cet401
     * title :  四级01
     * audioUrl : http://7xrfxa.com1.z0.glb.clouddn.com/sayenglish01.mp4
     */

    private String id;
    private String title;
    private String audioUrl;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAudioUrl() {
        return audioUrl;
    }
}
