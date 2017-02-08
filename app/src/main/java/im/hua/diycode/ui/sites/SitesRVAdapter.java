package im.hua.diycode.ui.sites;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.data.entity.SiteEntity;
import im.hua.diycode.util.ImageViewLoader;
import im.hua.mvp.framework.SimpleRVAdapter;

/**
 * Created by hua on 2017/1/8.
 */

public class SitesRVAdapter extends SimpleRVAdapter<SiteEntity, SitesRVAdapter.ItemViewHolder> {

    @Override
    public DiffUtil.Callback getDiffCallback(List<SiteEntity> data, List<SiteEntity> newData) {
        return null;
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.sites_item;
    }

    @Override
    public ItemViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    InnerGridRVAdapter innerGridRVAdapter;

    @Override
    public void bindView(ItemViewHolder holder, SiteEntity data, int position) {
        holder.mTvName.setText(data.getName());
        if (null == innerGridRVAdapter) {
            innerGridRVAdapter = new InnerGridRVAdapter();
        }
        holder.mRecyclerView.setAdapter(innerGridRVAdapter);
        innerGridRVAdapter.setDatas(data.getSites());
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class InnerGridRVAdapter extends SimpleRVAdapter<SiteEntity.SitesEntity, InnerGridRVAdapter.ItemViewHolder> {

        @Override
        public DiffUtil.Callback getDiffCallback(List<SiteEntity.SitesEntity> data, List<SiteEntity.SitesEntity> newData) {
            return null;
        }

        @Override
        public int getItemLayoutRes() {
            return R.layout.sites_inner_item;
        }

        @Override
        public InnerGridRVAdapter.ItemViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void bindView(final InnerGridRVAdapter.ItemViewHolder holder, final SiteEntity.SitesEntity data, int position) {
            ImageViewLoader.loadUrl(holder.itemView.getContext(),data.getAvatar_url(),holder.mIvAvatar);
            holder.mTvName.setText(data.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl()));
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.iv_avatar)
            ImageView mIvAvatar;
            @BindView(R.id.tv_name)
            TextView mTvName;

            public ItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
