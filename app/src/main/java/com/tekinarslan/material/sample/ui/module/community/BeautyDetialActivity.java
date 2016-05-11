package com.tekinarslan.material.sample.ui.module.community;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyqdhgo on 2016/5/10.
 */
public class BeautyDetialActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_article)
    TextView tvArticle;
    @Bind(R.id.backdrop)
    SimpleDraweeView backdrop;

    private String theme;
    private String imageUrl;
    private String article;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_detial);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initView() {
        toolbar.setTitle(theme);
        toolbar.setNavigationIcon(R.drawable.back);
        tvArticle.setText(article);
        setCover(imageUrl);
    }

    private void setCover(String imageUrl) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                .setResizeOptions(
                        new ResizeOptions(500, 500))
                .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(backdrop.getController())
                .setAutoPlayAnimations(true)
                .build();
        backdrop.setController(draweeController);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        theme = bundle.getString("THEME");
        imageUrl = bundle.getString("IMAGEURL");
        article = bundle.getString("ARTICLE");
        Logger.i("theme->" + theme + " arcicle->" + article);
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
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
