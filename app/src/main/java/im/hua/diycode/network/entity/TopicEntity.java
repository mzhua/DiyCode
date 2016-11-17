package im.hua.diycode.network.entity;

/**
 * Created by hua on 2016/11/17.
 */

public class TopicEntity {

    /**
     * id : 427
     * title : Diycode 代码艺术技术沙龙 | 广州站小结 (视频 PPT 都有了)
     * created_at : 2016-11-15T00:13:15.653+08:00
     * updated_at : 2016-11-17T17:43:07.997+08:00
     * replied_at : 2016-11-17T17:43:07.977+08:00
     * replies_count : 28
     * node_name : 线下活动
     * node_id : 44
     * last_reply_user_id : 651
     * last_reply_user_login : marshall
     * user : {"id":30,"login":"d_clock","name":"D_clock爱吃葱花","avatar_url":"http://diycode.b0.upaiyun.com/user/large_avatar/30.jpg"}
     * deleted : false
     * excellent : true
     * abilities : {"update":false,"destroy":false}
     */

    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private String replied_at;
    private int replies_count;
    private String node_name;
    private int node_id;
    private int last_reply_user_id;
    private String last_reply_user_login;
    private UserEntity user;
    private boolean deleted;
    private boolean excellent;
    private AbilitiesEntity abilities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getReplied_at() {
        return replied_at;
    }

    public void setReplied_at(String replied_at) {
        this.replied_at = replied_at;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public int getLast_reply_user_id() {
        return last_reply_user_id;
    }

    public void setLast_reply_user_id(int last_reply_user_id) {
        this.last_reply_user_id = last_reply_user_id;
    }

    public String getLast_reply_user_login() {
        return last_reply_user_login;
    }

    public void setLast_reply_user_login(String last_reply_user_login) {
        this.last_reply_user_login = last_reply_user_login;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isExcellent() {
        return excellent;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public AbilitiesEntity getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesEntity abilities) {
        this.abilities = abilities;
    }
}
