package im.hua.mvp.framework.di;

import javax.inject.Singleton;

import dagger.Component;
import im.hua.mvp.framework.BaseActivity;

/**
 * Created by hua on 16/10/12.
 */
@Singleton
@Component
public interface FApplicationComponent {
    void inject(BaseActivity baseActivity);
}
