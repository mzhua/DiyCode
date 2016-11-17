package im.hua.diycode.di.component;

import android.app.Activity;

import dagger.Component;
import im.hua.diycode.di.PerActivity;
import im.hua.diycode.di.module.ActivityModule;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    // Exposed to sub-graphs.
    Activity activity();
}
