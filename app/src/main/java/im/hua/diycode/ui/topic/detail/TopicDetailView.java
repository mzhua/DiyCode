package im.hua.diycode.ui.topic.detail;

import im.hua.diycode.data.entity.TopicEntity;
import im.hua.mvp.framework.IMVPView;

/**
 * Created by hua on 2016/12/12.
 */

public interface TopicDetailView extends IMVPView {
    void showTopicDetailInfo(TopicEntity entity);
}
