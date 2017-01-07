package im.hua.diycode.di.component;

import dagger.Component;
import im.hua.diycode.di.PerActivity;
import im.hua.diycode.ui.news.NewsPresenter;

/**
 * Created by hua on 2017/1/7.
 */
@Component(dependencies = ApplicationComponent.class)
@PerActivity
public interface NewsComponent {
    NewsPresenter getNewsPresenter();
}
