package im.hua.diycode.ui.topic;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.data.repository.ITopicsRepository;
import im.hua.diycode.data.MyException;
import im.hua.diycode.data.entity.OkEntity;
import im.hua.diycode.data.entity.TopicEntity;
import im.hua.mvp.framework.BackgroundableSubscriber;
import im.hua.mvp.framework.IMVPView;
import im.hua.mvp.framework.MVPListPresenter;
import rx.Subscriber;

/**
 * Created by hua on 2016/11/17.
 */

public class TopicsPresenter extends MVPListPresenter<ITopicsView, TopicEntity> {

    private ITopicsRepository mTopicsRepository;

    @Inject
    public TopicsPresenter(ITopicsRepository topicsRepository) {
        mTopicsRepository = topicsRepository;
    }

    public void getTopics(final int offset) {
        if (!isLoadingMore(offset)) {
            getView().showLoadingView("");
        }
        addSubscription(this.mTopicsRepository.getTopics(null, null, offset, PAGE_SIZE)
                .subscribe(new Subscriber<List<TopicEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoadingView("");
                        if (e instanceof UnknownHostException) {
                            getView().showErrorMessage("请检查你的网络后重试！");
                        } else {
                            getView().showErrorMessage(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<TopicEntity> topicEntities) {
                        resolveNext(topicEntities,offset);
                    }
                }));
    }

    public void favTopic(final String topicId, final boolean favorite) {
        this.mTopicsRepository.favTopic(topicId, favorite)
                .subscribe(new BackgroundableSubscriber<OkEntity>() {
                    @Override
                    public IMVPView getXView() {
                        return getView();
                    }

                    @Override
                    public void onXCompleted() {

                    }

                    @Override
                    public void onXError(Throwable e) {
                        getView().favFailed(topicId, favorite);
                        if (e instanceof MyException && ((MyException) e).getCode() == 401) {
                            getView().reLogin(e.getMessage());
                        }
                    }

                    @Override
                    public void onXNext(OkEntity data) {
                        if (data.getOk() == 0) {
                            getView().favFailed(topicId, favorite);
                            getView().reLogin("token无效，请重新登录！");
                        } else {
                            getView().favSuccess(topicId, favorite);
                        }
                    }
                });
    }

    public void followTopic(final String topicId, final boolean follow) {
        this.mTopicsRepository.followTopic(topicId, follow)
                .subscribe(new BackgroundableSubscriber<OkEntity>() {
                    @Override
                    public IMVPView getXView() {
                        return getView();
                    }

                    @Override
                    public void onXCompleted() {

                    }

                    @Override
                    public void onXError(Throwable e) {
                        getView().followFailed(topicId, follow);
                        if (e instanceof MyException && ((MyException) e).getCode() == 401) {
                            getView().reLogin(e.getMessage());
                        }
                    }

                    @Override
                    public void onXNext(OkEntity data) {
                        if (data.getOk() == 0) {
                            getView().followFailed(topicId, follow);
                            getView().reLogin("token无效，请重新登录！");
                        } else {
                            getView().followSuccess(topicId, follow);
                        }
                    }
                });
    }
}
