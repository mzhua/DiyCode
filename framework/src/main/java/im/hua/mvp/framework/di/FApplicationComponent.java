package im.hua.mvp.framework.di;

import javax.inject.Singleton;

import dagger.Component;
import im.hua.mvp.framework.BaseActivity;
import im.hua.mvp.framework.BaseFragment;

/**
 * Created by hua on 16/10/12.
 */
@Singleton
@Component
public interface FApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);
}
