package im.hua.mvp.framework;

import java.util.List;

/**
 * Created by hua on 2017/1/5.
 */

public interface IMVPListAuthView<E> extends IMVPAuthView {
    void showDatas(List<E> var1);

    void appendDatas(List<E> var1);

    void showLoadingMore();

    void noMoreData();
}