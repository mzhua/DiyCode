package im.hua.diycode.ui.sites;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.data.entity.SiteEntity;
import im.hua.diycode.data.repository.ISitesRepository;
import im.hua.diycode.data.util.CommonErrorSubscriber;
import im.hua.mvp.framework.IMVPAuthView;
import im.hua.mvp.framework.MVPListAuthPresenter;

/**
 * Created by hua on 2017/1/8.
 */

public class SitesPresenter extends MVPListAuthPresenter<ISitesView, SiteEntity> {
    private ISitesRepository mRepository;

    @Inject
    public SitesPresenter(ISitesRepository repository) {
        mRepository = repository;
    }

    public void getSiteList() {
        addSubscription(mRepository.getSiteList()
                .subscribe(new CommonErrorSubscriber<List<SiteEntity>>() {
                    @Override
                    public IMVPAuthView getMVPAuthView() {
                        return getView();
                    }

                    @Override
                    public void onNext(List<SiteEntity> siteEntities) {
                        getView().showDatas(siteEntities);
                    }
                }));
    }
}
