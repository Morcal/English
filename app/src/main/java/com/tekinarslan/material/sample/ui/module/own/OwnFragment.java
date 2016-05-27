package com.tekinarslan.material.sample.ui.module.own;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.GlideImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.utills.PicassoImageLoader;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.CircleImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/18.
 */
public class OwnFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = OwnFragment.class.getSimpleName();
    private static final int IMAGE_PICKER = 1;
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
    @Bind(R.id.profile_avatar)
    CircleImageView avator;

    ImagePicker imagePicker;

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
        setImagePicker();
    }

    private void init() {
        avator.setOnClickListener(this);
        setting.setOnClickListener(this);
        profileMore.setOnClickListener(this);
        tvCollect.setOnClickListener(this);
        tvTiezi.setOnClickListener(this);
        tvQuestion.setOnClickListener(this);
    }

    private void setImagePicker() {

        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setMultiMode(false); //图片选择模式
//        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_avatar:
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
                break;
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
                Intent asklist = new Intent(getActivity(), AskListActivity.class);
                startActivity(asklist);
                break;

            case R.id.tv_question:
                Intent question = new Intent(getActivity(), EditAnswerActivity.class);
                startActivity(question);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                String path = images.get(0).path;
                Logger.i("images sizes->" + images.size() + "path->" + path);
                PicassoImageLoader imageLoader = new PicassoImageLoader();
                imageLoader.displayImage(getActivity(), path, avator, 50, 50);
                // 存储头像路径
                SharedPreferences preferences = getActivity().getSharedPreferences("avatorpath", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("path", path);
                editor.commit();
            } else {
                ViewUtils.showToastShort(getActivity(), "没有数据");
            }
        }
    }
}
