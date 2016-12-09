package im.hua.diycode.ui.topic;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.network.api.TopicAPI;
import im.hua.diycode.network.entity.TopicEntity;
import im.hua.diycode.network.util.ResponseCompose;
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
    private Gson mGson = new Gson();

    @Inject
    public TopicsRepository(TopicAPI topicAPI) {
        mTopicAPI = topicAPI;
    }

    public <T> List<T> read(Class<T> type,JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        List<T> list = new ArrayList<>();
        in.beginArray();
        TypeAdapter<T> adapter = mGson.getAdapter(type);
        while (in.hasNext()) {
            T instance = adapter.read(in);
            list.add(instance);
        }
        in.endArray();

        return list;
    }

    public <T> List<T> jsonArrayParse(Class<T> clazz,String value){
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(value).getAsJsonArray();

        List<T> lcs = new ArrayList<T>();

        for(JsonElement obj : Jarray ){
            T cse = mGson.fromJson( obj , clazz);
            lcs.add(cse);
        }

        return lcs;
    }

    @Override
    public Observable<List<TopicEntity>> getTopics() {
        return mTopicAPI.getTopicsNew()
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<String, List<TopicEntity>>() {
                    @Override
                    public List<TopicEntity> convert(String value) {
                        List<TopicEntity> tmp = jsonArrayParse(TopicEntity.class,value);
                        return tmp;
                    }
                }));
      /*  return mTopicAPI.getTopics()
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
                });*/
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
