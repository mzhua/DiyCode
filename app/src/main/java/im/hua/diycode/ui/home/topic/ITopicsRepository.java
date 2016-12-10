package im.hua.diycode.ui.home.topic;

import java.util.List;

import im.hua.diycode.network.entity.TopicEntity;
import rx.Observable;

/**
 * Created by hua on 2016/11/21.
 */

public interface ITopicsRepository {
    /**
     * 获取首页topics
     * @return
     */
    Observable<List<TopicEntity>> getTopics();

    /**
     * 获取topic的详细信息
     * @param id
     * @return
     */
    Observable<TopicEntity> getTopicsDetailById(String id);
}
