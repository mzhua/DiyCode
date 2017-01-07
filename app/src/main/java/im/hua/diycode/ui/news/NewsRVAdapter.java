package im.hua.diycode.ui.news;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.data.entity.NewsEntity;
import im.hua.diycode.util.ImageViewLoader;
import im.hua.mvp.framework.SimpleRVAdapter;

/**
 * Created by hua on 2016/12/16.
 */

public class NewsRVAdapter extends SimpleRVAdapter<NewsEntity, NewsRVAdapter.ItemViewHolder> {


    @Override
    public DiffUtil.Callback getDiffCallback(List<NewsEntity> data, List<NewsEntity> newData) {
        return null;
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.news_list_item;
    }

    @Override
    public ItemViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void bindView(ItemViewHolder holder, NewsEntity data, int position) {
        ImageViewLoader.loadUrl(holder.itemView.getContext(),data.getUser().getAvatar_url(),holder.mNewsUserHeader);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_user_header)
        ImageView mNewsUserHeader;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
