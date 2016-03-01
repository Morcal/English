package com.tekinarslan.material.sample.ui.module.community;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.Dao;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.FloatingActionButton;
import com.tekinarslan.material.sample.weight.ProgressBarCircular;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * 每日精选
 */
public class SiftFragment extends Fragment {
    private static final String TAG = SiftFragment.class.getSimpleName();
    private static final String URL = "http://www.yywz123.com/blog/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sift, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
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
                getBodyInfo(doc);
            }
        });
    }

    private void getBodyInfo(Document doc) {
        Elements elements = doc.select("div.article");
        for (Element ele : elements) {
            Element element = ele.getElementsByTag("h2").first();
            Element elementImg = ele.select("div.thumbnail").get(1);
            Element img = elementImg.getElementsByTag("a").first().getElementsByTag("img").first();
            String imgUrl = img.attr("src");
            String height = img.attr("height");
            String width = img.attr("width");
            String href = element.getElementsByTag("a").attr("href");
            String title = element.text();
            Log.i(TAG, "href:" + href + " " + "title:" + title);
            Log.i(TAG, "imgUrl:" + imgUrl + " " + "width:" + " " + width + "" + "height:" + " " + height);
        }
    }

}