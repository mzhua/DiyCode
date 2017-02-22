package im.hua.diycode.ui.topic;

import im.hua.diycode.data.entity.TopicEntity;
import im.hua.mvp.framework.IMVPListAuthView;

/**
 * Created by hua on 2016/11/17.
 */

public interface ITopicsView extends IMVPListAuthView<TopicEntity> {

    void favSuccess(String topicId, boolean fav);

    void favFailed(String topicId, boolean fav);

    void followSuccess(String topicId, boolean follow);

    void followFailed(String topicId, boolean follow);

    void reLogin(String message);
}
