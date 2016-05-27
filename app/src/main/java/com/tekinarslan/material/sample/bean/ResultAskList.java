package com.tekinarslan.material.sample.bean;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by lyqdhgo on 2016/5/26.
 */
public class ResultAskList {

    /**
     * e : 9999
     * m : 操作成功
     * data : [{"id":"23","ask_id":"1","answer_id":"0","ask":"想服务端提交的数据乱七八糟，的护肤回复本节课被人看见","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464251514","update_time":"1464251514"},{"id":"22","ask_id":"1","answer_id":"0","ask":"想服务端提交的数据乱七八糟，的护肤回复本节课被人看见","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464231943","update_time":"1464231943"},{"id":"21","ask_id":"1","answer_id":"0","ask":"想服务端提交的数据乱七八糟，的护肤回复本节课被人看见","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464231925","update_time":"1464231925"},{"id":"20","ask_id":"1","answer_id":"0","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464231430","update_time":"1464231430"},{"id":"19","ask_id":"1","answer_id":"6","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":"1232543","status":"1","creator":"0","editor":"6","create_time":"1464224764","update_time":"1464227012"},{"id":"18","ask_id":"1","answer_id":"0","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464224759","update_time":"1464224759"},{"id":"17","ask_id":"1","answer_id":"0","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464224338","update_time":"1464224338"},{"id":"16","ask_id":"1","answer_id":"0","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464224318","update_time":"1464224318"},{"id":"15","ask_id":"1","answer_id":"0","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464223909","update_time":"1464223909"},{"id":"13","ask_id":"1","answer_id":"0","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464223908","update_time":"1464223908"},{"id":"14","ask_id":"1","answer_id":"0","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464223908","update_time":"1464223908"},{"id":"12","ask_id":"1","answer_id":"0","ask":"c4cc56fc6d2f3de687aef910802b1427","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464223903","update_time":"1464223903"},{"id":"11","ask_id":"1","answer_id":"0","ask":"1111","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464223184","update_time":"1464223184"},{"id":"10","ask_id":"1","answer_id":"0","ask":"1111","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464223183","update_time":"1464223183"},{"id":"9","ask_id":"1","answer_id":"0","ask":"1111","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464153213","update_time":"1464153213"},{"id":"8","ask_id":"1","answer_id":"0","ask":"1111","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464153188","update_time":"1464153188"},{"id":"5","ask_id":"1","answer_id":"0","ask":"1111","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464153164","update_time":"1464153164"},{"id":"6","ask_id":"1","answer_id":"0","ask":"1111","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464153164","update_time":"1464153164"},{"id":"7","ask_id":"1","answer_id":"0","ask":"1111","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464153164","update_time":"1464153164"},{"id":"4","ask_id":"1","answer_id":"0","ask":"1111","answer":null,"status":"1","creator":"0","editor":"0","create_time":"1464153157","update_time":"1464153157"},{"id":"3","ask_id":"1","answer_id":null,"ask":"31111111","answer":"cccccc","status":"1","creator":"0","editor":null,"create_time":"0","update_time":"1463114052"}]
     */

    private String e;
    private String m;
    /**
     * id : 23
     * ask_id : 1
     * answer_id : 0
     * ask : 想服务端提交的数据乱七八糟，的护肤回复本节课被人看见
     * answer : null
     * status : 1
     * creator : 0
     * editor : 0
     * create_time : 1464251514
     * update_time : 1464251514
     */

    private List<DataEntity> data;

    public void setE(String e) {
        this.e = e;
    }

    public void setM(String m) {
        this.m = m;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getE() {
        return e;
    }

    public String getM() {
        return m;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String ask_id;
        private String answer_id;
        private String ask;
        private Object answer;
        private String status;
        private String creator;
        private String editor;
        private String create_time;
        private String update_time;

        public void setId(String id) {
            this.id = id;
        }

        public void setAsk_id(String ask_id) {
            this.ask_id = ask_id;
        }

        public void setAnswer_id(String answer_id) {
            this.answer_id = answer_id;
        }

        public void setAsk(String ask) {
            this.ask = ask;
        }

        public void setAnswer(Object answer) {
            this.answer = answer;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getId() {
            return id;
        }

        public String getAsk_id() {
            return ask_id;
        }

        public String getAnswer_id() {
            return answer_id;
        }

        public String getAsk() {
            return ask;
        }

        public Object getAnswer() {
            return answer;
        }

        public String getStatus() {
            return status;
        }

        public String getCreator() {
            return creator;
        }

        public String getEditor() {
            return editor;
        }

        public String getCreate_time() {
            return create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }
    }
}
