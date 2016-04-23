package com.tekinarslan.material.sample.ui.module.study;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Write;
import com.tekinarslan.material.sample.ui.adapter.WriteListAdapter;
import com.tekinarslan.material.sample.utills.UIUtil;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class WriteListActivity extends AppCompatActivity {
    private static final String TAG = WriteListActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.list_write)
    ListView listWrite;

    private WriteListAdapter adapter;
    private String id;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listwrite);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        adapter = new WriteListAdapter(this);
        ViewUtils.showDialog(this, "加载中...");
        final BmobQuery<Write> query = new BmobQuery<Write>();
        query.findObjects(this, new FindListener<Write>() {
            @Override
            public void onSuccess(List<Write> list) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(WriteListActivity.this, "查询成功");
                Logger.i("write size-> " + list.size());
                adapter.setItems(list);
                for (int i = 0; i < list.size(); i++) {
                    Write write = list.get(i);
                    if (write != null) {
                        title = write.getTitle();
                        id = write.getId();
                    }
                    Logger.i("id->" + id + "title->" + title);
                }

                listWrite.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.hideDialog();
                ViewUtils.showToastShort(WriteListActivity.this, "查询失败");
            }
        });
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);

        listWrite.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1));
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listWrite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Write write = (Write) parent.getItemAtPosition(position);
                String writeId = write.getId();
                Write.WriteEntity entity = write.getWrite();
                String direction = entity.getWriteDirection();
                String question = entity.getWriteQuestion();
                String imageUrl = entity.getWriteImageUrl();
                Logger.i("writeId-->" + writeId);
                Bundle bundle = new Bundle();
                bundle.putString("ID",writeId);
                bundle.putString("DIRECTION", direction);
                bundle.putString("QUESTION", question);
                bundle.putString("IMAGEURL", imageUrl);
                Intent intent = new Intent(WriteListActivity.this, WriteActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
