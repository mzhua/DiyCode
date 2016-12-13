package im.hua.diycode.di.component;

import dagger.Component;
import im.hua.diycode.di.PerActivity;
import im.hua.diycode.ui.home.topic.TopicsPresenter;
import im.hua.diycode.ui.home.topic.detail.TopicDetailPresenter;
import im.hua.diycode.ui.home.topic.detail.TopicReplyPresenter;

/**
 * Created by hua on 2016/11/17.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class})
public interface TopicsComponent {
    TopicsPresenter getTopicsPresenter();

    TopicDetailPresenter getTopicDetailPresenter();

    TopicReplyPresenter getTopicReplyPresenter();
}
