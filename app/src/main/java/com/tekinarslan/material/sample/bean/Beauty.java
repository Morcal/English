package com.tekinarslan.material.sample.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lyqdhgo on 2016/3/30.
 */
public class Beauty extends BmobObject {


    /**
     * id : 1267
     * title : VOL.1267 多重自我
     * data : 27
     * 03/2016
     * imgUrl : http://caodan.org/wp-content/uploads/vol/1267.jpg
     * theme : 多重自我
     * author : Dola Sun 作品
     * desc : 感情这个东西，你爱我，我就更爱你。你讨厌我，我就自觉别过头去。 by  小饭
     * comment : 暂无评论
     */

    private String id;
    private String title;
    private String data;
    private String imgUrl;
    private String theme;
    private String author;
    private String desc;
    private String comment;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTheme() {
        return theme;
    }

    public String getAuthor() {
        return author;
    }

    public String getDesc() {
        return desc;
    }

    public String getComment() {
        return comment;
    }
}
