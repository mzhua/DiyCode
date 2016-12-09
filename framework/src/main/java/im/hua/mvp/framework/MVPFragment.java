package im.hua.mvp.framework;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by hua on 16/10/11.
 */

public abstract class MVPFragment<V extends IMVPView, P extends IMVPPresenter<V>> extends BaseFragment implements IMVPView {
    public abstract P getPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        P presenter = getPresenter();
        if (null != presenter) {
            presenter.attachView((V) this);
        } else {
            throw new IllegalArgumentException("the presenter can not be null");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView(isRetainingInstance());
        getPresenter().destroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    /**
     * 界面销毁后是否继续presenter中的操作(比如点赞的操作，及时界面销毁了，理论上还是要继续完成请求)
     * 如果想要继续操作，则override本方法，返回true
     *
     * @return
     */
    public boolean isRetainingInstance() {
        return false;
    }
}
