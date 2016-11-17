package im.hua.mvp.framework;

/**
 * Created by hua on 16/10/12.
 */

public interface IMVPPresenter<V extends IMVPView> {
    void attachView(V view);

    void detachView(boolean retainInstance);

    V getView();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();
}
