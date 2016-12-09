package im.hua.diycode.network.api;

import java.util.List;

import im.hua.diycode.network.entity.TopicEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hua on 2016/11/17.
 */

public interface TopicAPI {
    @GET("api/v3/topics.json")
    Observable<List<TopicEntity>> getTopics();

    @GET("api/v3/topics/{id}")
    Observable<TopicEntity> getTopicsDetail(@Path("id") String id);
}
