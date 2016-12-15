package im.hua.mvp.framework;

/**
 * Created by hua on 16/10/12.
 */

public abstract class MVPListPresenter<V extends IMVPView> extends MVPPresenter<V> {
    protected boolean isLoadingMore(int offset) {
        return offset > 0;
    }
}
