package im.hua.diycode.network.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hua on 2016/11/17.
 */

public interface UserAPI {
    /**
     * 获取当前登录用户信息
     * @param accessToken
     * @return
     */
    @GET("api/v3/users/me.json")
    Observable<Response<String>> getCurrentUserInfo(@Query("access_token") String accessToken);
}
