package im.hua.mvp.framework;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.internal.util.SubscriptionList;

/**
 * Created by hua on 16/10/12.
 */

public abstract class MVPAuthPresenter<V extends IMVPAuthView> implements IMVPPresenter<V> {
    private WeakReference<V> mView;
    private SubscriptionList mSubscriptionList = new SubscriptionList();

    protected void addSubscription(Subscription subscription) {
        mSubscriptionList.add(subscription);
    }

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
        if (null != mSubscriptionList) {
            if (!mSubscriptionList.isUnsubscribed()) {
                mSubscriptionList.unsubscribe();
            }
        }
    }
}
