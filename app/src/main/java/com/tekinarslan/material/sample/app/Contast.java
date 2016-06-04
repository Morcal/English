package com.tekinarslan.material.sample.app;

/**
 * Created by lyqdhgo on 2016/2/28.
 */
public class Contast {
    public static boolean isLogin;
    public static String objectId;
    public static final String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };
    // 公共参数
    public static String COMMONPARAM = "?appId=lls&deviceId=868201026091087&sDeviceId=868201026091087&appVer=4&token=809685e0c40d013333bf0273409c204a";
    // 流利说说客
    public static String HOSTURL = "http://apineo.llsapp.com/api/v1/podcasts?appId=lls&deviceId=868201026091087&sDeviceId=868201026091087&appVer=4&token=809685e0c40d013333bf0273409c204a";
    // 说客详情页头
    public static String SHUOKE = "http://apineo.llsapp.com/api/v1/podcasts/";
    // 说客详情页公参
    public static String SHKPARAMS = "/episodes?appId=lls&deviceId=868201026091087&sDeviceId=868201026091087&appVer=4&token=809685e0c40d013333bf0273409c204a";

    public static String SHKDETIALID = "YmM0MWYwMDAwMDAwMDAxMg%3D%3D";
    // 流利说听力
    public static String LISTENURL = "http://apineo.llsapp.com/api/v1/topics/essential?appId=lls&deviceId=868201026091087&sDeviceId=868201026091087&appVer=4&token=809685e0c40d013333bf0273409c204a";
    // 听力详情页
    public static String LISTENDETIAL = "https://apineo.llsapp.com/api/v1/topics/";
    // 每日精选图片集，摘自caodan.org,存储在七牛
    public static String[] SIFIMAGES = new String[]{
            "http://7xrfxa.com1.z0.glb.clouddn.com/1197.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1194.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1193.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1187.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1185.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1183.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1178.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1177.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1171.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1165.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1163.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1161.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1160.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1153.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1155.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1141.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1137.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1135.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1130.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1120.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1114.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1111.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1109.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1105.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1104.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1070.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1050.jpg",
            "http://7xrfxa.com1.z0.glb.clouddn.com/1045.jpg",
    };

    // CET4听力测试音频
    public static final String CET4LISTEN = "http://7xrfxa.com1.z0.glb.clouddn.com/cets20140601.mp3";

    // PHP Server Host
    // http://gaojinzhu.duapp.com/interface/user/ask?source=3&user_id=1&ask=1111
    public static final String SERVERHOST = "http://gaojinzhu.duapp.com/interface/user";

    public static void init() {
        isLogin = false;
    }

    // 轮播页动画
    /**
     * ACCORDTION_EFFECT：左右折叠效果
     */
    public static final int DEFAULT_EFFECT = 0;
    /**
     * DEFAULT_EFFECT：默认效果
     */
    public static final int ACCORDTION_EFFECT = 1;
    /**
     * DEPTH_PAGE_EFFECT：深入浅出效果
     */
    public static final int DEPTH_PAGE_EFFECT = 2;
    /**
     * DEFAULT_EFFECT：右下角进入效果
     */
    public static final int IN_RIGHT_DOWN_EFFECT = 3;
    /**
     * DEFAULT_EFFECT：右上角进入效果
     */
    public static final int IN_RIGHT_UP_EFFECT = 4;

}
