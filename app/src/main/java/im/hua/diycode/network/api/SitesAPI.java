package im.hua.diycode.network.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hua on 2016/11/17.
 */

public interface SitesAPI {
    @GET("api/v3/sites.json")
    Observable getSites(@Query("type") String type, @Query("node_id") Integer node_id, @Query("offset") Integer offset, @Query("limit") Integer limit);
}
