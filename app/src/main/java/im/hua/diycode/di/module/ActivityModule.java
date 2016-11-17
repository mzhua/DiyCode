package im.hua.diycode.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import im.hua.diycode.di.PerActivity;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return mActivity;
    }
}
