package im.hua.diycode.network.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hua on 2016/11/17.
 */

public interface NewsAPI {
    @GET("api/v3/news.json")
    Observable getNews();
}
