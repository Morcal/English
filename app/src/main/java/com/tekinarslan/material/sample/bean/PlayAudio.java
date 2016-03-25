package com.tekinarslan.material.sample.bean;

import java.util.List;

/**
 * Created by lyqdhgo on 2016/3/25.
 */
public class PlayAudio {

    /**
     * id : YjM0MDgwMDAwMDExNTQ4ZQ==
     * title : 我记得那美妙的一瞬
     * attachedImg : http://cdn.llsapp.com/forum/image/6e0828d6f31340e5b53c75fdadb09f4e_1458736590317.jpg
     * repliesCount : 63
     * platform : 2
     * os : Android 5.0
     * appVersion : 3.1.2
     * audioUrl : http://cdn.llsapp.com/forum/audio/14587365903151458735225166_8k_32k.mp3
     * audioLength : 120
     * createdAt : 1458736595
     * repliedAt : 1458781586
     * bodyLength : 1425
     * body : I remembered that is wonderful as soon as flickers:
     * Appeared you in mine front,
     * Some like appears briefly fantasy,
     * Has like the chaste America's angel.
     * 我记得那美妙的一瞬，
     * 在我的面前出现了你，
     * 有如昙花一现的幻想，
     * 有如纯洁至美的精灵。
     * <p/>
     * In that hopeless sad suffering,
     * Makes noise in that in ostentatious life puzzle,
     * Nearby my ear for a long time is making a sound your gentlesound,
     * I also see your lovable beautiful figure in the sleep.
     * 在那无望的忧愁的折磨中，
     * 在那喧闹的浮华生活的困扰中，
     * 我的耳边长久地响着你温柔的声音，
     * 我还在睡梦中见到你可爱的倩影。
     * <p/>
     * Many years have passed by, storm smile
     * Has scattered the former days dream,
     * There upon I have put behind your gentle sound,
     * Also has your that angel resembles the beautiful figure.
     * 许多年过去了，暴风骤雨般的激情,
     * 驱散了往日的梦想，
     * 于是我忘却了你温柔的声音,
     * 还有你那天仙似的的倩影。
     * <p/>
     * In the remote place, in the gloomy life which imprisons,
     * My day such calmly dissipates,
     * The insincere person, does not have the poem the inspiration,
     * Without the tear, does not have the life, also does not have the love.
     * 在穷乡僻壤，在囚禁的阴暗生活中，
     * 我的日子就那样静静地消逝，
     * 没有倾心的人，没有诗的灵感，
     * 没有眼泪，没有生命，也没有爱情。
     * <p/>
     * Now the mind starts to regain consciousness:
     * By now has reappeared in front of me you,
     * Has illusory image which like appears briefly,
     * Has like the chaste America's angel.
     * 如今心灵又开始苏醒：
     * 在我面前又重新出现了你，
     * 有如昙花一现的幻影，
     * 有如纯洁至美的天仙。
     * <p/>
     * My heart in is wild with joy jumps,
     * In heart all reregain consciousness,
     * Had the sincere person, had the poem inspiration,
     * Had the life, had the tear, also had the love.
     * 我的心在狂喜中跳跃，
     * 心中的一切又重新苏醒，
     * 有了倾心的人，有了诗的灵感，
     * 有了生命，有了眼泪，也有了爱情。
     * tags : ["美文"]
     * type : ForumTopic
     * user : {"id":"YjM0MWQwMDAwMDczMWVjZA==","nick":"梦想的旅行者","avatar":"http://cdn.llsapp.com/868299029377008_1454738562892.jpg","answerBadgeLevel":0,"level":29}
     * isCircleEssential : false
     * circle : {"id":"YjhjMjEwMDAwMDAwMDIzYw==","name":"书海撷珠📚"}
     * isManager : false
     * likesCount : 100
     * followsCount : 0
     * isLiked : false
     * isFollowed : false
     * isPinned : false
     */

    private String id;
    private String title;
    private String attachedImg;
    private int repliesCount;
    private int platform;
    private String os;
    private String appVersion;
    private String audioUrl;
    private int audioLength;
    private int createdAt;
    private int repliedAt;
    private int bodyLength;
    private String body;
    private String type;
    /**
     * id : YjM0MWQwMDAwMDczMWVjZA==
     * nick : 梦想的旅行者
     * avatar : http://cdn.llsapp.com/868299029377008_1454738562892.jpg
     * answerBadgeLevel : 0
     * level : 29
     */

    private UserEntity user;
    private boolean isCircleEssential;
    /**
     * id : YjhjMjEwMDAwMDAwMDIzYw==
     * name : 书海撷珠📚
     */

    private CircleEntity circle;
    private boolean isManager;
    private int likesCount;
    private int followsCount;
    private boolean isLiked;
    private boolean isFollowed;
    private boolean isPinned;
    private List<String> tags;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAttachedImg(String attachedImg) {
        this.attachedImg = attachedImg;
    }

    public void setRepliesCount(int repliesCount) {
        this.repliesCount = repliesCount;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public void setAudioLength(int audioLength) {
        this.audioLength = audioLength;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public void setRepliedAt(int repliedAt) {
        this.repliedAt = repliedAt;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setIsCircleEssential(boolean isCircleEssential) {
        this.isCircleEssential = isCircleEssential;
    }

    public void setCircle(CircleEntity circle) {
        this.circle = circle;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public void setFollowsCount(int followsCount) {
        this.followsCount = followsCount;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public void setIsFollowed(boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    public void setIsPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAttachedImg() {
        return attachedImg;
    }

    public int getRepliesCount() {
        return repliesCount;
    }

    public int getPlatform() {
        return platform;
    }

    public String getOs() {
        return os;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public int getAudioLength() {
        return audioLength;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public int getRepliedAt() {
        return repliedAt;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public String getBody() {
        return body;
    }

    public String getType() {
        return type;
    }

    public UserEntity getUser() {
        return user;
    }

    public boolean isIsCircleEssential() {
        return isCircleEssential;
    }

    public CircleEntity getCircle() {
        return circle;
    }

    public boolean isIsManager() {
        return isManager;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public int getFollowsCount() {
        return followsCount;
    }

    public boolean isIsLiked() {
        return isLiked;
    }

    public boolean isIsFollowed() {
        return isFollowed;
    }

    public boolean isIsPinned() {
        return isPinned;
    }

    public List<String> getTags() {
        return tags;
    }

    public static class UserEntity {
        private String id;
        private String nick;
        private String avatar;
        private int answerBadgeLevel;
        private int level;

        public void setId(String id) {
            this.id = id;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setAnswerBadgeLevel(int answerBadgeLevel) {
            this.answerBadgeLevel = answerBadgeLevel;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getId() {
            return id;
        }

        public String getNick() {
            return nick;
        }

        public String getAvatar() {
            return avatar;
        }

        public int getAnswerBadgeLevel() {
            return answerBadgeLevel;
        }

        public int getLevel() {
            return level;
        }
    }

    public static class CircleEntity {
        private String id;
        private String name;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
