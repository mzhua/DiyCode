package im.hua.diycode.data.api;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
     * @param grant_type   password
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("oauth/token")
    Observable<Response<String>> getToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret, @Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

}
