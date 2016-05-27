package com.tekinarslan.material.sample.ui.module.study;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Read;
import com.tekinarslan.material.sample.bean.ResultAskList;
import com.tekinarslan.material.sample.bean.Write;
import com.tekinarslan.material.sample.ui.adapter.ParalFragmentAdapter;
import com.tekinarslan.material.sample.utills.Util;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/5/17.
 */
public class ReadActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_content)
    TextView content;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    ParalFragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        ViewUtils.showDialog(this, "加载中...");
        final BmobQuery<Read> query = new BmobQuery<Read>();
        query.findObjects(this, new FindListener<Read>() {
            @Override
            public void onSuccess(List<Read> list) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(ReadActivity.this, "查询成功");

                String passage = list.get(0).getPassage();
                String pass = Util.ToDBC(passage);
                content.setTypeface(Typeface.createFromAsset(ReadActivity.this.getAssets(), "fonts/Roboto-Regular.ttf"));
                content.setText(pass);

                FragmentManager fm = getSupportFragmentManager();
                adapter = new ParalFragmentAdapter(fm);
                adapter.setList(list.get(0).getShortChoice());
                viewPager.setAdapter(adapter);

//                Read read = list.get(0);
//                Logger.i("Read size-> " + list.size() + "\n" + read.toString());
//                List<Read.ShortChoiceEntity> shortEntities = read.getShortChoice();
//                for (Read.ShortChoiceEntity entity : shortEntities) {
//                    int pos = entity.getPos();
//                    String question = entity.getQuestion();
//                    Logger.i("pos->" + pos + " question->" + question);
//                    List<Read.ShortChoiceEntity.ChoiceEntity> choses = entity.getChoice();
//                    for (Read.ShortChoiceEntity.ChoiceEntity choice : choses) {
//                        Logger.i(choice.getChose() + " " + choice.getContent());
//                    }
//
//                }
//                String passage = read.getPassage();
//                content.setText(passage);
            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(ReadActivity.this, "查询失败");
            }
        });

    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
//        FragmentManager fm = getSupportFragmentManager();
//        adapter = new ParalFragmentAdapter(fm);
//        viewPager.setAdapter(adapter);
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

//        content.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveReadToBmob();
//            }
//        });
    }

    private void saveReadToBmob() {
        Read read = new Read();
        List<Read.ShortChoiceEntity> shortChices = new ArrayList<>();

        List<Read.ShortChoiceEntity.ChoiceEntity> chose1 = new ArrayList<>();
        List<Read.ShortChoiceEntity.ChoiceEntity> chose2 = new ArrayList<>();
        List<Read.ShortChoiceEntity.ChoiceEntity> chose3 = new ArrayList<>();
        List<Read.ShortChoiceEntity.ChoiceEntity> chose4 = new ArrayList<>();
        List<Read.ShortChoiceEntity.ChoiceEntity> chose5 = new ArrayList<>();

        chose1.add(createChoice("A", "It will delay the occurrence of skin cancer."));
        chose1.add(createChoice("B", "It will protect them from sunburn."));
        chose1.add(createChoice("C", "It will keep their skin smooth and fair."));
        chose1.add(createChoice("D", "It will work for people of any skin color."));

        chose2.add(createChoice("A", "It is ineffective in preventing melanomas."));
        chose2.add(createChoice("B", "It is ineffective in case of intense sunlight."));
        chose2.add(createChoice("C", "It is ineffective with long-term exposure."));
        chose2.add(createChoice("D", "It is ineffective for people with fair skin."));

        chose3.add(createChoice("A", "Sunscreen should be applied alongside other protection measures."));
        chose3.add(createChoice("B", "High-risk people benefit the most from the application of sunscreen."));
        chose3.add(createChoice("C", "Irregular application of sunscreen does women more harm than good."));
        chose3.add(createChoice("D", "Daily application of sunscreen helps reduce the incidence of melanomas."));

        chose4.add(createChoice("A", "It misleads people to rely on sunscreen for protection."));
        chose4.add(createChoice("B", "It helps people to select the most effective sunscreen."));
        chose4.add(createChoice("C", "It is not based on direct observation of the subjects."));
        chose4.add(createChoice("D", "It confirms the results of the first Australian study."));

        chose5.add(createChoice("A", "Using both covering up and sunscreen."));
        chose5.add(createChoice("B", "Staying in the shade whenever possible."));
        chose5.add(createChoice("C", "Using covering up instead of sunscreen."));
        chose5.add(createChoice("D", "Applying the right amount of sunscreen."));

        shortChices.add(createShortChoice(56, "What is people's common expectation of a high-factor sunscreen?", chose1, "B"));
        shortChices.add(createShortChoice(57, "What does the research in Nature say about a high-factor sunscreen?", chose2, "B"));
        shortChices.add(createShortChoice(58, "What do we learn from the 2011 Australian study of 1,621 people?", chose3, "B"));
        shortChices.add(createShortChoice(59, "What does the author say about the second Australian study?", chose4, "B"));
        shortChices.add(createShortChoice(60, "What does the author suggest to reduce melanoma rates?", chose5, "B"));

        read.setPassage(getResources().getString(R.string.passage));
        read.setShortChoice(shortChices);
        read.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Logger.i("保存成功");
                ViewUtils.showToastShort(ReadActivity.this, "保存成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Logger.i("保存失败");
                ViewUtils.showToastShort(ReadActivity.this, "保存失败");
            }
        });
    }

    //创建一个题目
    private Read.ShortChoiceEntity createShortChoice(int pos, String question, List<Read.ShortChoiceEntity.ChoiceEntity> chose, String type) {
        Read.ShortChoiceEntity shortChoiceEntity = new Read.ShortChoiceEntity();
        shortChoiceEntity.setChoice(chose);
        shortChoiceEntity.setPos(pos);
        shortChoiceEntity.setQuestion(question);
        shortChoiceEntity.setType(type);
        shortChoiceEntity.setUserAnswer(" ");
        return shortChoiceEntity;
    }

    //创建一个选项
    private Read.ShortChoiceEntity.ChoiceEntity createChoice(String chose, String content) {
        Read.ShortChoiceEntity.ChoiceEntity choiceEntity = new Read.ShortChoiceEntity.ChoiceEntity();
        choiceEntity.setChose(chose);
        choiceEntity.setContent(content);
        return choiceEntity;
    }


}
