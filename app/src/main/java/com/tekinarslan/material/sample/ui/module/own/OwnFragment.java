package com.tekinarslan.material.sample.ui.module.own;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.utills.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/18.
 */
public class OwnFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.profile_setting)
    TextView setting;
    @Bind(R.id.profile_more)
    ImageView profileMore;


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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_more:
                // DialogFragment to host SublimePicker
                SublimePickerFragment pickerFrag = new SublimePickerFragment();
//                pickerFrag.setCallback(mFragmentCallback);

                // Options\

                Pair<Boolean, SublimeOptions> optionsPair = getOptions();

                if (!optionsPair.first) { // If options are not valid
                    ViewUtils.showToastShort(getActivity(), "No pickers activated");
                    return;
                }

                // Valid options
                Bundle bundle = new Bundle();
                bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
                pickerFrag.setArguments(bundle);

                pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                pickerFrag.show(getActivity().getSupportFragmentManager(), "SUBLIME_PICKER");
                break;
        }
    }

    // Validates & returns SublimePicker options
    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = 0;


        displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;
        displayOptions |= SublimeOptions.ACTIVATE_TIME_PICKER;
        displayOptions |= SublimeOptions.ACTIVATE_RECURRENCE_PICKER;
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        options.setPickerToShow(SublimeOptions.Picker.TIME_PICKER);
        options.setPickerToShow(SublimeOptions.Picker.REPEAT_OPTION_PICKER);

        options.setDisplayOptions(displayOptions);

        // Enable/disable the date range selection feature
//        options.setCanPickDateRange(cbAllowDateRangeSelection.isChecked());

        // Example for setting date range:
        // Note that you can pass a date range as the initial date params
        // even if you have date-range selection disabled. In this case,
        // the user WILL be able to change date-range using the header
        // TextViews, but not using long-press.

        /*Calendar startCal = Calendar.getInstance();
        startCal.set(2016, 2, 4);
        Calendar endCal = Calendar.getInstance();
        endCal.set(2016, 2, 17);

        options.setDateParams(startCal, endCal);*/

        // If 'displayOptions' is zero, the chosen options are not valid
        return new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }
}
