package im.hua.diycode.network.body;

import im.hua.diycode.Constants;

/**
 * Created by hua on 2016/12/5.
 */

public class TokenBody {

    /**
     * client_id : a27e72bd
     * client_secret : aafa838eeede101e5e2b5862c2087da6a403df1cd485ed0b7b6351adb96b4389
     * grant_type : password
     * password : diycode_mzhua0708
     * username : mzhua78@hotmail.com
     */

    private String client_id;
    private String client_secret;
    private String grant_type;
    private String password;
    private String username;

    public String getClient_id() {
        return Constants.CLIENT_ID;
    }

    public String getClient_secret() {
        return Constants.CLIENT_SECRET;
    }

    public String getGrant_type() {
        return "password";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
