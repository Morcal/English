package com.tekinarslan.material.sample.ui.module.study;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.CourseCetEntity;
import com.tekinarslan.material.sample.bean.SpokenEntity;
import com.tekinarslan.material.sample.utills.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 */
public class CourseListFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    public int flag = -1;
    private int mPage;
    private SpokenAdapter adapter;

    @Bind(R.id.listview)
    ListView listView;

    public static CourseListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CourseListFragment pageFragment = new CourseListFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        BmobQuery<CourseCetEntity> query = new BmobQuery<CourseCetEntity>();
        query.findObjects(getActivity(), new FindListener<CourseCetEntity>() {
            @Override
            public void onSuccess(List<CourseCetEntity> list) {
                Logger.i("Course list size->" + list.size());
                ViewUtils.showToastShort(getActivity(), "查询成功");
                adapter = new SpokenAdapter(getActivity(), list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(int i, String s) {
                Logger.i("Error:" + s);
                ViewUtils.showToastShort(getActivity(), "查询失败");
            }
        });
    }

    private void initView() {

    }

    private void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Long itemID = parent.getItemIdAtPosition(position);
                Logger.i("ItemID->" + itemID);
                // 点击之前把上次的焦点清掉
                TextView textView = (TextView) view.findViewById(R.id.tv_spokename);
                ImageView playspoken = (ImageView) view.findViewById(R.id.iv_playspokenm);
                if (flag >= 0) {
                    View oldView = listView.getChildAt(flag);
                    TextView oldText = (TextView) oldView.findViewById(R.id.tv_spokename);
                    ImageView oldPlay = (ImageView) oldView.findViewById(R.id.iv_playspokenm);
                    oldText.setTextColor(getResources().getColor(R.color.text_black));
                    oldPlay.setBackground(getResources().getDrawable(R.drawable.play_normal));
                }
                Logger.i("Select view->" + parent.getSelectedView());
                CourseCetEntity entity = (CourseCetEntity) parent.getItemAtPosition(position);
                Logger.i("select item：" + entity.getId());
                String title = entity.getTitle();
                String audioUrl = entity.getAudioUrl();
                Logger.i("title:" + title + " audioUrl->" + audioUrl);

                textView.setTextColor(getResources().getColor(R.color.material_teal));
                playspoken.setBackground(getResources().getDrawable(R.drawable.play_press));
                flag = position;
                mCallback.onSpokenSelected(title, audioUrl);
            }
        });
    }

    OnHeadlineSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        void onSpokenSelected(String title, String audioUrl);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnHeadlineSelectedListener) context;
    }

    class SpokenAdapter extends BaseAdapter {

        private List<CourseCetEntity> list;
        private Context context;

        public SpokenAdapter(Context context, List<CourseCetEntity> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_spoken, null);
                holder.spokenName = (TextView) convertView.findViewById(R.id.tv_spokename);
                holder.spokenPlay = (ImageView) convertView.findViewById(R.id.iv_playspokenm);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            CourseCetEntity entity = (CourseCetEntity) getItem(position);
            holder.spokenName.setText(entity.getTitle());
            return convertView;
        }

        class ViewHolder {
            TextView spokenName;
            ImageView spokenPlay;
        }
    }

}



