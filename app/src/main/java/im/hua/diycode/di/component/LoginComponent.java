package im.hua.diycode.di.component;

import dagger.Component;
import im.hua.diycode.di.PerActivity;
import im.hua.diycode.ui.login.LoginActivity;

/**
 * Created by hua on 2016/12/11.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
}
