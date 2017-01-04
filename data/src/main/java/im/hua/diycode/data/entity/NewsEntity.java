package im.hua.diycode.data.entity;

/**
 * Created by hua on 2016/11/17.
 */

public class NewsEntity extends BaseEntity{

    /**
     * id : 1539
     * title : RxJava - 小心 observeOn 的陷阱
     * created_at : 2016-11-17T10:58:59.944+08:00
     * updated_at : 2016-11-17T10:58:59.944+08:00
     * user : {"id":30,"login":"d_clock","name":"D_clock爱吃葱花","avatar_url":"http://diycode.b0.upaiyun.com/user/large_avatar/30.jpg"}
     * node_name : Android
     * node_id : 1
     * last_reply_user_id : null
     * last_reply_user_login : null
     * replied_at : null
     * address : http://www.jianshu.com/p/238b286ac69c
     * replies_count : 0
     */

    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private UserEntity user;
    private String node_name;
    private int node_id;
    private String last_reply_user_id;
    private String last_reply_user_login;
    private String replied_at;
    private String address;
    private int replies_count;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public String getLast_reply_user_id() {
        return last_reply_user_id;
    }

    public void setLast_reply_user_id(String last_reply_user_id) {
        this.last_reply_user_id = last_reply_user_id;
    }

    public String getLast_reply_user_login() {
        return last_reply_user_login;
    }

    public void setLast_reply_user_login(String last_reply_user_login) {
        this.last_reply_user_login = last_reply_user_login;
    }

    public String getReplied_at() {
        return replied_at;
    }

    public void setReplied_at(String replied_at) {
        this.replied_at = replied_at;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

}
