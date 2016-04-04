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
import com.tekinarslan.material.sample.utills.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/4/4.
 */
public class LoginFragment extends Fragment {
    @Bind(R.id.et_username)
    EditText editName;
    @Bind(R.id.et_password)
    EditText editPwd;
    @Bind(R.id.tv_login)
    TextView login;
    @Bind(R.id.tv_tip)
    TextView tip;

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
                String userName = editName.getText().toString().trim();
                String password = editPwd.getText().toString().trim();
                if (!userName.isEmpty() && !password.isEmpty()) {
                    user = new User();
                    user.setUsername(userName);
                    user.setPassword(password);
                    user.login(getActivity(), new SaveListener() {
                        @Override
                        public void onSuccess() {
                            ViewUtils.showToastShort(getActivity(), "登录成功");
                            if (getActivity() != null) {
                                getActivity().finish();
                            }
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            ViewUtils.showToastShort(getActivity(), "登录失败" + s);
                            if (getActivity() != null) {
                                getActivity().finish();
                            }
                        }
                    });
                }
            }
        });
    }
}
