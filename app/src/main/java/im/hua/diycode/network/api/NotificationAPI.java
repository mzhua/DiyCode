package im.hua.diycode.network.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hua on 2016/12/10.
 */

public interface NotificationAPI {
    @GET("api/v3/notifications.json")
    Observable<String> getNotifications(@Query("offset") Integer offset, @Query("limit") Integer limit);
}
