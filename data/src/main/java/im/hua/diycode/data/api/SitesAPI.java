package im.hua.diycode.data.api;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hua on 2016/11/17.
 */

public interface SitesAPI {
    @GET("api/v3/sites.json")
    Observable<Response<String>> getSites();
}
