package com.tekinarslan.material.sample.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lyqdhgo on 2016/5/31.
 */
public class Friend extends BmobObject {
    //用户
    private User user;
    //好友
    private User friendUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }
}
