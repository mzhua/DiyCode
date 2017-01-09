package im.hua.diycode.data.repository.impl;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.data.api.SitesAPI;
import im.hua.diycode.data.entity.SiteEntity;
import im.hua.diycode.data.repository.ISitesRepository;
import im.hua.diycode.data.util.GsonConverterUtil;
import im.hua.diycode.data.util.ResponseCompose;
import rx.Observable;

/**
 * Created by hua on 2017/1/8.
 */

public class SitesRepository implements ISitesRepository {
    private SitesAPI mSitesAPI;

    @Inject
    public SitesRepository(SitesAPI sitesAPI) {
        mSitesAPI = sitesAPI;
    }

    @Override
    public Observable<List<SiteEntity>> getSiteList() {
        return mSitesAPI.getSites()
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<List<SiteEntity>>() {
                    @Override
                    public List<SiteEntity> convert(String value) {
                        return GsonConverterUtil.jsonArrayParse(SiteEntity.class,value);
                    }
                }));
    }
}
