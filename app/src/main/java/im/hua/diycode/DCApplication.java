package im.hua.diycode;

import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerApplicationComponent;
import im.hua.diycode.di.module.ApplicationModule;
import im.hua.mvp.framework.FApplication;
import io.realm.Realm;

/**
 * Created by hua on 2016/11/17.
 */

public class DCApplication extends FApplication<ApplicationComponent> {

    private ApplicationComponent mApplicationComponent;

    @Override
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        initInjector();
    }

    public void setApplicationComponent(ApplicationComponent component) {
        this.mApplicationComponent = component;
    }

    private void initInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
