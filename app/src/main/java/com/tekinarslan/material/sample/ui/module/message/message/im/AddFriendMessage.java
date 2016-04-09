package com.tekinarslan.material.sample.ui.module.message.message.im;

import cn.bmob.newim.bean.BmobIMExtraMessage;

/**添加好友请求
 * @author :smile
 * @project:addFriendMessage
 * @date :2016-01-30-17:28
 */
public class AddFriendMessage extends BmobIMExtraMessage {

    @Override
    public String getMsgType() {
        return "add";
    }

    @Override
    public boolean isTransient() {
        //设置为true,表明为暂态消息，那么这条消息并不会保存到本地db中，SDK只负责发送出去
        //设置为false,则会保存到指定会话的数据库中
        return true;
    }

    public AddFriendMessage(){}

}
