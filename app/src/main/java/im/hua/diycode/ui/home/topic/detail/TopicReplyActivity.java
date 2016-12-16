package im.hua.diycode.ui.home.topic.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerTopicsComponent;
import im.hua.diycode.network.entity.TopicReplyEntity;
import im.hua.diycode.widget.rvwrapper.LoadMoreWrapper;
import im.hua.mvp.framework.MVPActivity;

public class TopicReplyActivity extends MVPActivity<TopicReplyView, TopicReplyPresenter> implements TopicReplyView{
    public static String EXTRA_KEY_TOPIC_ID = "topicId";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.topic_reply_content)
    EditText mTopicReplyContent;
    @BindView(R.id.topic_send)
    ImageView mTopicSend;

    private TopicReplyRVAdapter mReplyRVAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;

    private TopicReplyPresenter mTopicReplyPresenter;
    private String mTopicId = "";

    @Override
    public TopicReplyPresenter getPresenter() {
        if (null == mTopicReplyPresenter) {
            mTopicReplyPresenter = DaggerTopicsComponent.builder()
                    .applicationComponent((ApplicationComponent) getApplicationComponent())
                    .build()
                    .getTopicReplyPresenter();
        }
        return mTopicReplyPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_detail_reply_activity);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishWithAnimation();
            }
        });

        initAdapter();

        Intent intent = getIntent();
        if (null != intent) {
            mTopicId = intent.getStringExtra(EXTRA_KEY_TOPIC_ID);
            getPresenter().getRepliesOfTopic(mTopicId, 0);
        } else {
            showShortToast("Topic Id无效");
            finishWithAnimation();
        }
    }

    private void initAdapter() {
        if (null == mReplyRVAdapter) {
            mReplyRVAdapter = new TopicReplyRVAdapter(this);
            mLoadMoreWrapper = new LoadMoreWrapper(this, mRecyclerView, mReplyRVAdapter);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    getPresenter().getRepliesOfTopic(mTopicId,mReplyRVAdapter.getItemCount());
                }
            });
        }
    }

    public void onItemClick(View view, TopicReplyEntity entity) {
    }

    public void onFavClick(View view, TopicReplyEntity entity) {
    }

    public void onPraiseClick(View view, TopicReplyEntity entity) {
    }

    private void finishWithAnimation() {
        finish();
        overridePendingTransition(0, R.anim.slide_out_bottom);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishWithAnimation();
    }

    @Override
    public void showLoadingView(String message) {

    }

    @Override
    public void hideLoadingView(String message) {

    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void showDatas(List<TopicReplyEntity> datas) {
        mReplyRVAdapter.setDatas(datas);
    }

    @Override
    public void appendDatas(List<TopicReplyEntity> datas) {
        mReplyRVAdapter.appendTopics(datas);
    }

    @Override
    public void showLoadingMore() {
        mLoadMoreWrapper.loadingMore();
    }

    @Override
    public void noMoreData() {
        mLoadMoreWrapper.reachEnd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReplyRVAdapter = null;
    }
}
