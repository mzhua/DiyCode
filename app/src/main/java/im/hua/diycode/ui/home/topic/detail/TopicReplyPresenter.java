package im.hua.diycode.ui.home.topic.detail;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.data.remote.repository.ITopicsRepository;
import im.hua.diycode.network.entity.TopicReplyEntity;
import im.hua.mvp.framework.MVPPresenter;
import rx.Subscriber;

/**
 * Created by hua on 2016/12/13.
 */

public class TopicReplyPresenter extends MVPPresenter<TopicReplyView> {
    private ITopicsRepository mTopicsRepository;

    @Inject
    public TopicReplyPresenter(ITopicsRepository topicsRepository) {
        mTopicsRepository = topicsRepository;
    }

    public void getRepliesOfTopic(String topicId, int offset) {
        getView().showLoadingView("");
        this.mTopicsRepository.getRepliesOfTopic(topicId, offset)
                .subscribe(new Subscriber<List<TopicReplyEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoadingView("");
                    }

                    @Override
                    public void onNext(List<TopicReplyEntity> topicReplyEntities) {
                        getView().hideLoadingView("");
                        getView().showTopicReplies(topicReplyEntities);
                    }
                });
    }
}
