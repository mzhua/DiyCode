package im.hua.mvp.framework;

import rx.Subscriber;

/**
 * 可以后台运行的请求，即页面销毁了也可以继续完成请求
 * Created by hua on 2016/12/15.
 */

public abstract class BackgroundableSubscriber<E> extends Subscriber<E> {
    public abstract IMVPView getXView();

    public abstract void onXCompleted();

    public abstract void onXError(Throwable e);

    public abstract void onXNext(E data);

    @Override
    public void onCompleted() {
        if (getXView() != null) {
            onXCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (getXView() != null) {
            onXError(e);
        }
    }

    @Override
    public void onNext(E data) {
        if (getXView() != null) {
            onXNext(data);
        }
    }
}
