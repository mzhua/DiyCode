package im.hua.mvp.framework.di;

import javax.inject.Singleton;

import dagger.Component;
import im.hua.mvp.framework.BaseActivity;
import im.hua.mvp.framework.BaseAppCompatActivity;
import im.hua.mvp.framework.BaseFragment;
import im.hua.mvp.framework.BaseV4Fragment;

/**
 * Created by hua on 16/10/12.
 */
@Singleton
@Component
public interface FApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseAppCompatActivity baseActivity);
    void inject(BaseFragment baseFragment);
    void inject(BaseV4Fragment baseFragment);
}
