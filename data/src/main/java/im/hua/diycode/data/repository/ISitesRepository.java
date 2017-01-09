package im.hua.diycode.data.repository;

import java.util.List;

import im.hua.diycode.data.entity.SiteEntity;
import rx.Observable;

/**
 * Created by hua on 2017/1/8.
 */

public interface ISitesRepository {
    Observable<List<SiteEntity>> getSiteList();
}
