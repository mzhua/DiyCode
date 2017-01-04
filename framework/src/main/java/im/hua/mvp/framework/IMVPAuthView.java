package im.hua.mvp.framework;

/**
 * Created by hua on 2017/1/4.
 */

public interface IMVPAuthView extends IMVPView {
    void forceToReLogin(String message);
}
