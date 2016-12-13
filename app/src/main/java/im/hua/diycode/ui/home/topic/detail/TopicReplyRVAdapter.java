package im.hua.diycode.ui.home.topic.detail;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.databinding.TopicReplyListItemBinding;
import im.hua.diycode.network.entity.TopicReplyEntity;
import im.hua.diycode.util.ImageViewLoader;
import im.hua.diycode.util.ShowTimeFormatter;
import im.hua.mvp.framework.SimpleRVAdapter;

/**
 * Created by hua on 2016/12/13.
 */

public class TopicReplyRVAdapter extends SimpleRVAdapter<TopicReplyEntity, TopicReplyRVAdapter.ItemViewHolder> {
    private TopicReplyActivity mTopicReplyActivity;

    public TopicReplyRVAdapter(TopicReplyActivity topicReplyActivity) {
        mTopicReplyActivity = topicReplyActivity;
    }

    @Override
    public DiffUtil.Callback getDiffCallback(List<TopicReplyEntity> data, List<TopicReplyEntity> newData) {
        return null;
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.topic_reply_list_item;
    }

    @Override
    public ItemViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void bindView(ItemViewHolder holder, TopicReplyEntity data) {
        holder.mItemBinding.setReply(data);
        holder.mItemBinding.setHandler(mTopicReplyActivity);
        holder.mTopicReplyTime.setText(ShowTimeFormatter.getFormatTime(data.getUpdated_at(), ""));
        holder.mTopicReplyTitle.setText(Html.fromHtml(data.getBody_html()));
        ImageViewLoader.loadUrl(holder.itemView.getContext(), data.getUser().getAvatar_url(), holder.mTopicReplyUserHeader);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TopicReplyListItemBinding mItemBinding;

        @BindView(R.id.topic_reply_user_header)
        ImageView mTopicReplyUserHeader;
        @BindView(R.id.topic_reply_fav)
        ImageView mTopicReplyFav;
        @BindView(R.id.topic_reply_praise)
        ImageView mTopicReplyPraise;
        @BindView(R.id.topic_reply_user_name)
        TextView mTopicReplyUserName;
        @BindView(R.id.topic_reply_node_name)
        TextView mTopicReplyNodeName;
        @BindView(R.id.topic_reply_time)
        TextView mTopicReplyTime;
        @BindView(R.id.topic_reply_title)
        TextView mTopicReplyTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemBinding = TopicReplyListItemBinding.bind(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
