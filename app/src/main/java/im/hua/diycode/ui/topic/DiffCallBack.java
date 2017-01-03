package im.hua.diycode.ui.topic;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import java.util.List;

import im.hua.diycode.network.entity.TopicEntity;

/**
 * Created by hua on 2016/12/10.
 */

public class DiffCallBack extends DiffUtil.Callback {

    private List<TopicEntity> mData;
    private List<TopicEntity> mNewData;

    public DiffCallBack(List<TopicEntity> data, List<TopicEntity> newData) {
        mData = data;
        mNewData = newData;
    }

    @Override
    public int getOldListSize() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData == null ? 0 : mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mData.get(oldItemPosition).getId() == mNewData.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        TopicEntity oldData = mData.get(oldItemPosition);
        TopicEntity newData = mNewData.get(newItemPosition);
        return oldData.getTitle().equals(newData.getTitle()) &&
                oldData.getNode_name().equals(newData.getNode_name()) &&
                oldData.getUpdated_at().equals(newData.getUpdated_at()) &&
                (!TextUtils.isEmpty(oldData.getUser().getName()) && oldData.getUser().getName().equals(newData.getUser().getName())) &&
                oldData.getUser().getAvatar_url().equals(newData.getUser().getAvatar_url());
    }
}