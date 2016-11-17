package im.hua.diycode.network.api;

import retrofit2.http.Field;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hua on 2016/11/17.
 */

public interface AuthAPI {
    /**
     * 获取授权token
     * @param client_id
     * @param client_secret
     * @param grant_type
     * @param username
     * @param password
     * @return
     */
    @POST("oauth/token")
    Observable getToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret, @Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

}
