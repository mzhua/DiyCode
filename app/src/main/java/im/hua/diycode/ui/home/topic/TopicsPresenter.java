package im.hua.diycode.ui.home.topic;

import android.util.Log;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.network.MyException;
import im.hua.diycode.network.entity.OkEntity;
import im.hua.diycode.network.entity.TopicEntity;
import im.hua.mvp.framework.MVPPresenter;
import rx.Subscriber;

/**
 * Created by hua on 2016/11/17.
 */

public class TopicsPresenter extends MVPPresenter<TopicsView> {

    private ITopicsRepository mTopicsRepository;

    @Inject
    public TopicsPresenter(ITopicsRepository topicsRepository) {
        mTopicsRepository = topicsRepository;
    }

    public void getTopicsDetail(String id) {
        this.mTopicsRepository.getTopicsDetailById(id)
                .subscribe(new Subscriber<TopicEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d("TopicsPresenter d", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TopicsPresenter d", e.getMessage());
                    }

                    @Override
                    public void onNext(TopicEntity topicEntity) {
                        Log.d("TopicsPresenter d", topicEntity.getTitle());
                    }
                });
    }

    private boolean isLoadingMore(int offset) {
        return offset > 0;
    }

    public void getTopics(final int offset) {
        if (!isLoadingMore(offset)) {
            getView().showLoadingView("");
        }
        this.mTopicsRepository.getTopics(null, null, offset)
                .subscribe(new Subscriber<List<TopicEntity>>() {
                    @Override
                    public void onCompleted() {
                        getView().hideLoadingView("");
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
                        if (null == topicEntities || topicEntities.size() == 0) {
                            getView().noMoreData();
                        } else {
                            if (isLoadingMore(offset)) {
                                getView().appendTopics(topicEntities);
                            } else {
                                getView().showTopics(topicEntities);
                            }
                        }
                    }
                });
    }

    public void favTopic(final String topicId, final boolean favorite) {
        this.mTopicsRepository.favTopic(topicId, favorite)
                .subscribe(new Subscriber<OkEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof MyException && ((MyException) e).getCode() == 401) {
                            getView().reLogin(e.getMessage());
                        } else {
                            getView().favFailed(topicId, favorite);
                        }
                    }

                    @Override
                    public void onNext(OkEntity okEntity) {
                        if (okEntity.getOk() == 0) {
                            getView().favFailed(topicId, favorite);
                        } else {
                            getView().favSuccess(topicId, favorite);
                        }
                    }
                });
    }

    public void followTopic(final String topicId, final boolean follow) {
        this.mTopicsRepository.followTopic(topicId, follow)
                .subscribe(new Subscriber<OkEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof MyException && ((MyException) e).getCode() == 401) {
                            getView().reLogin(e.getMessage());
                        } else {
                            getView().followFailed(topicId, follow);
                        }
                    }

                    @Override
                    public void onNext(OkEntity okEntity) {
                        if (okEntity.getOk() == 0) {
                            getView().followFailed(topicId, follow);
                        } else {
                            getView().followSuccess(topicId, follow);
                        }
                    }
                });
    }
}
