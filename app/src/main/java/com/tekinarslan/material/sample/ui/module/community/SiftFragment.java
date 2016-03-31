package com.tekinarslan.material.sample.ui.module.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Contast;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.bean.Article;
import com.tekinarslan.material.sample.bean.Image;
import com.tekinarslan.material.sample.bean.User;
import com.tekinarslan.material.sample.ui.adapter.ArticleAdapter;
import com.tekinarslan.material.sample.utills.ViewUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 每日精选
 */
public class SiftFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = SiftFragment.class.getSimpleName();
    private static final String URL = "http://www.yywz123.com/blog/";
    private static final String DEFAULT_AVATAR = "https://cms-assets.tutsplus.com/uploads/users/41/posts/25951/image/material-design-3.jpg";
    private static final String TITLE = "title";
    private static final String DESC = "desc";
    private List<Article> articles1 = new ArrayList<>();
    private Article article;
    private User user;
    private Image image;
    private ArticleAdapter adapter;
    private String type;
    private String desc;
    private String title;
    private int pageCount = 1;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.list_articl)
    ListView listView;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_sift, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeColors(R.color.material_deep_teal_500, R.color.blue, R.color.red);
        adapter = new ArticleAdapter(getActivity());
        initData(URL);
        initEvent();
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
                    setListImage(list);
                    adapter.setItems(list);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                    swipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    /***
     * 设置文章封面图
     * @param list
     */
    private void setListImage(List<Article> list) {
        int size = list.size();
        // 随机产生10张图片数组
        String[] images = getImages(size);
        for (int i = 0; i < size; i++) {
            list.get(i).getCover().setUrl(images[i]);
        }
    }

    private String[] getImages(int size) {
        String[] images= Contast.SIFIMAGES;
        String[] result = new String[10];

        boolean r[] = new boolean[images.length];
        Random random = new Random();
        int m = size; // 要随机取的元素个数
        if (m > images.length || m < 0)
            return images;
        int n = 0;
        while (true) {
            int temp = random.nextInt(images.length);
            if (!r[temp]) {
                if (n == m) // 取到足量随机数后退出循环
                    break;
                n++;
                System.out.println("得到的第" + n + "个随机数为：" + images[temp]);
                result[n - 1] = images[temp];
                r[temp] = true;
            }
        }
        return result;
    }

    private void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) parent.getItemAtPosition(position);
                String shareUrl = article.getShareUrl();
                String title = article.getTitle();
                String desc = article.getSummary();
                String url = article.getShareUrl();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
//                intent.putExtra(TITLE, title);
//                intent.putExtra(DESC, desc);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });
    }

    private List<Article> getBodyInfo(Document doc) {
        articles1 = new ArrayList<>();
        Element eleMain = doc.select("div.main").first();
        Elements elements = eleMain.getElementsByTag("li");
        for (Element e : elements) {
            Log.i(TAG, e.toString());
            article = new Article();
            user = new User();
            image = new Image();
            // 获取时间
            Element eleDate = e.select("div.post_date").first();
            String year = eleDate.select("span.date_y").text();
            String mouth = eleDate.select("span.date_m").text();
            String day = eleDate.select("span.date_d").text();
            String date = year + " " + mouth + " " + day;
            Log.i(TAG, "date:" + date);
            article.setPublishTime(date);
            // 获取文章信息
            Element ele = e.select("div.article").first();
            Element element = ele.getElementsByTag("h2").first();
            Element elementImg = ele.select("div.thumbnail").get(1);
            Element img = elementImg.getElementsByTag("a").first().getElementsByTag("img").first();

            String info = ele.select("div.info").text();
            desc = ele.select("div.entry_post").first().getElementsByTag("span").text();
            String imgUrl = img.attr("src");
            String height = img.attr("height");
            String width = img.attr("width");
            String href = element.getElementsByTag("a").attr("href");
            title = element.text();
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
            article.setType("Type:" + ty);
            article.setReadViews("PViews:" + re);
            user.setAvatar(DEFAULT_AVATAR); //用户头像
            user.setNickname("Author:" + au);
            article.setPublisher(user);
            image.setUrl(imgUrl);
            article.setCover(image);
            articles1.add(article);
        }
        return articles1;
    }

    @Override
    public void onRefresh() {
        pageCount++;
        Dao.getEntity(URL + "page/" + pageCount, new Dao.EntityListener() {
            @Override
            public void onError() {
                ViewUtils.showToastShort(getActivity(), getString(R.string.error));
            }

            @Override
            public void onSuccess(String result) {
                Document doc = Jsoup.parse(result);
                List<Article> list = getBodyInfo(doc);
                if (list.size() != 0 && list != null) {
//                    articles1.addAll(list);
                    setListImage(list);
                    list.addAll(list);
                    adapter.setItems(articles1);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                    swipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) view.getParent()).removeView(view);
    }
}