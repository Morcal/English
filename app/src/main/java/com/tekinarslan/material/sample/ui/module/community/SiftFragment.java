package com.tekinarslan.material.sample.ui.module.community;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.Article;
import com.tekinarslan.material.sample.bean.Image;
import com.tekinarslan.material.sample.bean.User;
import com.tekinarslan.material.sample.ui.adapter.ArticleAdapter;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.FloatingActionButton;
import com.tekinarslan.material.sample.weight.ProgressBarCircular;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 每日精选
 */
public class SiftFragment extends Fragment {
    private static final String TAG = SiftFragment.class.getSimpleName();
    private static final String URL = "http://www.yywz123.com/blog/";
    private static final String DEFAULT_AVATAR = "https://cms-assets.tutsplus.com/uploads/users/41/posts/25951/image/material-design-3.jpg";
    private List<Article> articles1 = new ArrayList<>();
    private Article article;
    private User user;
    private Image image;
    private ArticleAdapter adapter;
    private String type;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.list_articl)
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sift, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new ArticleAdapter(getActivity());
        initData(URL);
    }

    private void initData(String type) {
        ViewUtils.showDialog(getActivity(), "Loading");
        Dao.getEntity(type, new Dao.EntityListener() {
            @Override
            public void onError() {
                ViewUtils.showToastShort(getActivity(), getString(R.string.error));
                ViewUtils.hideDialog();
            }

            @Override
            public void onSuccess(String result) {
                ViewUtils.hideDialog();
                Document doc = Jsoup.parse(result);
                List<Article> list = getBodyInfo(doc);
                Log.i(TAG, "size: " + list.size());
                if (list.size() != 0 && list != null) {
//                    articles1.addAll(list);
                    adapter.setItems(list);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }
            }
        });
    }

    private List<Article> getBodyInfo(Document doc) {
        articles1 = new ArrayList<>();
        article = new Article();
        user = new User();
        image = new Image();

        Elements eleDates = doc.select("div.post_date");
        for (Element eleDate : eleDates) {
            String year = eleDate.select("span.date_y").text();
            String mouth = eleDate.select("span.date_m").text();
            String day = eleDate.select("span.date_d").text();
            String date = year + " " + mouth + " " + day;
            Log.i(TAG, "date:" + date);
            article.setPublishTime(date);
            articles1.add(article);
        }

        Elements elements = doc.select("div.article");
        for (Element ele : elements) {
            Element element = ele.getElementsByTag("h2").first();
            Element elementImg = ele.select("div.thumbnail").get(1);
            Element img = elementImg.getElementsByTag("a").first().getElementsByTag("img").first();

            String info = ele.select("div.info").text();
            String desc = ele.select("div.entry_post").first().getElementsByTag("span").text();
            String imgUrl = img.attr("src");
            String height = img.attr("height");
            String width = img.attr("width");
            String href = element.getElementsByTag("a").attr("href");
            String title = element.text();
            String[] infos = info.split("\\|");
            String author = infos[0];
            String au = author.split("：")[1];
            String type = infos[1];
            String ty = type.split("：")[1];
            String read = infos[2];
            String re = read.split("：")[1];
            Log.i(TAG, "author: " + au + " type: " + ty + " read: " + re);
            Log.i(TAG, "href:" + href + " " + "title:" + title);
            Log.i(TAG, "imgUrl:" + imgUrl + " " + "width:" + " " + width + "" + "height:" + " " + height);
            Log.i(TAG, "desc:" + desc);
            Log.i(TAG, "info:" + info);
            article.setShareUrl(href); // 分享链接
            article.setTitle(title); // 标题
            article.setSummary(desc); // 描述
            user.setAvatar(DEFAULT_AVATAR); //用户头像
//            user.setNickname(nickName); //用户名
//            user.setCount(count);
            article.setPublisher(user);
            image.setUrl(imgUrl);
//            image.setHeight(Integer.parseInt(height));
//            image.setWidth(Integer.parseInt(width));
            article.setCover(image);
            articles1.add(article);
        }
        return articles1;
    }
}