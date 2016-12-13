package im.hua.diycode.ui.home.topic.detail;

import java.util.List;

import im.hua.diycode.network.entity.TopicReplyEntity;
import im.hua.mvp.framework.IMVPView;

/**
 * Created by hua on 2016/12/12.
 */

public interface TopicReplyView extends IMVPView {
    void showTopicReplies(List<TopicReplyEntity> entity);
}
