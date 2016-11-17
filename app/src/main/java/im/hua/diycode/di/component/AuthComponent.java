package im.hua.diycode.di.component;

import dagger.Component;
import im.hua.diycode.di.PerActivity;
import im.hua.diycode.di.module.ActivityModule;
import im.hua.diycode.di.module.AuthModule;

/**
 * Created by hua on 16/10/12.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class, AuthModule.class})
public interface AuthComponent extends ActivityComponent {
//    void inject(LoginActivity activity);
}
