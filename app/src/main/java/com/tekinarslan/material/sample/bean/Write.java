package com.tekinarslan.material.sample.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lyqdhgo on 2016/4/5.
 */
public class Write extends BmobObject {

    /**
     * id : cet4150602
     * title : cet4write150602
     * write : {"score":106.5,"standardAnswer":"标准答案","userAnswer":"我的答案","writeQuestion":"Why am I going to school if my phone already knows everything?","writeImageUrl":"http://uploadfiles.nowcoder.com/app/banner/alogrithm_course_banner.png","writeDirection":"Directions: For this part, you are allowed 30 minutes to write an essay based on the picture below.You should start your essay with a brief description of the picture and then comment on the kid's understanding of going to school.You should write at least 120 words but no more than 180 words."}
     */

    private String id;
    private String title;
    /**
     * score : 106.5
     * standardAnswer : 标准答案
     * userAnswer : 我的答案
     * writeQuestion : Why am I going to school if my phone already knows everything?
     * writeImageUrl : http://uploadfiles.nowcoder.com/app/banner/alogrithm_course_banner.png
     * writeDirection : Directions: For this part, you are allowed 30 minutes to write an essay based on the picture below.You should start your essay with a brief description of the picture and then comment on the kid's understanding of going to school.You should write at least 120 words but no more than 180 words.
     */

    private WriteEntity write;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWrite(WriteEntity write) {
        this.write = write;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public WriteEntity getWrite() {
        return write;
    }

    public static class WriteEntity {
        private double score;
        private String standardAnswer;
        private String userAnswer;
        private String writeQuestion;
        private String writeImageUrl;
        private String writeDirection;

        public void setScore(double score) {
            this.score = score;
        }

        public void setStandardAnswer(String standardAnswer) {
            this.standardAnswer = standardAnswer;
        }

        public void setUserAnswer(String userAnswer) {
            this.userAnswer = userAnswer;
        }

        public void setWriteQuestion(String writeQuestion) {
            this.writeQuestion = writeQuestion;
        }

        public void setWriteImageUrl(String writeImageUrl) {
            this.writeImageUrl = writeImageUrl;
        }

        public void setWriteDirection(String writeDirection) {
            this.writeDirection = writeDirection;
        }

        public double getScore() {
            return score;
        }

        public String getStandardAnswer() {
            return standardAnswer;
        }

        public String getUserAnswer() {
            return userAnswer;
        }

        public String getWriteQuestion() {
            return writeQuestion;
        }

        public String getWriteImageUrl() {
            return writeImageUrl;
        }

        public String getWriteDirection() {
            return writeDirection;
        }
    }
}
