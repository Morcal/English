package com.tekinarslan.material.sample.ui.module.message.message.im.im.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.utills.TimeUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.List;

import butterknife.Bind;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMMessageType;

public class ConversationHolder extends BaseViewHolder {

    @Bind(R.id.iv_recent_avatar)
    public ImageView iv_recent_avatar;
    @Bind(R.id.tv_recent_name)
    public TextView tv_recent_name;
    @Bind(R.id.tv_recent_msg)
    public TextView tv_recent_msg;
    @Bind(R.id.tv_recent_time)
    public TextView tv_recent_time;
    @Bind(R.id.tv_recent_unread)
    public TextView tv_recent_unread;

    public ConversationHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_conversation, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {
        BmobIMConversation conversation = (BmobIMConversation) o;
        List<BmobIMMessage> msgs = conversation.getMessages();
        if (msgs != null && msgs.size() > 0) {
            BmobIMMessage lastMsg = msgs.get(0);
            String content = lastMsg.getContent();
            if (lastMsg.getMsgType().equals(BmobIMMessageType.TEXT.getType())) {
                tv_recent_msg.setText(content);
            } else if (lastMsg.getMsgType().equals(BmobIMMessageType.IMAGE.getType())) {
                tv_recent_msg.setText("[图片]");
            } else if (lastMsg.getMsgType().equals(BmobIMMessageType.VOICE.getType())) {
                tv_recent_msg.setText("[语音]");
            } else if (lastMsg.getMsgType().equals(BmobIMMessageType.LOCATION.getType())) {
                tv_recent_msg.setText("[位置]" + content);
            } else {//开发者自定义的消息类型，需要自行处理
                tv_recent_msg.setText("[未知]");
            }
            tv_recent_time.setText(TimeUtil.getChatTime(false, lastMsg.getCreateTime()));
        }
        //会话图标
        ViewUtils.setAvatar(conversation.getConversationIcon(), R.drawable.head, iv_recent_avatar);
        //会话标题
        tv_recent_name.setText(conversation.getConversationTitle());
        //查询指定未读消息数
        long unread = BmobIM.getInstance().getUnReadCount(conversation.getConversationId());
        if (unread > 0) {
            tv_recent_unread.setVisibility(View.VISIBLE);
            tv_recent_unread.setText(String.valueOf(unread));
        } else {
            tv_recent_unread.setVisibility(View.GONE);
        }
    }

}