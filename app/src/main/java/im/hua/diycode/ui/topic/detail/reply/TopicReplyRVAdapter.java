package im.hua.diycode.ui.topic.detail.reply;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.Constants;
import im.hua.diycode.R;
import im.hua.diycode.data.entity.TopicReplyEntity;
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
    public void bindView(final ItemViewHolder holder, final TopicReplyEntity data, int position) {
        holder.mTopicReplyTime.setText(ShowTimeFormatter.getFormatTime(data.getUpdated_at(), ""));
        holder.mTopicReplyUserName.setText(data.getUser().getName());
        holder.mTopicReplyFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopicReplyActivity.onFavClick(v,data);
            }
        });
        holder.mTopicReplyPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopicReplyActivity.onPraiseClick(v,data);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopicReplyActivity.onItemClick(v,data);
            }
        });
        holder.mTopicReplyTitle.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("#")) {

                    return true;
                } else if (url.startsWith("/")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BASE_URL + url));
                    holder.itemView.getContext().startActivity(intent);
                    return true;
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    holder.itemView.getContext().startActivity(intent);
                    return true;
                }
            }
        });
        holder.mTopicReplyTitle.loadDataWithBaseURL(Constants.BASE_URL,data.getBody_html(), "text/html; charset=UTF-8;","UTF-8",null);
        ImageViewLoader.loadUrl(holder.itemView.getContext(), data.getUser().getAvatar_url(), holder.mTopicReplyUserHeader);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

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
        WebView mTopicReplyTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
