package im.hua.diycode.ui.news;

import javax.inject.Inject;

import im.hua.diycode.data.entity.NewsEntity;
import im.hua.diycode.data.repository.INewsRepository;
import im.hua.mvp.framework.MVPListPresenter;

/**
 * Created by hua on 2017/1/4.
 */

public class NewsPresenter extends MVPListPresenter<INewView,NewsEntity> {

    private INewsRepository mRepository;

    @Inject
    public NewsPresenter(INewsRepository repository) {
        mRepository = repository;
    }

    public void getNewsList(Integer node_id, Integer offset){
        mRepository.getNewsList(node_id, offset, 20);
    }
}
