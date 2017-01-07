package im.hua.diycode.data.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hua on 2016/11/17.
 */

public interface NewsAPI {
    @GET("api/v3/news.json")
    Observable<Response<String>> getNews(@Query("node_id") Integer node_id, @Query("offset") Integer offset, @Query("limit") Integer limit);
}
