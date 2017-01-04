package im.hua.diycode.data.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hua on 2016/12/10.
 */

public interface NodeAPI {
    @GET("api/v3/nodes.json")
    Observable<String> getNodes();

    @GET("api/v3/nodes/{id}")
    Observable<String> getNodeById(@Path("id") String id);
}
