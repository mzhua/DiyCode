package im.hua.diycode.ui.topic;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.network.api.TopicAPI;
import im.hua.diycode.network.entity.TopicEntity;
import im.hua.diycode.network.util.ResponseCompose;
import im.hua.diycode.util.GsonConverterUtil;
import rx.Observable;


/**
 * Created by hua on 2016/11/21.
 */

public class TopicsRepository implements ITopicsRepository {
    private TopicAPI mTopicAPI;

    @Inject
    public TopicsRepository(TopicAPI topicAPI) {
        mTopicAPI = topicAPI;
    }

    @Override
    public Observable<List<TopicEntity>> getTopics() {
        return mTopicAPI.getTopics()
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<List<TopicEntity>>() {
                    @Override
                    public List<TopicEntity> convert(String value) {
                        return GsonConverterUtil.jsonArrayParse(TopicEntity.class, value);
                    }
                }));
    }

    @Override
    public Observable<TopicEntity> getTopicsDetailById(String id) {
        return mTopicAPI.getTopicsDetail(id)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<TopicEntity>() {
                    @Override
                    public TopicEntity convert(String value) {
                        return GsonConverterUtil.jsonObjectParse(TopicEntity.class,value);
                    }
                }));
    }
}
