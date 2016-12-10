package im.hua.diycode.ui.home.topic;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

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

    public void getTopics() {
        this.mTopicsRepository.getTopics()
                .subscribe(new Subscriber<List<TopicEntity>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("TopicsPresenter", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TopicsPresenter", "error=="+e.getMessage());
                    }

                    @Override
                    public void onNext(List<TopicEntity> topicEntities) {
                        getView().showTopics(topicEntities);
                    }
                });
    }

}
