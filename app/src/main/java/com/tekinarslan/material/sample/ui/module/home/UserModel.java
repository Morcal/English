package com.tekinarslan.material.sample.ui.module.home;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.bean.User;

import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author :smile
 * @project:UserModel
 * @date :2016-01-22-18:09
 */
public class UserModel extends BaseModel {

    private static UserModel ourInstance = new UserModel();

    public static UserModel getInstance() {
        return ourInstance;
    }

    private UserModel() {}

    /** 登录
     * @param username
     * @param password
     * @param listener
     */
    public void login(String username, String password, final LogInListener listener) {
        if(TextUtils.isEmpty(username)){
            listener.internalDone(new BmobException(CODE_NULL, "请填写用户名"));
            return;
        }
        if(TextUtils.isEmpty(password)){
            listener.internalDone(new BmobException(CODE_NULL, "请填写密码"));
            return;
        }
        final User user =new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(getContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                listener.done(getCurrentUser(),null);
            }

            @Override
            public void onFailure(int i, String s) {
                listener.done(user,new BmobException(i,s));
            }
        });
    }

    /**
     * 退出登录
     */
    public void logout(){
        BmobUser.logOut(getContext());
    }

    public User getCurrentUser(){
        return BmobUser.getCurrentUser(getContext(), User.class);
    }
    /**
     * @param username
     * @param password
     * @param pwdagain
     * @param listener
     */
    public void register(String username,String password, String pwdagain, final LogInListener listener) {
        if(TextUtils.isEmpty(username)){
            listener.internalDone(new BmobException(CODE_NULL, "请填写用户名"));
            return;
        }
        if(TextUtils.isEmpty(password)){
            listener.internalDone(new BmobException(CODE_NULL, "请填写密码"));
            return;
        }
        if(TextUtils.isEmpty(pwdagain)){
            listener.internalDone(new BmobException(CODE_NULL, "请填写确认密码"));
            return;
        }
        if(!password.equals(pwdagain)){
            listener.internalDone(new BmobException(CODE_NOT_EQUAL, "两次输入的密码不一致，请重新输入"));
            return;
        }
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(getContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                listener.done(null,null);
            }

            @Override
            public void onFailure(int i, String s) {
                listener.done(null,new BmobException(i,s));
            }
        });
    }

    /**查询用户
     * @param username
     * @param limit
     * @param listener
     */
    public void queryUsers(String username,int limit,final FindListener<User> listener){
        BmobQuery<User> query = new BmobQuery<>();
        //去掉当前用户
        try {
            BmobUser user = BmobUser.getCurrentUser(getContext());
            query.addWhereNotEqualTo("username",user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        query.addWhereContains("username", username);
        query.setLimit(limit);
        query.order("-createdAt");
        query.findObjects(getContext(), new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                if (list != null && list.size() > 0) {
                    listener.onSuccess(list);
                } else {
                    listener.onError(CODE_NULL, "查无此人");
                }
            }

            @Override
            public void onError(int i, String s) {
                listener.onError(i, s);
            }
        });
    }

    /**查询用户信息
     * @param objectId
     * @param listener
     */
    public void queryUserInfo(String objectId, final QueryUserListener listener){
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", objectId);
        query.findObjects(getContext(), new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                if(list!=null && list.size()>0){
                    listener.internalDone(list.get(0), null);
                }else{
                    listener.internalDone(new BmobException(000, "查无此人"));
                }
            }

            @Override
            public void onError(int i, String s) {
                listener.internalDone(new BmobException(i, s));
            }
        });
    }

    /**更新用户资料和会话资料
     * @param event
     * @param listener
     */
    public void updateUserInfo(MessageEvent event,final UpdateCacheListener listener){
        final BmobIMConversation conversation=event.getConversation();
        final BmobIMUserInfo info =event.getFromUserInfo();
        String username =info.getName();
        String title =conversation.getConversationTitle();
        Logger.i("" + username + "," + title);
        //sdk内部，将新会话的会话标题用objectId表示，因此需要比对用户名和会话标题--单聊，后续会根据会话类型进行判断
        if(!username.equals(title)) {
            UserModel.getInstance().queryUserInfo(info.getUserId(), new QueryUserListener() {
                @Override
                public void done(User s, BmobException e) {
                    if(e==null){
                        String name =s.getUsername();
                        String avatar = s.getAvatar();
                        Logger.i("query success："+name+","+avatar);
                        conversation.setConversationIcon(avatar);
                        conversation.setConversationTitle(name);
                        info.setName(name);
                        info.setAvatar(avatar);
                        //更新用户资料
                        BmobIM.getInstance().updateUserInfo(info);
                        //更新会话资料
                        BmobIM.getInstance().updateConversation(conversation);
                    }else{
                        Logger.e(e);
                    }
                    listener.done(null);
                }
            });
        }else{
            listener.internalDone(null);
        }
    }
}
