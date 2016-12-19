package im.hua.mvp.framework;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * Created by hua on 16/10/11.
 */

public abstract class AppbarMVPActivity<V extends IMVPView, P extends IMVPPresenter<V>> extends MVPActivity<V,P> {
    @LayoutRes
    public abstract int getContentLayout();

    @IdRes
    public abstract int getToolBarId();

    @NonNull
    public abstract String getToolBarTitle();

    @DrawableRes
    public abstract int getToolBarNavIconDrawableId();

    /**
     * return true to disable the default finish action
     *
     * @return
     */
    public abstract boolean onBackClick();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        try {
            Toolbar toolbar = (Toolbar) findViewById(getToolBarId());
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                toolbar.setNavigationIcon(getToolBarNavIconDrawableId());
                toolbar.setTitle(getToolBarTitle());
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!onBackClick()) {
                            finish();
                        }
                    }
                });
            }
        } catch (Exception e) {
            Log.e("AppbarMVPActivity", e.getMessage());
        }
    }
}
