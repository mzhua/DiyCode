package im.hua.diycode.network.entity;

import android.text.TextUtils;

/**
 * Created by hua on 2016/11/17.
 */

public class UserEntity extends BaseEntity {
    /**
     * id : 30
     * login : d_clock
     * name : D_clock爱吃葱花
     * avatar_url : http://diycode.b0.upaiyun.com/user/large_avatar/30.jpg
     */

    private int id;
    private String login;
    private String name;
    private String avatar_url;

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
        return TextUtils.isEmpty(name) ? "null" : name;
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
}
