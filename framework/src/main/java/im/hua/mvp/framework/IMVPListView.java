package im.hua.mvp.framework;

import java.util.List;

/**
 * Created by hua on 16/10/12.
 */

public interface IMVPListView<E> extends IMVPView{
    void showDatas(List<E> datas);

    void appendDatas(List<E> datas);

    void showLoadingMore();

    void noMoreData();
}
