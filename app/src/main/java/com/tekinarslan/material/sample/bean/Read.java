package com.tekinarslan.material.sample.bean;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

/**
 * Created by lyqdhgo on 2016/5/17.
 */
public class Read extends BmobObject {

    /**
     * passage : If you think a high-factor sunscreen (防晒霜)keeps you safe from harmful rays, you may be wrong.Research in this week's Nature shows that while factor 50 reduces the number of melanomas(黑瘤)and delays their occurrence, it can't prevent them.Melanomas are the most aggressive skin cancers.You have a higher risk if you have red or blond hair, fair skin, blue or green eyes, or sunburn easily, or if a close relative has had one.Melanomas are more common if you have periodic intense exposure to the sun.Other skin cancers are increasingly likely with long-term exposure.
     * There is continuing debate as to how effective sunscreen is in reducing melanomas  the evidence is weaker than it is for preventing other types of skin cancer.A 2011 Australian study of 1,621 people found that people randomly selected to apply sunscreen daily had half the rate of melanomas of people who used cream as needed.A second study, comparing 1,167 people with melanomas to 1,101 who didn't have the cancer, found that using sunscreen routinely, alongside other protection such as hats,long sleeves or staying in the shade, did give some protection.This study said other forms of sun protection  not sunscreen  seemed most beneficial.The study relied on people remembering what they had done over each decade of their lives, so it's not entirely reliable.But it seems reasonable to think sunscreen gives people a false sense of security in the sun.
     * Many people also don't use sunscreen properly  applying insufficient amounts, failing to reapply after a couple of hours and staying in the sun too long.It is sunburn that is most worrying  recent research shows five episodes of sunburn in the teenage years increases the risk of all skin cancers.
     * The good news is that a combination of sunscreen and covering up can reduce melanoma rates, as shown by Australian figures from their slip-slop-slap campaign.So if there is a heat wave this summer, it would be best for us, too, to slip on a shirt, slop on (抹上)sunscreen and slap on a hat.
     * shortChoice : [{"pos":56,"question":"What is people's common expectation of a high-factor sunscreen?","choice":[{"chose":"A","content":"It will delay the occurrence of skin cancer."},{"chose":"B","content":"It will protect them from sunburn."},{"chose":"C","content":"It will keep their skin smooth and fair."},{"chose":"D","content":"It will work for people of any skin color."}],"type":"B","userAnswer":""},{"pos":57,"question":"What does the research in Nature say about a high-factor sunscreen?","choice":[{"chose":"A","content":"It is ineffective in preventing melanomas."},{"chose":"B","content":"It is ineffective in case of intense sunlight."},{"chose":"C","content":"It is ineffective with long-term exposure."},{"chose":"D","content":"It is ineffective for people with fair skin."}],"type":"B","userAnswer":""},{"pos":58,"question":"What do we learn from the 2011 Australian study of 1,621 people?","choice":[{"chose":"A","content":"Sunscreen should be applied alongside other protection measures."},{"chose":"B","content":"High-risk people benefit the most from the application of sunscreen."},{"chose":"C","content":"Irregular application of sunscreen does women more harm than good."},{"chose":"D","content":"Daily application of sunscreen helps reduce the incidence of melanomas."}],"type":"B","userAnswer":""},{"pos":59,"question":"What does the author say about the second Australian study?","choice":[{"chose":"A","content":"It misleads people to rely on sunscreen for protection."},{"chose":"B","content":"It helps people to select the most effective sunscreen."},{"chose":"C","content":"It is not based on direct observation of the subjects."},{"chose":"D","content":"It confirms the results of the first Australian study."}],"type":"B","userAnswer":""},{"pos":60,"question":"What does the author suggest to reduce melanoma rates?","choice":[{"chose":"A","content":"Using both covering up and sunscreen."},{"chose":"B","content":"Staying in the shade whenever possible."},{"chose":"C","content":"Using covering up instead of sunscreen."},{"chose":"D","content":"Applying the right amount of sunscreen."}],"type":"B","userAnswer":""}]
     */

    private String passage;
    /**
     * pos : 56
     * question : What is people's common expectation of a high-factor sunscreen?
     * choice : [{"chose":"A","content":"It will delay the occurrence of skin cancer."},{"chose":"B","content":"It will protect them from sunburn."},{"chose":"C","content":"It will keep their skin smooth and fair."},{"chose":"D","content":"It will work for people of any skin color."}]
     * type : B
     * userAnswer :
     */

    private List<ShortChoiceEntity> shortChoice;

    public void setPassage(String passage) {
        this.passage = passage;
    }

    public void setShortChoice(List<ShortChoiceEntity> shortChoice) {
        this.shortChoice = shortChoice;
    }

    public String getPassage() {
        return passage;
    }

    public List<ShortChoiceEntity> getShortChoice() {
        return shortChoice;
    }

    public static class ShortChoiceEntity {
        private int pos;
        private String question;
        private String type;
        private String userAnswer;
        /**
         * chose : A
         * content : It will delay the occurrence of skin cancer.
         */

        private List<ChoiceEntity> choice;

        public void setPos(int pos) {
            this.pos = pos;
        }

        public void setQuestion(String question) {
            this.question = question;
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

        public String getQuestion() {
            return question;
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

    @Override
    public String toString() {
        return "Read{" +
                "passage='" + passage + '\'' +
                ", shortChoice=" + shortChoice +
                '}';
    }
}
