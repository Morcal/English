package com.tekinarslan.material.sample.ui.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.User;
import com.tekinarslan.material.sample.ui.module.message.message.event.FinishEvent;
import com.tekinarslan.material.sample.utills.ViewUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/4/4.
 */
public class RegisterFragment extends Fragment {
    @Bind(R.id.et_username)
    EditText editName;
    @Bind(R.id.et_password)
    EditText editPwd;
    @Bind(R.id.tv_register)
    TextView register;

    private User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initEvEnt();
    }

    private void initEvEnt() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterClick();
//                String userName = editName.getText().toString().trim();
//                String password = editPwd.getText().toString().trim();
//                if (!userName.isEmpty() && !password.isEmpty()) {
//                    user = new User();
//                    user.setUsername(userName);
//                    user.setPassword(password);
//                    if (userName.contains("@")) {
//                        user.setEmail(userName);
//                    } else {
//                        user.setMobilePhoneNumber(userName);
//                    }
//                }
//                user.signUp(getActivity(), new SaveListener() {
//                    @Override
//                    public void onSuccess() {
//                        ViewUtils.showToastShort(getActivity(), "注册成功");
//                        ((LoginActivity) getActivity()).setDefaultFragment();
//                    }
//
//                    @Override
//                    public void onFailure(int i, String s) {
//                        ViewUtils.showToastShort(getActivity(), "注册失败" + s);
//                    }
//                });
            }
        });
    }

    public void onRegisterClick() {
        UserModel.getInstance().register(editName.getText().toString(), editPwd.getText().toString(), null, new LogInListener() {
            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {
                    EventBus.getDefault().post(new FinishEvent());
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                } else {
                    ViewUtils.showToastShort(getActivity(), e.getMessage() + "(" + e.getErrorCode() + ")");
                }
            }
        });
    }
}
