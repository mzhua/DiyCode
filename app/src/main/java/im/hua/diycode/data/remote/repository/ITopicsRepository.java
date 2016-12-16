package im.hua.diycode.data.remote.repository;

import java.util.List;

import im.hua.diycode.network.entity.OkEntity;
import im.hua.diycode.network.entity.TopicEntity;
import im.hua.diycode.network.entity.TopicReplyEntity;
import rx.Observable;

/**
 * Created by hua on 2016/11/21.
 */

public interface ITopicsRepository {
    /**
     * 获取首页topics
     * @return
     */
    Observable<List<TopicEntity>> getTopics(String type, Integer nodeId, int offset, int pageSize);

    /**
     * 获取topic的详细信息
     * @param id
     * @return
     */
    Observable<TopicEntity> getTopicsDetailById(String id);

    /**
     * 收藏
     * @param topicId
     * @param favorite
     * @return
     */
    Observable<OkEntity> favTopic(String topicId,boolean favorite);

    /**
     * 关注
     * @param topicId
     * @param follow
     * @return
     */
    Observable<OkEntity> followTopic(String topicId,boolean follow);

    /**
     * 获取话题评论列表
     * @param topicId
     * @param offset
     * @param pageSize
     * @return
     */
    Observable<List<TopicReplyEntity>> getRepliesOfTopic(String topicId, int offset, int pageSize);
}
