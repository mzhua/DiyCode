package im.hua.diycode.ui.news;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.data.entity.NewsEntity;
import im.hua.diycode.databinding.NewsListItemBinding;
import im.hua.diycode.util.ImageViewLoader;
import im.hua.diycode.util.ShowTimeFormatter;
import im.hua.mvp.framework.SimpleRVAdapter;

/**
 * Created by hua on 2016/12/16.
 */

public class NewsRVAdapter extends SimpleRVAdapter<NewsEntity, NewsRVAdapter.ItemViewHolder> {

    private NewsFragment mNewsFragment;

    public NewsRVAdapter(NewsFragment newsFragment) {
        mNewsFragment = newsFragment;
    }

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
        ImageViewLoader.loadUrl(holder.itemView.getContext(), data.getUser().getAvatar_url(), holder.mNewsUserHeader);
        holder.mNewsTime.setText(ShowTimeFormatter.getFormatTime(data.getReplied_at(),data.getUpdated_at()));
        holder.mBind.setNews(data);
        holder.mBind.setHandler(mNewsFragment);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final NewsListItemBinding mBind;
        @BindView(R.id.news_user_header)
        ImageView mNewsUserHeader;
        @BindView(R.id.news_time)
        TextView mNewsTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mBind = NewsListItemBinding.bind(itemView);
        }
    }
}
