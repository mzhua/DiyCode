package im.hua.diycode.di.component;

import dagger.Component;
import im.hua.diycode.di.PerActivity;
import im.hua.diycode.ui.sites.SitesPresenter;

/**
 * Created by hua on 2017/1/8.
 */
@Component(dependencies = ApplicationComponent.class)
@PerActivity
public interface SitesComponent {
    SitesPresenter getSitesPresenter();
}
