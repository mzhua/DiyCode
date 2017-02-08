package im.hua.mvp.framework;

import android.support.annotation.LayoutRes;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hua on 2016/12/13.
 */

public abstract class SimpleRVAdapter<Bean, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<Bean> mDatas;

    public abstract DiffUtil.Callback getDiffCallback(List<Bean> data, List<Bean> newData);

    @LayoutRes
    public abstract int getItemLayoutRes();

    public abstract VH getItemViewHolder(View view);

    public abstract void bindView(VH holder, Bean data, int position);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return getItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(getItemLayoutRes(), null));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        bindView(holder, this.mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setDatas(List<Bean> datas) {
        if (null == datas) {
            datas = new ArrayList<>();
        }
        DiffUtil.Callback diffCallback = getDiffCallback(this.mDatas, datas);
        if (diffCallback != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, true);
            diffResult.dispatchUpdatesTo(this);
        }

        if (null != mDatas) {
            mDatas.clear();
        } else {
            mDatas = new ArrayList<>(datas.size());
        }
        mDatas.addAll(datas);
        if (diffCallback == null) {
            notifyDataSetChanged();
        }
    }

    public void appendDatas(List<Bean> datas) {
        if (null == datas) {
            return;
        }
        datas.addAll(0, this.mDatas);
        DiffUtil.Callback diffCallback = getDiffCallback(this.mDatas, datas);
        if (diffCallback != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, true);
            diffResult.dispatchUpdatesTo(this);
        }
        if (null != mDatas) {
            mDatas.clear();
        } else {
            mDatas = new ArrayList<>(datas.size());
        }
        mDatas.addAll(datas);
        if (diffCallback == null) {
            notifyDataSetChanged();
        }
    }

    public List<Bean> getDatas() {
        return mDatas;
    }
}
