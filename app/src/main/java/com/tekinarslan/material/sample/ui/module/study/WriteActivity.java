package com.tekinarslan.material.sample.ui.module.study;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Write;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/4/5.
 */
public class WriteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = WriteActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.but_find_write)
    Button find;
    @Bind(R.id.but_save_write)
    Button save;
    @Bind(R.id.tv_writedir)
    TextView writeDire;
    @Bind(R.id.tv_writeque)
    TextView writeQues;
    @Bind(R.id.et_write)
    EditText write;
    @Bind(R.id.iv_image)
    ImageView image;

    String title;
    String direction;
    String question;
    String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);
        initView();
//        initData();
        initEvent();
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        find.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    private void findData() {
        final BmobQuery<Write> query = new BmobQuery<Write>();
        query.findObjects(this, new FindListener<Write>() {
            @Override
            public void onSuccess(List<Write> list) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(WriteActivity.this, "查询成功");
                Log.i(TAG, "write size-> " + list.size());
//                for (int i = 0; i < list.size(); i++) {
                Write write = list.get(1);
                if (write != null) {
                    title = write.getTitle();
                    Write.WriteEntity writeEntity = write.getWrite();
                    if (writeEntity != null) {
                        question = writeEntity.getWriteQuestion();
                        direction = writeEntity.getWriteDirection();
                        imageUrl = writeEntity.getWriteImageUrl();
                        if (imageUrl != null) {
                            image.setVisibility(View.VISIBLE);
                            UIUtil.setAvatar(imageUrl, image, 360, 155);
                        }
                        writeDire.setTypeface(Typeface.createFromAsset(WriteActivity.this.getAssets(), "fonts/FZSongKeBenXiuKaiS-R-GB.TTF"));
                        writeDire.setTypeface(Typeface.createFromAsset(WriteActivity.this.getAssets(), "fonts/inconsolata.otf"));
                        writeDire.setText(direction);
                        writeQues.setText(question);
                    }
                }
                Log.i(TAG, "title->" + title + " question->" + question);
            }
//            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(WriteActivity.this, "查询失败");
            }
        });
    }

    private void saveData() {
        Write write = new Write();
        Write.WriteEntity writeEntity = new Write.WriteEntity();
        write.setId("cet4151201");
        write.setTitle("cet4write151201");
        writeEntity.setScore(106.5);
        writeEntity.setStandardAnswer("标准答案");
        writeEntity.setUserAnswer("我的答案");
        writeEntity.setWriteQuestion("1.英语教学中出现了重交际轻语法的现象\n2.这一现象发生的主要原因及其后果\n3.我的看法");
        writeEntity.setWriteImageUrl("");
        writeEntity.setWriteDirection("Directions: For this part, you are allowed 30 minutes to write an essay based on the picture below.You should start your essay with a brief description of the picture and then comment on the kid's understanding of going to school.You should write at least 120 words but no more than 180 words.");
        write.setWrite(writeEntity);
        write.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                ViewUtils.showToastShort(WriteActivity.this, "插入成功");
                Log.i(TAG, "插入成功");
            }

            @Override
            public void onFailure(int i, String s) {
                ViewUtils.showToastShort(WriteActivity.this, "插入成功");
            }
        });
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (this != null) {
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_save_write:
                Log.i(TAG, "save");
                saveData();
                break;
            case R.id.but_find_write:
                Log.i(TAG, "find");
                ViewUtils.showDialog(this, "加载中...");
                findData();
                break;
        }
    }

}
