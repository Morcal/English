package com.tekinarslan.material.sample.ui.module.own;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/2/29.
 */
public class EditNameActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.iv_delete)
    ImageView delete;
    @Bind(R.id.tv_save)
    TextView save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_edit_name);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
    }

    private void initEvent() {
        delete.setOnClickListener(this);
        save.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("NICKNAME", etName.getText().toString());
                setResult(1, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_delete:
                etName.setText("");
                break;
            case R.id.tv_save:
                //隐藏软键盘，保存数据到bmob,并向前页返回数据

                break;
            default:
                break;
        }
    }
}
