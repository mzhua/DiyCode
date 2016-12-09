package im.hua.diycode.ui.topic;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.network.api.TopicAPI;
import im.hua.diycode.network.entity.TopicEntity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hua on 2016/11/21.
 */

public class TopicsRepository implements ITopicsRepository{
    private TopicAPI mTopicAPI;

    @Inject
    public TopicsRepository(TopicAPI topicAPI) {
        mTopicAPI = topicAPI;
    }

    @Override
    public Observable<List<TopicEntity>> getTopics() {
        return mTopicAPI.getTopics()
                .compose(new Observable.Transformer<List<TopicEntity>, List<TopicEntity>>() {
                    @Override
                    public Observable<List<TopicEntity>> call(Observable<List<TopicEntity>> listObservable) {
                        return listObservable.flatMap(new Func1<List<TopicEntity>, Observable<List<TopicEntity>>>() {
                            @Override
                            public Observable<List<TopicEntity>> call(List<TopicEntity> topicEntities) {
                                return createData(topicEntities);
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<TopicEntity> getTopicsDetailById(String id) {
        return mTopicAPI.getTopicsDetail(id)
                .compose(new Observable.Transformer<TopicEntity, TopicEntity>() {
                    @Override
                    public Observable<TopicEntity> call(Observable<TopicEntity> topicEntityObservable) {
                        return topicEntityObservable.flatMap(new Func1<TopicEntity, Observable<TopicEntity>>() {
                            @Override
                            public Observable<TopicEntity> call(TopicEntity topicEntity) {
                                return createData(topicEntity);
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
