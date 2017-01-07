package im.hua.diycode.data.repository;

import java.util.List;

import im.hua.diycode.data.entity.NewsEntity;
import rx.Observable;

/**
 * Created by hua on 2017/1/7.
 */

public interface INewsRepository {
    Observable<List<NewsEntity>> getNewsList(Integer node_id, Integer offset, Integer limit);
}
