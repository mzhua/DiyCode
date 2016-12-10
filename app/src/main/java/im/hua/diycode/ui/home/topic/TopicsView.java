package im.hua.diycode.ui.home.topic;

import java.util.List;

import im.hua.diycode.network.entity.TopicEntity;
import im.hua.mvp.framework.IMVPView;

/**
 * Created by hua on 2016/11/17.
 */

public interface TopicsView extends IMVPView {
    void showTopics(List<TopicEntity> topics);

    void appendTopics(List<TopicEntity> topics);

    void noMoreData();
}
