package com.tekinarslan.material.sample.ui.module.community;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Words;
import com.tekinarslan.material.sample.utills.AppToast;
import com.tekinarslan.material.sample.utills.ViewUtils;
import com.tekinarslan.material.sample.weight.weight.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lyqdhgo on 2016/3/29.
 */
public class WordsFragment extends Fragment implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener {
    private static final String TAG = WordsFragment.class.getSimpleName();
    @Bind(R.id.swipe_view)
    SwipeFlingAdapterView swipeView;

    private int cardWidth;
    private int cardHeight;
    private boolean isFlavor = false;
    private InnerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_words, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
        queryToBmob();
    }

    private void queryToBmob() {
        BmobQuery<Words> query = new BmobQuery<Words>();
        query.findObjects(getActivity(), new FindListener<Words>() {
            @Override
            public void onSuccess(List<Words> list) {
                ViewUtils.showToastShort(getActivity(), "查询成功");
                Log.i(TAG, "Size->:" + list.size());
                for (int i = 0; i < list.size(); i++) {
                    Words word = list.get(i);
                    Log.i(TAG, "imageUrl->:" + word.getImgUrl());
                }
                adapter.addAll(list);
            }

            @Override
            public void onError(int i, String s) {
                ViewUtils.showToastShort(getActivity(), "查询出错+" + s);
            }
        });
    }

    private void saveToBmob() {
        Words word = new Words();
        word.setImgUrl("http://oimagec1.ydstatic.com/image?product=dict-treasury&id=8610474441778323756&w=400&h=340\n");
        word.setEnglish("Are you tired of exaggerated Hollywood action movies\nthat defy common sense?");
        word.setChinese("你是否厌倦了那些违背常识、过分夸张的好莱坞动作片？");
        word.setWord("defy");
        word.setYin("[dɪ'faɪ]");
        word.setTrans("vt.违抗，反对，对抗；不服从；");

        word.save(getActivity(), new SaveListener() {
            @Override
            public void onSuccess() {
                ViewUtils.showToastShort(getActivity(), "保存成功");
            }

            @Override
            public void onFailure(int i, String s) {
                ViewUtils.showToastShort(getActivity(), "保存失败");
            }
        });
    }

    private void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 18 * density));
        cardHeight = (int) (dm.heightPixels - (338 * density));
        //swipeView.setIsNeedSwipe(true);
        swipeView.setFlingListener(this);
        swipeView.setOnItemClickListener(this);

        adapter = new InnerAdapter();
        swipeView.setAdapter(adapter);
    }


    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {
        if (v.getTag() instanceof ViewHolder) {
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            ViewHolder vh = (ViewHolder) v.getTag();
            View child = vh.portraitView;
            Rect outRect = new Rect();
            child.getGlobalVisibleRect(outRect);
            if (outRect.contains(x, y)) {
//                AppToast.show(getActivity(), "click 大图");
            } else {
                outRect.setEmpty();
                child = vh.ivFlovar;
                child.getGlobalVisibleRect(outRect);
                if (outRect.contains(x, y)) {
//                    AppToast.show(getActivity(), "喜欢");
                    if (isFlavor) {
                        vh.ivFlovar.setImageResource(R.drawable.icon_bar_fav_active);
                        isFlavor = false;
                    } else {
                        vh.ivFlovar.setImageResource(R.drawable.icon_bar_faved);
                        isFlavor = true;

                    }
                }
            }
        }
    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
//        AppToast.show(getActivity(), "swipe left card");
        Log.i(TAG, "swipe left card");
    }

    @Override
    public void onRightCardExit(Object dataObject) {
//        AppToast.show(getActivity(), "swipe right card");
        Log.i(TAG, "swipe right card");

    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            queryToBmob();
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {

    }

    private class InnerAdapter extends BaseAdapter {

        ArrayList<Words> objs;

        public InnerAdapter() {
            objs = new ArrayList<>();
        }

        public void addAll(Collection<Words> collection) {
            if (isEmpty()) {
                objs.addAll(collection);
                notifyDataSetChanged();
            } else {
                objs.addAll(collection);
            }
        }

        public void clear() {
            objs.clear();
            notifyDataSetChanged();
        }

        public boolean isEmpty() {
            return objs.isEmpty();
        }

        public void remove(int index) {
            if (index > -1 && index < objs.size()) {
                objs.remove(index);
                notifyDataSetChanged();
            }
        }


        @Override
        public int getCount() {
            return objs.size();
        }

        @Override
        public Words getItem(int position) {
            if (objs == null || objs.size() == 0) return null;
            return objs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // TODO: getView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Words word = getItem(position);
            if (convertView == null)
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_new_items, parent, false);
            ViewHolder holder = new ViewHolder();
            convertView.setTag(holder);
            convertView.getLayoutParams().width = cardWidth;
            holder.portraitView = (SimpleDraweeView) convertView.findViewById(R.id.portrait);
            holder.portraitView.getLayoutParams().height = cardHeight;
            holder.tvEnglish = (TextView) convertView.findViewById(R.id.tv_english);
            holder.tvEnglish.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/FZSongKeBenXiuKaiS-R-GB.TTF"));
            holder.tvChinese = (TextView) convertView.findViewById(R.id.tv_chinese);
            holder.tvWord = (TextView) convertView.findViewById(R.id.tv_word);
            holder.tvBiao = (TextView) convertView.findViewById(R.id.tv_biao);
            holder.tvTrans = (TextView) convertView.findViewById(R.id.tv_trans);
            holder.ivShare = (ImageView) convertView.findViewById(R.id.im_share);
            holder.ivFlovar = (ImageView) convertView.findViewById(R.id.im_flavor);

            holder.portraitView.setImageURI(Uri.parse(word.getImgUrl()));
            holder.tvEnglish.setText(word.getEnglish());
            holder.tvChinese.setText(word.getChinese());
            holder.tvWord.setText(word.getWord());
            holder.tvBiao.setText(word.getYin());
            holder.tvTrans.setText(word.getTrans());

            final CharSequence no = "暂无";
            return convertView;
        }
    }

    private static class ViewHolder {
        SimpleDraweeView portraitView;
        TextView tvEnglish;
        TextView tvChinese;
        TextView tvWord;
        TextView tvBiao;
        TextView tvTrans;
        ImageView ivShare;
        ImageView ivFlovar;
    }
}
