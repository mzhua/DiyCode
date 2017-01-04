package im.hua.diycode.ui.topic.detail;

import javax.inject.Inject;

import im.hua.diycode.data.remote.repository.ITopicsRepository;
import im.hua.diycode.data.entity.TopicEntity;
import im.hua.mvp.framework.MVPPresenter;
import rx.Subscriber;

/**
 * Created by hua on 2016/12/12.
 */

public class TopicDetailPresenter extends MVPPresenter<TopicDetailView> {
    private ITopicsRepository mTopicsRepository;

    @Inject
    public TopicDetailPresenter(ITopicsRepository topicsRepository) {
        mTopicsRepository = topicsRepository;
    }

    public void getTopicsDetail(String id) {
        getView().showLoadingView("");
        this.mTopicsRepository.getTopicsDetailById(id)
                .subscribe(new Subscriber<TopicEntity>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoadingView("");
                    }

                    @Override
                    public void onNext(TopicEntity topicEntity) {
                        getView().hideLoadingView("");
                        getView().showTopicDetailInfo(topicEntity);
                    }
                });
    }
}
