package com.tekinarslan.material.sample.ui.module.study;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Listener;
import com.tekinarslan.material.sample.bean.Read;
import com.tekinarslan.material.sample.ui.adapter.ParalFragmentAdapter;
import com.tekinarslan.material.sample.utills.Util;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/6/1.
 */
public class ListenerActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.top_group)
    RadioGroup radioGroup;
    @Bind(R.id.rabtn_article)
    RadioButton rabutArticle;
    @Bind(R.id.rabtn_question)
    RadioButton rabutQuestion;
    @Bind(R.id.rabtn_analyze)
    RadioButton rabutAnalyze;
    @Bind(R.id.content)
    FrameLayout frameLayout;
    @Bind(R.id.but_save)
    Button save;

    private String analyze;
    private String article;
    private String audioUrl;
    private Listener listener;

    private ListenerArtFragment artFragment;
    private ListenerQuseFragment quseFragment;
    private ListenerAnalyFragment analyFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {

        ViewUtils.showDialog(this, "加载中...");
        final BmobQuery<Listener> query = new BmobQuery<Listener>();
        query.findObjects(this, new FindListener<Listener>() {
            @Override
            public void onSuccess(List<Listener> list) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(ListenerActivity.this, "查询成功");

                listener = list.get(0);
                Listener.ListenEntity listenEntity = listener.getListen();
                // 返回每个题目
                List<Listener.ListenEntity.ListenQuestionEntity> questionList = listenEntity.getListenQuestion();
                Logger.i("questionList->" + questionList.size());
                List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice = questionList.get(0).getChoice();
                List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choiceList = questionList.get(0).getChoice();
                // 返回原文
                article = listenEntity.getListenArticle();
                audioUrl = listenEntity.getListenAudioUrl();
                analyze = listenEntity.getListenAnalyze();
                Logger.i("article" + article + " audioUrl" + audioUrl + " analyze" + analyze);


//                content.setTypeface(Typeface.createFromAsset(ListenerActivity.this.getAssets(), "fonts/Roboto-Regular.ttf"));
//                content.setText(pass);
//
//                FragmentManager fm = getSupportFragmentManager();
//                adapter = new ParalFragmentAdapter(fm);
//                adapter.setList(list.get(0).getShortChoice());
//                viewPager.setAdapter(adapter);

            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(ListenerActivity.this, "查询失败");
            }
        });
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        // 设置默认界面
        setDefaultFragment();
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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (checkedId) {
                    case R.id.rabtn_article:
                        if (artFragment == null) {
                            artFragment = ListenerArtFragment.newInstance();
                            Bundle atrBundle = new Bundle();
                            atrBundle.putString("ARTICLE", article);
                            artFragment.setArguments(atrBundle);
                        }
                        transaction.replace(R.id.content, artFragment);
                        break;
                    case R.id.rabtn_question:
                        if (quseFragment == null) {
                            quseFragment = ListenerQuseFragment.newInstance();
                            Bundle quesBundle = new Bundle();
                            quesBundle.putSerializable("LISTENER", listener);
                            quseFragment.setArguments(quesBundle);
                        }
                        transaction.replace(R.id.content, quseFragment);
                        break;
                    case R.id.rabtn_analyze:
                        if (analyFragment == null) {
                            analyFragment = ListenerAnalyFragment.newInstance();
                            Bundle analyBundle = new Bundle();
                            analyBundle.putString("ANALYZE", analyze);
                            analyFragment.setArguments(analyBundle);
                        }
                        transaction.replace(R.id.content, analyFragment);
                        break;
                    default:
                        break;
                }
                transaction.commitAllowingStateLoss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveListenBmob();
            }
        });
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        analyFragment = new ListenerAnalyFragment();
        Bundle analyBundle = new Bundle();
//                            quesBundle.putSerializable("LISTENER", listener);
        analyBundle.putString("ANALYZE", "暂无解析");
        analyFragment.setArguments(analyBundle);
        transaction.replace(R.id.content, analyFragment);
        transaction.commit();
    }

    // 测试将数据提交到Bmob
    private void saveListenBmob() {
        Listener listener = new Listener();
        Listener.ListenEntity listenEntity = new Listener.ListenEntity();
        List<Listener.ListenEntity.ListenQuestionEntity> questionList = new ArrayList<>();

        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice1 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice2 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice3 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice4 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice5 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice6 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice7 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice8 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice9 = new ArrayList<>();
        List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> choice10 = new ArrayList<>();

        choice1.add(createChoice("A", "He will give the woman some tips on the game."));
        choice1.add(createChoice("B", "The woman has good reason to quit the game."));
        choice1.add(createChoice("C", "He is willing to play chess with the woman."));
        choice1.add(createChoice("D", "The woman should go on playing chess."));

        choice2.add(createChoice("A", "The man can forward the mail to Mary."));
        choice2.add(createChoice("B", "She can call Mary to take care of the mail."));
        choice2.add(createChoice("C", "Mary probably knows Sally's new address."));
        choice2.add(createChoice("D", "She would like to resume contact with Sally."));

        choice3.add(createChoice("A", "His handwriting has a unique style."));
        choice3.add(createChoice("B", "His notes are not easy to read."));
        choice3.add(createChoice("C", "He did not attend today's class."));
        choice3.add(createChoice("D", "He is very pleased to be able to help."));

        choice4.add(createChoice("A", "The man had better choose another restaurant."));
        choice4.add(createChoice("B", "The new restaurant is a perfect place for dating."));
        choice4.add(createChoice("C", "The new restaurant caught her fancy immediately."));
        choice4.add(createChoice("D", "The man has good taste in choosing the restaurant."));

        choice5.add(createChoice("A", "He has been looking forward to spring."));
        choice5.add(createChoice("B", "He has been waiting for the winter sale."));
        choice5.add(createChoice("C", "He will clean the woman's boots for spring."));
        choice5.add(createChoice("D", "He will help the woman put things away."));

        choice6.add(createChoice("A", "The woman is rather forgetful."));
        choice6.add(createChoice("B", "The man appreciates the woman's help."));
        choice6.add(createChoice("C", "The man often lends books to the woman."));
        choice6.add(createChoice("D", "The woman often works overtime at weekends."));

        choice7.add(createChoice("A", "Go to work on foot."));
        choice7.add(createChoice("B", "Take a sightseeing trip."));
        choice7.add(createChoice("C", "Start work earlier than usual."));
        choice7.add(createChoice("D", "Take a walk when the weather is nice."));

        choice8.add(createChoice("A", "The plane is going to land at another airport."));
        choice8.add(createChoice("B", "All flights have been delayed due to bad weather."));
        choice8.add(createChoice("C", "Temporary closing has disturbed the airport's operation."));
        choice8.add(createChoice("D", "The airport's management is in real need of improvement."));

        choice9.add(createChoice("A", "It specializes in safety from leaks."));
        choice9.add(createChoice("B", "It is headquartered in London."));
        choice9.add(createChoice("C", "It has a partnership with LCP."));
        choice9.add(createChoice("D", "It has a chemical processing plant."));

        choice10.add(createChoice("A", "He is Mr.Grand's friend."));
        choice10.add(createChoice("B", "He is a safety inspector."));
        choice10.add(createChoice("C", "He is a salesman."));
        choice10.add(createChoice("D", "He is a chemist."));

        questionList.add(createListenQuestion(1, choice1, "B"));
        questionList.add(createListenQuestion(2, choice2, "B"));
        questionList.add(createListenQuestion(3, choice3, "B"));
        questionList.add(createListenQuestion(4, choice4, "B"));
        questionList.add(createListenQuestion(5, choice5, "B"));
        questionList.add(createListenQuestion(6, choice6, "B"));
        questionList.add(createListenQuestion(7, choice7, "B"));
        questionList.add(createListenQuestion(8, choice8, "B"));
        questionList.add(createListenQuestion(9, choice9, "B"));
        questionList.add(createListenQuestion(10, choice10, "B"));

        listenEntity.setListenQuestion(questionList);
        listenEntity.setListenDirection(getString(R.string.listen_direction));
        listenEntity.setListenAudioUrl("http://wximg.233.com/attached/media/20151113/20151113103125_1575.mp3");
        listenEntity.setListenArticle("M:Well,did you enjoy it?\nW:Yes,I enjoy it much more than I thought I Would\nM:Really?\\nW:Yes,I do not usually go to science fiction films,I do not think they are much better than comics on film,if  you know what I mean.\\nM:Yes,sure.And a few years ago,they were certainy like that.But they are got a lot better now.\\nW:Yes,and historcial films that is what I really like,I naver miss a good film set in the Middle Ages.Oh,and love stories I naver miss one on TV.\\nM:Funnily enough.I do not like those kinds of films at all.But to come back to this one,I personally did not think it was very good.it certainly was not as good as other science fiction films I have seen.\\nW:Was not it.\\nM:No.not at all.Oh,the effects were very good.\\nW:Yes.I thought they were marvelous,especially the battle in space,incredible.\\nM:Yes.but I was going to say I thought the acting was terrble.\\nW:Yes. I suppose Jason was too good to be ture.\\nM:Well,yes,but not always.War of the Worlds was not like that,for example.Anyway,you enjiys the file.That is most important.\\nW:Yes.I did.Thanks for taking me.");
        listenEntity.setListenAnalyze("暂无评论");
        listener.setListen(listenEntity);

        listener.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Logger.i("保存成功");
                ViewUtils.showToastShort(ListenerActivity.this, "保存成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Logger.i("保存失败" + s);
                ViewUtils.showToastShort(ListenerActivity.this, "保存失败" + s);
            }
        });
    }

    //创建一个题目
    private Listener.ListenEntity.ListenQuestionEntity createListenQuestion(int pos, List<Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity> chose, String type) {
        Listener.ListenEntity.ListenQuestionEntity questionEntity = new Listener.ListenEntity.ListenQuestionEntity();
        questionEntity.setChoice(chose);
        questionEntity.setPos(pos);
        questionEntity.setType(type);
        questionEntity.setUserAnswer(" ");
        return questionEntity;
    }

    //创建一个选项
    private Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity createChoice(String chose, String content) {
        Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity choiceEntity = new Listener.ListenEntity.ListenQuestionEntity.ChoiceEntity();
        choiceEntity.setChose(chose);
        choiceEntity.setContent(content);
        return choiceEntity;
    }


}
