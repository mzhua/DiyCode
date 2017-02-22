package im.hua.diycode.data.util;

import javax.net.ssl.SSLHandshakeException;

import im.hua.diycode.data.MyException;
import im.hua.mvp.framework.IMVPAuthView;
import rx.Subscriber;

/**
 * Created by hua on 2017/1/7.
 */

public abstract class CommonErrorSubscriber<T> extends Subscriber<T> {
    public abstract IMVPAuthView getMVPAuthView();

    @Override
    public void onCompleted() {
        getMVPAuthView().hideLoadingView("");
    }

    @Override
    public void onError(Throwable e) {
        getMVPAuthView().hideLoadingView(e.getMessage());
        if (getMVPAuthView() != null) {
            if (e instanceof MyException) {
                if (((MyException) e).getCode() == 401) {
                    getMVPAuthView().forceToReLogin(e.getMessage());
                }
            } else if(e instanceof SSLHandshakeException){
                getMVPAuthView().showErrorMessage("请校准您的手机系统时间！");
            }
        }
    }
}
