package com.tekinarslan.material.sample.ui.module.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Translate;
import com.tekinarslan.material.sample.bean.Write;
import com.tekinarslan.material.sample.ui.adapter.TranslateListAdapter;
import com.tekinarslan.material.sample.ui.adapter.WriteListAdapter;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class TranslateListActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.listview)
    ListView listView;

    private TranslateListAdapter adapter;
    private String id;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranlatelist);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        adapter = new TranslateListAdapter(this);
        ViewUtils.showDialog(this, "加载中...");
        final BmobQuery<Translate> query = new BmobQuery<Translate>();
        query.findObjects(this, new FindListener<Translate>() {
            @Override
            public void onSuccess(List<Translate> list) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(TranslateListActivity.this, "查询成功");
                Logger.i("write size-> " + list.size());
                adapter.setItems(list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(TranslateListActivity.this, "查询失败" + s);
            }
        });
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Translate translate = (Translate) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString("ID", translate.getId());
                bundle.putString("TITLE", translate.getTitle());
                bundle.putString("DIRECTION", translate.getTransDirection());
                bundle.putString("CHINESE", translate.getTransChinese());
                bundle.putString("ENGLISH", translate.getTransEnglish());
                Logger.i("Title->" + translate.getTitle());
                Intent intent = new Intent(TranslateListActivity.this, TranslateActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
