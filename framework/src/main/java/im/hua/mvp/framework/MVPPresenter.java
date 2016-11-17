package im.hua.mvp.framework;

import java.lang.ref.WeakReference;

/**
 * Created by hua on 16/10/12.
 */

public abstract class MVPPresenter<V extends IMVPView> implements IMVPPresenter<V> {
    private WeakReference<V> mView;

    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @Override
    public V getView() {
        return null == mView ? null : mView.get();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
