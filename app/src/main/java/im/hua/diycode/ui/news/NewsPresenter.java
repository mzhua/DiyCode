package im.hua.diycode.ui.news;

import java.util.List;

import javax.inject.Inject;

import im.hua.diycode.data.entity.NewsEntity;
import im.hua.diycode.data.repository.INewsRepository;
import im.hua.diycode.data.util.CommonErrorSubscriber;
import im.hua.mvp.framework.IMVPAuthView;
import im.hua.mvp.framework.MVPListAuthPresenter;

/**
 * Created by hua on 2017/1/4.
 */

public class NewsPresenter extends MVPListAuthPresenter<INewView, NewsEntity> {

    private INewsRepository mRepository;

    @Inject
    public NewsPresenter(INewsRepository repository) {
        mRepository = repository;
    }

    public void getNewsList(Integer node_id, final Integer offset) {
        addSubscription(mRepository.getNewsList(node_id, offset, getPageSize())
                .subscribe(new CommonErrorSubscriber<List<NewsEntity>>() {
                    @Override
                    public IMVPAuthView getMVPAuthView() {
                        return getView();
                    }

                    @Override
                    public void onNext(List<NewsEntity> newsEntities) {
                        resolveNext(newsEntities,offset);
                    }
                }));
    }
}
