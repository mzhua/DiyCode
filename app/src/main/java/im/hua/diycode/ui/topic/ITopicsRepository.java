package im.hua.diycode.ui.topic;

import java.util.List;

import im.hua.diycode.network.entity.TopicEntity;
import rx.Observable;

/**
 * Created by hua on 2016/11/21.
 */

public interface ITopicsRepository {
    Observable<List<TopicEntity>> getTopics();
    Observable<TopicEntity> getTopicsDetailById(String id);
}
