package com.tekinarslan.material.sample.ui.module.own;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.PlayAudio;
import com.tekinarslan.material.sample.bean.Result;
import com.tekinarslan.material.sample.bean.ResultAskList;
import com.tekinarslan.material.sample.bean.User;
import com.tekinarslan.material.sample.ui.module.community.BeautyDetialActivity;
import com.tekinarslan.material.sample.utills.Util;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/18.
 */
public class OwnFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = OwnFragment.class.getSimpleName();
    @Bind(R.id.profile_setting)
    TextView setting;
    @Bind(R.id.profile_more)
    ImageView profileMore;
    @Bind(R.id.tv_collect)
    TextView tvCollect;
    @Bind(R.id.tv_tiezi)
    TextView tvTiezi;
    @Bind(R.id.tv_question)
    TextView tvQuestion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_own, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        setting.setOnClickListener(this);
        profileMore.setOnClickListener(this);
        tvCollect.setOnClickListener(this);
        tvTiezi.setOnClickListener(this);
        tvQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_setting:
                Intent setting = new Intent(getActivity(), SettingActivity.class);
                startActivity(setting);
                break;
            case R.id.profile_more:
                Intent editInfo = new Intent(getActivity(), MineInfoActivity.class);
                startActivity(editInfo);
//                showDatePicker();
                break;
            case R.id.tv_collect:
                Intent collect = new Intent(getActivity(), CollectActivity.class);
                startActivity(collect);
                break;
            case R.id.tv_tiezi:
                Intent asklist=new Intent(getActivity(),AskListActivity.class);
                startActivity(asklist);
                break;

            case R.id.tv_question:
                Intent question = new Intent(getActivity(), EditAnswerActivity.class);
                startActivity(question);
                break;
        }
    }
}
