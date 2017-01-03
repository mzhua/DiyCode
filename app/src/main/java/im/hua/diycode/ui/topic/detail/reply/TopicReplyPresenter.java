package im.hua.diycode.ui.topic.detail.reply;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.data.remote.repository.ITopicsRepository;
import im.hua.diycode.network.entity.TopicReplyEntity;
import im.hua.mvp.framework.MVPListPresenter;
import rx.Subscriber;

/**
 * Created by hua on 2016/12/13.
 */

public class TopicReplyPresenter extends MVPListPresenter<TopicReplyView,TopicReplyEntity> {
    private ITopicsRepository mTopicsRepository;

    @Inject
    public TopicReplyPresenter(ITopicsRepository topicsRepository) {
        mTopicsRepository = topicsRepository;
    }

    public void getRepliesOfTopic(String topicId, final int offset) {
        getView().showLoadingView("");
        addSubscription(this.mTopicsRepository.getRepliesOfTopic(topicId, offset, 20)
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
                        resolveNext(topicReplyEntities,offset);
                    }
                }));
    }
}
