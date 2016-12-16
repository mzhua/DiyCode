package im.hua.mvp.framework;

import java.util.List;

/**
 * Created by hua on 16/10/12.
 */

public abstract class MVPListPresenter<V extends IMVPListView<E>, E> extends MVPPresenter<V> {
    protected final int PAGE_SIZE = 20;

    protected boolean isLoadingMore(int offset) {
        return offset > 0;
    }

    /**
     * @param datas
     * @param offset
     */
    protected void resolveNext(List<E> datas, int offset) {
        getView().hideLoadingView("");
        if (null == datas || datas.size() == 0 || datas.size() < PAGE_SIZE) {
            getView().noMoreData();
        } else {
            getView().showLoadingMore();
            if (isLoadingMore(offset)) {
                getView().appendDatas(datas);
            } else {
                getView().showDatas(datas);
            }
        }
    }
}
