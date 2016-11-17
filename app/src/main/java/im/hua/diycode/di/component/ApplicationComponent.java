package im.hua.diycode.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import im.hua.diycode.di.module.ApiModule;
import im.hua.diycode.di.module.ApplicationModule;
import im.hua.diycode.di.module.RepositoryModule;
import im.hua.mvp.framework.BaseActivity;
import im.hua.mvp.framework.di.FApplicationComponent;
import io.realm.Realm;
import retrofit2.Retrofit;

/**
 * Created by hua on 16/10/12.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, RepositoryModule.class})
public interface ApplicationComponent extends FApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context getContext();

    SharedPreferences getSharedPreferences();

    Retrofit getRetrofit();

    Realm getRealm();

//    IUserRepository getLoginRepository();
}
