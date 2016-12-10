package im.hua.diycode.network.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hua on 2016/11/17.
 */

public interface TopicAPI {

    /**
     *
     * @param type ["last_actived", "recent", "no_reply", "popular", "excellent"]
     * @param node_id
     * @param offset
     * @param limit 1..150
     * @return
     */
    @GET("api/v3/topics.json")
    Observable<Response<String>> getTopics(@Query("type") String type, @Query("node_id") Integer node_id, @Query("offset") Integer offset, @Query("limit") Integer limit);

    @GET("api/v3/topics/{id}")
    Observable<Response<String>> getTopicsDetail(@Path("id") String id);
}
