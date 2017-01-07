package im.hua.diycode.data.repository.impl;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.data.api.NewsAPI;
import im.hua.diycode.data.entity.NewsEntity;
import im.hua.diycode.data.repository.INewsRepository;
import im.hua.diycode.data.util.GsonConverterUtil;
import im.hua.diycode.data.util.ResponseCompose;
import rx.Observable;

/**
 * Created by hua on 2017/1/7.
 */

public class NewsRepository implements INewsRepository {
    private NewsAPI mNewsAPI;

    @Inject
    public NewsRepository(NewsAPI newsAPI) {
        mNewsAPI = newsAPI;
    }


    @Override
    public Observable<List<NewsEntity>> getNewsList(Integer node_id, Integer offset, Integer limit) {
        return mNewsAPI.getNews(node_id, offset, limit)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<List<NewsEntity>>() {
                    @Override
                    public List<NewsEntity> convert(String value) {
                        return GsonConverterUtil.jsonArrayParse(NewsEntity.class, value);
                    }
                }));
    }
}
