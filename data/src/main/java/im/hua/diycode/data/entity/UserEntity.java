package im.hua.diycode.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by hua on 2016/11/17.
 */
@RealmClass
public class UserEntity implements RealmModel,Parcelable{

    /**
     * id : 1205
     * login : mzhua
     * name : 疾风Hua
     * avatar_url : http://diycode.b0.upaiyun.com/user/large_avatar/1205.jpg
     * location :
     * company :
     * twitter :
     * website :
     * bio :
     * tagline :
     * github : mzhua
     * created_at : 2016-07-28T23:52:22.805+08:00
     * email : mzhua78@hotmail.com
     * topics_count : 0
     * replies_count : 0
     * following_count : 1
     * followers_count : 0
     * favorites_count : 2
     * level : normal
     * level_name : 会员
     */

    private int id;
    private String login;
    private String name;
    private String avatar_url;
    private String location;
    private String company;
    private String twitter;
    private String website;
    private String bio;
    private String tagline;
    private String github;
    private String created_at;
    private String email;
    private int topics_count;
    private int replies_count;
    private int following_count;
    private int followers_count;
    private int favorites_count;
    private String level;
    private String level_name;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTopics_count() {
        return topics_count;
    }

    public void setTopics_count(int topics_count) {
        this.topics_count = topics_count;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(int favorites_count) {
        this.favorites_count = favorites_count;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.login);
        dest.writeString(this.name);
        dest.writeString(this.avatar_url);
        dest.writeString(this.location);
        dest.writeString(this.company);
        dest.writeString(this.twitter);
        dest.writeString(this.website);
        dest.writeString(this.bio);
        dest.writeString(this.tagline);
        dest.writeString(this.github);
        dest.writeString(this.created_at);
        dest.writeString(this.email);
        dest.writeInt(this.topics_count);
        dest.writeInt(this.replies_count);
        dest.writeInt(this.following_count);
        dest.writeInt(this.followers_count);
        dest.writeInt(this.favorites_count);
        dest.writeString(this.level);
        dest.writeString(this.level_name);
    }

    protected UserEntity(Parcel in) {
        this.id = in.readInt();
        this.login = in.readString();
        this.name = in.readString();
        this.avatar_url = in.readString();
        this.location = in.readString();
        this.company = in.readString();
        this.twitter = in.readString();
        this.website = in.readString();
        this.bio = in.readString();
        this.tagline = in.readString();
        this.github = in.readString();
        this.created_at = in.readString();
        this.email = in.readString();
        this.topics_count = in.readInt();
        this.replies_count = in.readInt();
        this.following_count = in.readInt();
        this.followers_count = in.readInt();
        this.favorites_count = in.readInt();
        this.level = in.readString();
        this.level_name = in.readString();
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
