package im.hua.diycode.data.entity;

/**
 * 话题回复
 * Created by hua on 2016/12/13.
 */

public class TopicReplyEntity extends BaseEntity {

    /**
     * id : 2431
     * body_html : <p>整个活动就是在一起聊天么？<br>
     报名的同学是否需要审核？还是任何人都可以参与？</p>

     <p>如果没有一个话题的讨论，现场都是菜鸟，或者水平参差不齐讨论起来也是很费劲的。建议是拟定话题，如果是圆桌讨论的话需要限定一个报名标准，达到才能参加 </p>
     * created_at : 2016-12-07T15:57:28.187+08:00
     * updated_at : 2016-12-12T10:33:11.594+08:00
     * deleted : false
     * topic_id : 485
     * user : {"id":48,"login":"jonsnow","name":"囧恩","avatar_url":"https://diycode.b0.upaiyun.com/user/large_avatar/48.jpg"}
     * likes_count : 0
     * abilities : {"update":false,"destroy":false}
     */

    private int id;
    private String body_html;
    private String created_at;
    private String updated_at;
    private boolean deleted;
    private int topic_id;
    private UserEntity user;
    private int likes_count;
    private AbilitiesEntity abilities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public AbilitiesEntity getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesEntity abilities) {
        this.abilities = abilities;
    }
}
