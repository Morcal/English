package com.tekinarslan.material.sample.ui.module.own;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.Result;
import com.tekinarslan.material.sample.utills.Util;
import com.tekinarslan.material.sample.utills.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class EditAnswerActivity extends AppCompatActivity {
    private static String DEFAULT_USER_ID = "1";
    private static int INPUTEDE = 2;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_ask)
    EditText editAsk;
    @Bind(R.id.tv_save)
    TextView save;
    @Bind(R.id.tv_inputed)
    TextView tvInput;
    @Bind(R.id.tv_total)
    TextView tvTotal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_edit_answer);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != this) {
                    finish();
                }
            }
        });

        // 监听editText字数变化
        editAsk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Logger.i("Count->" + count);


            }

            @Override
            public void afterTextChanged(Editable s) {
                int inputed = s.length();
                Logger.i(" 已输入->" + inputed);
                tvInput.setText(inputed + "");

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = editAsk.getText().toString().trim();
                Logger.i("提交提问->" + question);
                String md5Str = Util.strToMd5(question);
                // Logger.i("MD5加密后->" + md5Str);

                // 从Sp取出objectId
                SharedPreferences preferences = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
                String objectId = preferences.getString("objectId", DEFAULT_USER_ID);
                // String objectId = Contast.objectId;
                // String objectId = user.getObjectId();
                Logger.i("UserID->" + objectId);
                if (objectId.equals("") && objectId == null) {
                    ViewUtils.showToastShort(EditAnswerActivity.this, "objectId is null");
                }
                String ask = Contast.SERVERHOST + "/ask?source=3&user_id=" + objectId + "&ask=" + question;
                Dao.getEntity(ask, new Dao.EntityListener() {
                    @Override
                    public void onError() {
                        Logger.i("提交失败");
                    }

                    @Override
                    public void onSuccess(String result) {
                        Logger.i("提交成功" + " Result->" + result);
                        Result askList = new Gson().fromJson(result, Result.class);
                        if ("9999".equals(askList.getE())) {
                            Logger.i("提交问题成功");
                            ViewUtils.showToastShort(EditAnswerActivity.this, "提交成功");
                        } else {
                            Logger.i("其他原因导致提交失败");
                            ViewUtils.showToastShort(EditAnswerActivity.this, "提交失败 " + askList.getM());
                        }
                    }
                });
            }
        });

    }

}
