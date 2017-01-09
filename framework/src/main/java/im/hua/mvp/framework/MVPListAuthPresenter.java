package im.hua.mvp.framework;

import java.util.List;

/**
 * Created by hua on 16/10/12.
 */

public abstract class MVPListAuthPresenter<V extends IMVPListAuthView<E>, E> extends MVPPresenter<V> {

    protected int getPageSize() {
        return 20;
    }

    private boolean isLoadingMore(int offset) {
        return offset > 0;
    }

    /**
     * @param datas
     * @param offset
     */
    protected void resolveNext(List<E> datas, int offset) {
        getView().hideLoadingView("");
        if (null == datas) {
            getView().noMoreData();
        } else {
            getView().showLoadingMore();
            if (isLoadingMore(offset)) {
                getView().appendDatas(datas);
            } else {
                getView().showDatas(datas);
            }
            if (datas.size() < getPageSize()) {
                getView().noMoreData();
            }
        }
    }
}
