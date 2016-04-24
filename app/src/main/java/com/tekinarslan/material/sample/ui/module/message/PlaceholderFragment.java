package com.tekinarslan.material.sample.ui.module.message;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.orhanobut.logger.Logger;
import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.app.EnglishApplication;

/**
 * Created by lyqdhgo on 2016/4/8.
 * 签到页面
 */
public class PlaceholderFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RelativeLayout payInfo;
    private TextView butQiDao;
    private TextView qdnum;
    private TextView tvlocation;
    private Animation anim;
    private boolean isQianDao = false;
    // 与定位有关
    private LocationService locationService;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    if (isQianDao) {
                        startAnimation();
                        butQiDao.setText("签到成功");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.i("PlaceholderFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_imitate_qiandao, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        payInfo = (RelativeLayout) view.findViewById(R.id.rl_payInfo);
        qdnum = (TextView) view.findViewById(R.id.tv_qdnum);
        butQiDao = (TextView) view.findViewById(R.id.but_qiandao);
        tvlocation = (TextView) view.findViewById(R.id.tv_location);
        Logger.i("sectionNumber->" + sectionNumber);

    }

    @Override
    public void onStart() {
        super.onStart();
        // -----------location config ------------
        locationService = ((EnglishApplication) getActivity().getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //监听配置
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        initEvent();
    }

    private void initEvent() {
        butQiDao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开启定位
                if (!isQianDao) {
                    int count = Integer.parseInt(qdnum.getText().toString().trim());
                    count++;
                    locationService.start();// 定位SDK
                    qdnum.setText(count + "");
                } else {
                    locationService.stop();
                }
                newThread();
                isQianDao = true;
            }
        });
    }

    /**
     * 测试用的,开启子线程
     */
    private void newThread() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);

            }
        }).start();
    }

    /**
     * 启动打印小票动画
     */
    private void startAnimation() {
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_in);
        anim.setDuration(1000);
        anim.setFillAfter(true);
        payInfo.startAnimation(anim);
    }

    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str) {
        Logger.i("请求字符串->" + str);
    }

    @Override
    public void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nerror code : ");
                sb.append(location.getLocType());
                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");
                sb.append(location.getLongitude());
                sb.append("\nradius : ");
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");
                sb.append(location.getCityCode());
                sb.append("\ncity : ");
                sb.append(location.getCity());
                sb.append("\nDistrict : ");
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");
                sb.append(location.getStreet());
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\nDescribe: ");
                sb.append(location.getLocationDescribe());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());
                sb.append("\nPoi: ");
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    sb.append("\noperationers : ");
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                tvlocation.setText(location.getLocationDescribe());
                logMsg(sb.toString());
            }
        }

    };
}
