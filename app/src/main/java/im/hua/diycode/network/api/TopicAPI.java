package im.hua.diycode.network.api;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 获取话题详情
     * @param id
     * @return
     */
    @GET("api/v3/topics/{id}")
    Observable<Response<String>> getTopicsDetail(@Path("id") String id);

    /**
     * 收藏话题
     * @param topicId
     * @param access_token
     * @return
     */
    @POST("api/v3/topics/{topicId}/favorite.json")
    Observable<Response<String>> favTopic(@Path("topicId") String topicId,@Query("access_token") String access_token);

    /**
     * 取消收藏话题
     * @param topicId
     * @param access_token
     * @return
     */
    @POST("api/v3/topics/{topicId}/unfavorite.json")
    Observable<Response<String>> unFavTopic(@Path("topicId") String topicId,@Query("access_token") String access_token);

    /**
     * 关注话题
     * @param topicId
     * @param access_token
     * @return
     */
    @POST("api/v3/topics/{topicId}/follow.json")
    Observable<Response<String>> followTopic(@Path("topicId") String topicId,@Query("access_token") String access_token);

    /**
     * 取消关注话题
     * @param topicId
     * @param access_token
     * @return
     */
    @POST("api/v3/topics/{topicId}/unfollow.json")
    Observable<Response<String>> unFollowTopic(@Path("topicId") String topicId,@Query("access_token") String access_token);

    /**
     * 获取话题评论
     * @param topicId
     * @return
     */
    @GET("api/v3/topics/{topicId}/replies.json")
    Observable<Response<String>> getTopicsReplies(@Path("topicId") String topicId, @Query("offset") Integer offset, @Query("limit") Integer limit);

    /**
     * 回帖
     * @param topicId
     * @param body
     * @return
     */
    @FormUrlEncoded
    @POST("api/v3/topics/{topicId}/replies.json")
    Observable<Response<String>> replyTopic(@Path("topicId") String topicId, @Field("body") String body);

    /**
     * 获取当前登录用户的收藏
     * @param access_token
     * @param userName
     * @return
     */
    @GET("api/v3/users/{userName}/favorites.json")
    Observable<Response<String>> getCurrentUserFavorites(@Query("access_token") String access_token, @Path("userName") String userName);

}
