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

import com.orhanobut.logger.Logger;
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
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by lyqdhgo on 2016/4/5.
 */
public class WriteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = WriteActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.but_save_write)
    Button save;
    @Bind(R.id.tv_writedir)
    TextView writeDire;
    @Bind(R.id.tv_writeque)
    TextView writeQues;
    @Bind(R.id.tv_submit)
    TextView submit;
    @Bind(R.id.et_write)
    EditText write;
    @Bind(R.id.iv_image)
    ImageView image;

    String id;
    String direction;
    String question;
    String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("ID");
        direction = bundle.getString("DIRECTION");
        question = bundle.getString("QUESTION");
        imageUrl = bundle.getString("IMAGEURL");
        Logger.i("id:" + id + " dir:" + direction + " que:" + question + " img:" + imageUrl);
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        save.setOnClickListener(this);
        if (!direction.isEmpty()) {
            writeDire.setText(direction);
            writeDire.setVisibility(View.VISIBLE);
        }
        if (!question.isEmpty()) {
            writeQues.setText(question);
            writeQues.setVisibility(View.VISIBLE);
        }
        if (!imageUrl.isEmpty()) {
            image.setVisibility(View.VISIBLE);
            UIUtil.setAvatar(imageUrl, image,1000,360);
        }
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
                    // 返回上级是也提交数据
                    if(!(write.getText().toString().isEmpty())){
                        ViewUtils.showDialog(WriteActivity.this, "Saving");
                        getObjectId();
                    }
                    finish();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtils.showDialog(WriteActivity.this, "Saving");
                getObjectId();
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
            default:
                break;
        }
    }

    private void submiToBmob(String objectId, Write writ, Write.WriteEntity entity) {
        String userAnswer = write.getText().toString().trim();
//        Write write = new Write();
//        Write.WriteEntity entity = write.getWrite();
//        Logger.i("submiToBmob:"+" Write->" + write + " entity->" + entity);
        entity.setUserAnswer(userAnswer);
        writ.setWrite(entity);
//        String objectId = getObjectId();
        Logger.i("submiToBmob-->" + " objectId:" + objectId + " userAnswer:" + userAnswer);
        writ.update(this, objectId, new UpdateListener() {
            @Override
            public void onSuccess() {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(WriteActivity.this, "提交成功");
            }

            @Override
            public void onFailure(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(WriteActivity.this, "提交失败" + s);
            }
        });
    }

    public void getObjectId() {
        BmobQuery<Write> query = new BmobQuery<Write>();
        final String[] objectId = new String[1];
        query.addWhereEqualTo("id", id);
        query.findObjects(this, new FindListener<Write>() {
            @Override
            public void onSuccess(List<Write> list) {
                ViewUtils.showToastShort(WriteActivity.this, "查询objId成功");
                Write write = list.get(0);
                objectId[0] = write.getObjectId();
                Write.WriteEntity entity = write.getWrite();
                Logger.i("getObjectId:" + " Write->" + write + " entity->" + entity);
                Logger.i("FindId成功 " + "ObjectID-->" + objectId[0] + " ques-->" + entity.getWriteQuestion() + " dir-->" + entity.getWriteDirection());
                submiToBmob(objectId[0], write, entity);
            }

            @Override
            public void onError(int i, String s) {
                Logger.i("FindId失败");
                ViewUtils.showToastShort(WriteActivity.this, "查询objId失败" + s);
            }
        });
    }
}
