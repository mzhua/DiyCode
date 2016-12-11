package im.hua.diycode.ui.home.topic;

/**
 * Created by hua on 2016/12/10.
 */

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.databinding.TopicListItemBinding;
import im.hua.diycode.network.entity.TopicEntity;
import im.hua.diycode.util.MessageShowTimeUtil;

public class TopicsRVAdapter extends RecyclerView.Adapter<TopicsRVAdapter.ItemViewHolder> {
    private List<TopicEntity> mTopics;

    private TopicsFragment mTopicsFragment;

    public TopicsRVAdapter(TopicsFragment topicsFragment) {
        mTopicsFragment = topicsFragment;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        TopicEntity topic = mTopics.get(position);
        holder.mItemBinding.setTopic(topic);
        holder.mItemBinding.setHandler(mTopicsFragment);

        Glide.with(holder.itemView.getContext())
                .load(topic.getUser().getAvatar_url())
                .into(holder.mTopicUserHeader);

        //2016-12-10T01:53:12.465+08:00
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.CHINESE);
        try {
            String replied_at = topic.getReplied_at();
            Date updateDate = simpleDateFormat.parse(TextUtils.isEmpty(replied_at) ? topic.getUpdated_at() : replied_at);
            holder.mTopicTime.setText(MessageShowTimeUtil.getFormatTime(updateDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mTopics == null ? 0 : mTopics.size();
    }

    public void setTopics(List<TopicEntity> topics) {
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(this.mTopics, topics), true);
//        diffResult.dispatchUpdatesTo(this);
        mTopics = topics;
        notifyDataSetChanged();
    }

    public void appendTopics(List<TopicEntity> topics) {
        if (null == topics) {
            return;
        }
        topics.addAll(0, this.mTopics);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(this.mTopics, topics), true);
        diffResult.dispatchUpdatesTo(this);
        mTopics = topics;
    }

    public List<TopicEntity> getTopics() {
        return mTopics;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.topic_user_header)
        ImageView mTopicUserHeader;
        @BindView(R.id.topic_time)
        TextView mTopicTime;
        @BindView(R.id.topic_pic)
        ImageView mTopicPic;

        TopicListItemBinding mItemBinding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemBinding = TopicListItemBinding.bind(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}