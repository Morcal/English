package com.tekinarslan.material.sample.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by lyqdhgo on 2016/6/1.
 */
public class Listener extends BmobObject {

    /**
     * listenDirection : Directions : In this section, you will hear 8 short conversations and 2 long conversations.At the end ofeach conversation, one or more questions will be asked about what was said.Both theconversation and the questions will be spoken only once.After each question there will bea pause.During the pause, you must read the four choices marked A., B), C.and D),and decide which is the best answer.Then mark the corresponding letter on AnswerSheet 1 with a single line through the centre.
     * listenAudioUrl : http://wximg.233.com/attached/media/20151113/20151113103125_1575.mp3
     * listenArticle : M:Well,did you enjoy it?/nW:Yes,I enjoy it much more than I thought I Would/nM:Really?/nW:Yes,I do not usually go to science fiction films,I do not think they are much better than comics on film,if  you know what I mean./nM:Yes,sure.And a few years ago,they were certainy like that.But they are got a lot better now./nW:Yes,and historcial films that is what I really like,I naver miss a good film set in the Middle Ages.Oh,and love stories I naver miss one on TV./nM:Funnily enough.I do not like those kinds of films at all.But to come back to this one,I personally did not think it was very good.it certainly was not as good as other science fiction films I have seen./nW:Was not it./nM:No.not at all.Oh,the effects were very good./nW:Yes.I thought they were marvelous,especially the battle in space,incredible./nM:Yes.but I was going to say I thought the acting was terrble./nW:Yes. I suppose Jason was too good to be ture./nM:Well,yes,but not always.War of the Worlds was not like that,for example.Anyway,you enjiys the file.That is most important./nW:Yes.I did.Thanks for taking me.
     * listenAnalyze : 暂无解析
     * listenQuestion : [{"pos":1,"choice":[{"chose":"A","content":"He will give the woman some tips on the game."},{"chose":"B","content":"The woman has good reason to quit the game."},{"chose":"C","content":"He is willing to play chess with the woman."},{"chose":"D","content":"The woman should go on playing chess."}],"type":"B","userAnswer":""},{"pos":2,"choice":[{"chose":"A","content":"The man can forward the mail to Mary."},{"chose":"B","content":"She can call Mary to take care of the mail."},{"chose":"C","content":"Mary probably knows Sally's new address."},{"chose":"D","content":"She would like to resume contact with Sally."}],"type":"B","userAnswer":""},{"pos":3,"choice":[{"chose":"A","content":"His handwriting has a unique style."},{"chose":"B","content":"His notes are not easy to read."},{"chose":"C","content":"He did not attend today's class."},{"chose":"D","content":"He is very pleased to be able to help."}],"type":"B","userAnswer":""},{"pos":4,"choice":[{"chose":"A","content":"The man had better choose another restaurant."},{"chose":"B","content":"The new restaurant is a perfect place for dating."},{"chose":"C","content":"The new restaurant caught her fancy immediately."},{"chose":"D","content":"The man has good taste in choosing the restaurant."}],"type":"B","userAnswer":""},{"pos":5,"choice":[{"chose":"A","content":"He has been looking forward to spring."},{"chose":"B","content":"He has been waiting for the winter sale."},{"chose":"C","content":"He will clean the woman's boots for spring."},{"chose":"D","content":"He will help the woman put things away."}],"type":"B","userAnswer":""},{"pos":6,"choice":[{"chose":"A","content":"The woman is rather forgetful."},{"chose":"B","content":"The man appreciates the woman's help."},{"chose":"C","content":"The man often lends books to the woman."},{"chose":"D","content":"The woman often works overtime at weekends."}],"type":"B","userAnswer":""},{"pos":7,"choice":[{"chose":"A","content":"Go to work on foot."},{"chose":"B","content":"Take a sightseeing trip."},{"chose":"C","content":"Start work earlier than usual."},{"chose":"D","content":"Take a walk when the weather is nice."}],"type":"B","userAnswer":""},{"pos":8,"choice":[{"chose":"A","content":"The plane is going to land at another airport."},{"chose":"B","content":"All flights have been delayed due to bad weather."},{"chose":"C","content":"Temporary closing has disturbed the airport's operation."},{"chose":"D","content":"The airport's management is in real need of improvement."}],"type":"B","userAnswer":""},{"pos":9,"choice":[{"chose":"A","content":"It specializes in safety from leaks."},{"chose":"B","content":"It is headquartered in London."},{"chose":"C","content":"It has a partnership with LCP."},{"chose":"D","content":"It has a chemical processing plant."}],"type":"B","userAnswer":""},{"pos":10,"choice":[{"chose":"A","content":"He is Mr.Grand's friend."},{"chose":"B","content":"He is a safety inspector."},{"chose":"C","content":"He is a salesman."},{"chose":"D","content":"He is a chemist."}],"type":"B","userAnswer":""}]
     */

    private ListenEntity listen;

    public void setListen(ListenEntity listen) {
        this.listen = listen;
    }

    public ListenEntity getListen() {
        return listen;
    }

    public static class ListenEntity {
        private String listenDirection;
        private String listenAudioUrl;
        private String listenArticle;
        private String listenAnalyze;
        /**
         * pos : 1
         * choice : [{"chose":"A","content":"He will give the woman some tips on the game."},{"chose":"B","content":"The woman has good reason to quit the game."},{"chose":"C","content":"He is willing to play chess with the woman."},{"chose":"D","content":"The woman should go on playing chess."}]
         * type : B
         * userAnswer :
         */

        private List<ListenQuestionEntity> listenQuestion;

        public void setListenDirection(String listenDirection) {
            this.listenDirection = listenDirection;
        }

        public void setListenAudioUrl(String listenAudioUrl) {
            this.listenAudioUrl = listenAudioUrl;
        }

        public void setListenArticle(String listenArticle) {
            this.listenArticle = listenArticle;
        }

        public void setListenAnalyze(String listenAnalyze) {
            this.listenAnalyze = listenAnalyze;
        }

        public void setListenQuestion(List<ListenQuestionEntity> listenQuestion) {
            this.listenQuestion = listenQuestion;
        }

        public String getListenDirection() {
            return listenDirection;
        }

        public String getListenAudioUrl() {
            return listenAudioUrl;
        }

        public String getListenArticle() {
            return listenArticle;
        }

        public String getListenAnalyze() {
            return listenAnalyze;
        }

        public List<ListenQuestionEntity> getListenQuestion() {
            return listenQuestion;
        }

        public static class ListenQuestionEntity {
            private int pos;
            private String type;
            private String userAnswer;
            /**
             * chose : A
             * content : He will give the woman some tips on the game.
             */

            private List<ChoiceEntity> choice;

            public void setPos(int pos) {
                this.pos = pos;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUserAnswer(String userAnswer) {
                this.userAnswer = userAnswer;
            }

            public void setChoice(List<ChoiceEntity> choice) {
                this.choice = choice;
            }

            public int getPos() {
                return pos;
            }

            public String getType() {
                return type;
            }

            public String getUserAnswer() {
                return userAnswer;
            }

            public List<ChoiceEntity> getChoice() {
                return choice;
            }

            public static class ChoiceEntity {
                private String chose;
                private String content;

                public void setChose(String chose) {
                    this.chose = chose;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getChose() {
                    return chose;
                }

                public String getContent() {
                    return content;
                }
            }
        }
    }
}
