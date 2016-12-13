package im.hua.diycode.data.remote.repository.impl;

import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.data.remote.repository.ITopicsRepository;
import im.hua.diycode.network.api.TopicAPI;
import im.hua.diycode.network.entity.OkEntity;
import im.hua.diycode.network.entity.TokenEntity;
import im.hua.diycode.network.entity.TopicEntity;
import im.hua.diycode.network.entity.TopicReplyEntity;
import im.hua.diycode.network.util.ResponseCompose;
import im.hua.diycode.util.GsonConverterUtil;
import rx.Observable;


/**
 * Created by hua on 2016/11/21.
 */

public class TopicsRepository implements ITopicsRepository {
    private TopicAPI mTopicAPI;
    private TokenEntity mTokenEntity;

    @Inject
    public TopicsRepository(TopicAPI topicAPI, TokenEntity tokenEntity) {
        mTopicAPI = topicAPI;
        mTokenEntity = tokenEntity;
    }

    @Override
    public Observable<List<TopicEntity>> getTopics(String type, Integer nodeId, int offset) {
        return mTopicAPI.getTopics(type, nodeId, offset, 20)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<List<TopicEntity>>() {
                    @Override
                    public List<TopicEntity> convert(String value) {
                        return GsonConverterUtil.jsonArrayParse(TopicEntity.class, value);
                    }
                }));
    }

    @Override
    public Observable<TopicEntity> getTopicsDetailById(String id) {
        return mTopicAPI.getTopicsDetail(id)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<TopicEntity>() {
                    @Override
                    public TopicEntity convert(String value) {
                        return GsonConverterUtil.jsonObjectParse(TopicEntity.class, value);
                    }
                }));
    }

    @Override
    public Observable<OkEntity> favTopic(String topicId, boolean favorite) {
        if (isTokenInvalid()) {
            return Observable.just(new OkEntity());
        }
        Observable<OkEntity> fav;
        if (favorite) {
            fav = mTopicAPI.favTopic(topicId, mTokenEntity.getAccess_token())
                    .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OkEntity>() {
                        @Override
                        public OkEntity convert(String value) {
                            return GsonConverterUtil.jsonObjectParse(OkEntity.class, value);
                        }
                    }));
        } else {
            fav = mTopicAPI.unFavTopic(topicId, mTokenEntity.getAccess_token())
                    .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OkEntity>() {
                        @Override
                        public OkEntity convert(String value) {
                            return GsonConverterUtil.jsonObjectParse(OkEntity.class, value);
                        }
                    }));
        }

        return fav;
    }

    @Override
    public Observable<OkEntity> followTopic(String topicId, boolean follow) {
        if (isTokenInvalid()) {
            return Observable.just(new OkEntity());
        }
        Observable<OkEntity> fo;
        if (follow) {
            fo = mTopicAPI.followTopic(topicId, mTokenEntity.getAccess_token())
                    .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OkEntity>() {
                        @Override
                        public OkEntity convert(String value) {
                            return GsonConverterUtil.jsonObjectParse(OkEntity.class, value);
                        }
                    }));
        } else {
            fo = mTopicAPI.unFollowTopic(topicId, mTokenEntity.getAccess_token())
                    .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<OkEntity>() {
                        @Override
                        public OkEntity convert(String value) {
                            return GsonConverterUtil.jsonObjectParse(OkEntity.class, value);
                        }
                    }));
        }

        return fo;
    }

    @Override
    public Observable<List<TopicReplyEntity>> getRepliesOfTopic(String topicId, int offset) {
        return this.mTopicAPI.getTopicsReplies(topicId,offset,20)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<List<TopicReplyEntity>>() {
                    @Override
                    public List<TopicReplyEntity> convert(String value) {
                        return GsonConverterUtil.jsonArrayParse(TopicReplyEntity.class,value);
                    }
                }));
    }

    private boolean isTokenInvalid() {
        return null == mTokenEntity || TextUtils.isEmpty(mTokenEntity.getAccess_token());
    }
}
