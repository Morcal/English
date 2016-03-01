package com.tekinarslan.material.sample.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyqdh on 2016/1/4.
 */
public class User {
    // id
    private int id;
    // nickname
    private String nickname;
    // 头像
    private String avatar;
    // 作品数
    private String count;
    // 收藏的清单
    private Article collect;
    // 喜欢的清单
    private Article like;

    // 收藏集
    private List<Article> clollects = new ArrayList<>();
    private List<Article> likes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Article getCollect() {
        return collect;
    }

    public void setCollect(Article collect) {
        this.collect = collect;
    }

    public Article getLike() {
        return like;
    }

    public void setLike(Article like) {
        this.like = like;
    }

    public void addCollect(Article article) {
        clollects.add(article);
    }

    public List<Article> getClollects() {
        return clollects;
    }

    public void setClollects(List<Article> clollects) {
        this.clollects = clollects;
    }

    public List<Article> getLikes() {
        return likes;
    }

    public void setLikes(List<Article> likes) {
        this.likes = likes;
    }
}
