package im.hua.diycode.network.entity;

/**
 * Created by hua on 2016/12/11.
 */

public class TokenEntity extends BaseEntity {

    /**
     * access_token : c5937ff7c9e26916dd8d9ac44282a50c83969d93fe192131e8de4f62fc544afd
     * token_type : bearer
     * expires_in : 5184000
     * refresh_token : 0c73c0f909b64ed3cc7baa73d4e15071facfe34c6678e8825e512d326392d52e
     * created_at : 1481441658
     */

    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    private int created_at;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }
}
