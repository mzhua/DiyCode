package im.hua.diycode.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hua on 2016/11/17.
 */

public class TopicEntity extends BaseEntity implements Parcelable{

    private String id;
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
    private String body;
    private String body_html;
    private int hits;
    private int likes_count;
    private String suggested_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public String getSuggested_at() {
        return suggested_at;
    }

    public void setSuggested_at(String suggested_at) {
        this.suggested_at = suggested_at;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeString(this.replied_at);
        dest.writeInt(this.replies_count);
        dest.writeString(this.node_name);
        dest.writeInt(this.node_id);
        dest.writeInt(this.last_reply_user_id);
        dest.writeString(this.last_reply_user_login);
        dest.writeParcelable(this.user, flags);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeByte(this.excellent ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.abilities, flags);
        dest.writeString(this.body);
        dest.writeString(this.body_html);
        dest.writeInt(this.hits);
        dest.writeInt(this.likes_count);
        dest.writeString(this.suggested_at);
    }

    public TopicEntity() {
    }

    protected TopicEntity(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.replied_at = in.readString();
        this.replies_count = in.readInt();
        this.node_name = in.readString();
        this.node_id = in.readInt();
        this.last_reply_user_id = in.readInt();
        this.last_reply_user_login = in.readString();
        this.user = in.readParcelable(UserEntity.class.getClassLoader());
        this.deleted = in.readByte() != 0;
        this.excellent = in.readByte() != 0;
        this.abilities = in.readParcelable(AbilitiesEntity.class.getClassLoader());
        this.body = in.readString();
        this.body_html = in.readString();
        this.hits = in.readInt();
        this.likes_count = in.readInt();
        this.suggested_at = in.readString();
    }

    public static final Creator<TopicEntity> CREATOR = new Creator<TopicEntity>() {
        @Override
        public TopicEntity createFromParcel(Parcel source) {
            return new TopicEntity(source);
        }

        @Override
        public TopicEntity[] newArray(int size) {
            return new TopicEntity[size];
        }
    };
}
