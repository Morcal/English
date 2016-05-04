package com.tekinarslan.material.sample.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lyqdhgo on 2016/5/4.
 */
public class Translate extends BmobObject {

    /**
     * id : cet4151201
     * title : CET4translate151201
     * transDirection : Directions: For this part, you are allowed 30 minutes to translate a passage from Chinese into English.You should write your answer on Answer Sheet 2.
     * transChinese : 中国是世界上最古老的文明之一。构成现代世界基础的许多元素都起源于中国。中国现在拥有世界上发展最快的经济，并正经历着一次新的工业革命。中国还启动了雄心勃勃的太空探索计划，其中包括到2020年建成一个太空站。目前，中国是世界最大的出口国之一，并正在吸引大量外国投资。同时，它也在海外投资数十亿美元。2011年，中国超越日本成为世界第二大经济体。
     * transEnglish : China is one of the most ancient civilizations across the world, from which many elements that construct the foundation of the modem world are derived.Now China has the world's fastest growing economy and is experiencing a new industrial revolution.It has alSo launched an ambitious space exploration plan, including the building of a space station by 2020.Currently, being one of the largest exporters in the world, China is attracting massive foreign investment.Meanwhile, it has invested billions of dollars overseas as well.In 2011, China surpassed Japan, becoming the second largest economic entity in the world.
     * userTranslate :
     * score : 106.5
     */

    private String id;
    private String title;
    private String transDirection;
    private String transChinese;
    private String transEnglish;
    private String userTranslate;
    private double score;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTransDirection(String transDirection) {
        this.transDirection = transDirection;
    }

    public void setTransChinese(String transChinese) {
        this.transChinese = transChinese;
    }

    public void setTransEnglish(String transEnglish) {
        this.transEnglish = transEnglish;
    }

    public void setUserTranslate(String userTranslate) {
        this.userTranslate = userTranslate;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTransDirection() {
        return transDirection;
    }

    public String getTransChinese() {
        return transChinese;
    }

    public String getTransEnglish() {
        return transEnglish;
    }

    public String getUserTranslate() {
        return userTranslate;
    }

    public double getScore() {
        return score;
    }
}
