package im.hua.mvp.framework;

import android.app.Application;

import im.hua.mvp.framework.di.FApplicationComponent;

/**
 * Created by hua on 16/10/12.
 */

public abstract class FApplication<C extends FApplicationComponent> extends Application {

    public abstract C getApplicationComponent();

}
