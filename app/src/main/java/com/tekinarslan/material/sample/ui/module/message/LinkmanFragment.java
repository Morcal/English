package com.tekinarslan.material.sample.ui.module.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.bean.Friend;
import com.tekinarslan.material.sample.bean.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lyqdhgo on 2016/4/9.
 * 联系人
 */
public class LinkmanFragment extends Fragment {
    @Bind(R.id.tv_search)
    TextView search;
    @Bind(R.id.but_getman)
    Button getMan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linkman, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initEvent();
    }

    private void initEvent() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchFriActivity.class);
                startActivity(intent);
            }
        });

        getMan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                queryFriends();
            }
        });
    }

    /**
     * 查询好友
     */
    public void queryFriends() {
        BmobQuery<Friend> query = new BmobQuery<>();
        User user = BmobUser.getCurrentUser(getContext(), User.class);

        query.addWhereEqualTo("user", user);
//        query.include("friendUser");
        query.order("-updatedAt");
        query.findObjects(getContext(), new FindListener<Friend>() {
            @Override
            public void onSuccess(List<Friend> list) {
                if (list != null && list.size() > 0) {
                    Logger.i("LinkMan Size->" + list.size());
//                    listener.onSuccess(list);
                } else {
//                    listener.onError(0, "暂无联系人");
                }
            }

            @Override
            public void onError(int i, String s) {
//                listener.onError(i, s);
            }
        });
    }
}
