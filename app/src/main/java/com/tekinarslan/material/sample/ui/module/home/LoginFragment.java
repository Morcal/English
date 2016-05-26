package com.tekinarslan.material.sample.ui.module.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.elbbbird.android.socialsdk.SocialSDK;
import com.elbbbird.android.socialsdk.model.SocialToken;
import com.elbbbird.android.socialsdk.model.SocialUser;
import com.elbbbird.android.socialsdk.otto.BusProvider;
import com.elbbbird.android.socialsdk.otto.SSOBusEvent;
import com.orhanobut.logger.Logger;
import com.squareup.otto.Subscribe;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.User;
import com.tekinarslan.material.sample.ui.module.main.MainActivity;
import com.tekinarslan.material.sample.ui.module.message.message.event.FinishEvent;
import com.tekinarslan.material.sample.utills.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/4/4.
 */
public class LoginFragment extends Fragment {
    private static final String TAG = LoginFragment.class.getSimpleName();
    @Bind(R.id.et_username)
    EditText editName;
    @Bind(R.id.et_password)
    EditText editPwd;
    @Bind(R.id.tv_login)
    TextView login;
    @Bind(R.id.tv_tip)
    TextView tip;
    @Bind(R.id.tv_oauth)
    TextView oAuth;

    private User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initEvent();
    }

    private void initEvent() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
//                String userName = editName.getText().toString().trim();
//                String password = editPwd.getText().toString().trim();
//                if (!userName.isEmpty() && !password.isEmpty()) {
//                    user = new User();
//                    user.setUsername(userName);
//                    user.setPassword(password);
//                    user.login(getActivity(), new SaveListener() {
//                        @Override
//                        public void onSuccess() {
//                            ViewUtils.showToastShort(getActivity(), "登录成功");
//                            if (getActivity() != null) {
//                                getActivity().finish();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(int i, String s) {
//                            ViewUtils.showToastShort(getActivity(), "登录失败" + s);
//                            if (getActivity() != null) {
//                                getActivity().finish();
//                            }
//                        }
//                    });
//                }
            }
        });
    }

    public void onLoginClick() {
        UserModel.getInstance().login(editName.getText().toString(), editPwd.getText().toString(), new LogInListener() {

            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {
                    User user = (User) o;
                    //更新当前用户资料
                    String objectId = user.getObjectId();
                    Logger.i("Login->" + objectId);
                    // 登录成功后将数据写到sp
                    SharedPreferences preferences = getActivity().getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", user.getUsername());
                    editor.putString("password", editPwd.getText().toString());
                    editor.putString("objectId", user.getObjectId());
                    editor.commit();
                    Logger.i("Sp存储成功");

                    BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                } else {
                    ViewUtils.showToastShort(getActivity(), e.getMessage() + "(" + e.getErrorCode() + ")");
                }
            }
        });
    }

    @org.greenrobot.eventbus.Subscribe
    public void onEventMainThread(FinishEvent event) {
        getActivity().finish();
    }

    @OnClick(R.id.tv_oauth)
    public void loginWeiChat() {
        SocialSDK.setDebugMode(true);
        SocialSDK.init("wx3439182f14734741", "a6a0eef787f4f1094702e9d7b1e6262c", "2260575206", "1105240361");
        SocialSDK.oauth(getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onOauthResult(SSOBusEvent event) {
        switch (event.getType()) {
            case SSOBusEvent.TYPE_GET_TOKEN:
                SocialToken token = event.getToken();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_GET_TOKEN " + token.toString());
                break;
            case SSOBusEvent.TYPE_GET_USER:
                SocialUser user = event.getUser();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_GET_USER " + user.toString());
                break;
            case SSOBusEvent.TYPE_FAILURE:
                Exception e = event.getException();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_FAILURE " + e.toString());
                break;
            case SSOBusEvent.TYPE_CANCEL:
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_CANCEL");
                break;
        }
    }

}
