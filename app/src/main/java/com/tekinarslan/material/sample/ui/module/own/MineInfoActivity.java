package com.tekinarslan.material.sample.ui.module.own;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.CircleImageView;
import com.tekinarslan.material.sample.weight.SexChooseDialog;
import com.tekinarslan.material.sample.weight.wheelview.AddressView;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class MineInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MineInfoActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.avatar)
    CircleImageView avatar;
    @Bind(R.id.tv_nickname)
    TextView nickName;
    @Bind(R.id.tv_sex)
    TextView sex;
    @Bind(R.id.tv_birthday)
    TextView birthday;
    @Bind(R.id.tv_location)
    TextView location;

    private SelectedDate mSelectedDate;
    private AddressView addressView;
    protected String mRecurrenceOption, mRecurrenceRule;

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
            mSelectedDate = selectedDate;
            mRecurrenceOption = recurrenceOption != null ?
                    recurrenceOption.name() : "n/a";
            mRecurrenceRule = recurrenceRule != null ?
                    recurrenceRule : "n/a";
            updateInfoView();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mineinfo);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
    }

    private void initEvent() {
        avatar.setOnClickListener(this);
        nickName.setOnClickListener(this);
        sex.setOnClickListener(this);
        birthday.setOnClickListener(this);
        location.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showDatePicker() {
        // DialogFragment to host SublimePicker
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mFragmentCallback);

        // Options\
        Pair<Boolean, SublimeOptions> optionsPair = getOptions();

        if (!optionsPair.first) { // If options are not valid
            ViewUtils.showToastShort(this, "No pickers activated");
            return;
        }
        // Valid options
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
        pickerFrag.setArguments(bundle);

        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(this.getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    // Validates & returns SublimePicker options
    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = 0;


        displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;
        // displayOptions |= SublimeOptions.ACTIVATE_TIME_PICKER;
        // displayOptions |= SublimeOptions.ACTIVATE_RECURRENCE_PICKER;
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        // options.setPickerToShow(SublimeOptions.Picker.TIME_PICKER);
        // options.setPickerToShow(SublimeOptions.Picker.REPEAT_OPTION_PICKER);
        options.setDisplayOptions(displayOptions);
        return new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }

    // Show date, time & recurrence options that have been selected
    private void updateInfoView() {
        if (mSelectedDate != null) {
            String year = (String.valueOf(mSelectedDate.getStartDate()
                    .get(Calendar.YEAR))) + (applyBoldStyle("年"));
            String mouth = (String.valueOf(mSelectedDate.getStartDate()
                    .get(Calendar.MONTH) + 1)) + (applyBoldStyle("月"));
            String day = (String.valueOf(mSelectedDate.getStartDate()
                    .get(Calendar.DAY_OF_MONTH))) + applyBoldStyle("日");
            String date = year + mouth + day;
            Log.i(TAG, "date->" + date);
            birthday.setText(date);
        }
    }

    // Applies a StyleSpan to the supplied text
    private SpannableStringBuilder applyBoldStyle(String text) {
        SpannableStringBuilder ss = new SpannableStringBuilder(text);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 显示性别选择对话框
     *
     * @param isMale 默认选择的性别 true 男  false 女
     */
    private void showSexChooseDialog(boolean isMale) {
        SexChooseDialog.show(this, isMale).addOnDialogItemClickListener(new SexChooseDialog.OnDialogItemClickListener() {
            @Override
            public void onClick(int which) {
                sex.setText(which == SexChooseDialog.MALE ? "男" : "女");
            }
        });
    }

    /**
     * 地址选择
     */
    private void showAddressChooseDialog() {
        if (addressView == null) {
            addressView = new AddressView(this);
            addressView.setOnCityTextChange(new AddressView.OnCityTextChange() {
                @Override
                public void onChange(String text) {
                    location.setText(text);
                addressView.getCity();
                // user.detail.province = addressView.getProvince();
                // user.detail.city = addressView.getCity();
                if (addressView.getProvince() == null) {
                    ViewUtils.showToastShort(MineInfoActivity.this,"请选择省份");
                } else if (addressView.getCity() == null) {
                    ViewUtils.showToastShort(MineInfoActivity.this,"请选择城市");

                }
                }
            });
        }
        addressView.showAddressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar:
                break;
            case R.id.tv_nickname:
                Intent nickName = new Intent(this, EditNameActivity.class);
                startActivityForResult(nickName, 1);
                break;
            case R.id.tv_sex:
                showSexChooseDialog(sex.getText() != "女");
                break;
            case R.id.tv_birthday:
                showDatePicker();
                break;
            case R.id.tv_location:
                showAddressChooseDialog();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                String name = data.getStringExtra("NICKNAME");
                nickName.setText(name);
                break;
        }
    }
}
