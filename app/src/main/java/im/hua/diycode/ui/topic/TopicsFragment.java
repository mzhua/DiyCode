package im.hua.diycode.ui.topic;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerTopicsComponent;
import im.hua.diycode.data.entity.TopicEntity;
import im.hua.diycode.ui.login.LoginActivity;
import im.hua.diycode.ui.topic.detail.TopicDetailActivity;
import im.hua.diycode.widget.rvwrapper.LoadMoreWrapper;
import im.hua.mvp.framework.MVPFragment;

public class TopicsFragment extends MVPFragment<TopicsView, TopicsPresenter> implements TopicsView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;

    private TopicsPresenter mTopicsPresenter;

    private TopicsRVAdapter mTopicsRVAdapter;

    @Override
    public TopicsPresenter getPresenter() {
        if (null == mTopicsPresenter) {
            mTopicsPresenter = DaggerTopicsComponent.builder().applicationComponent((ApplicationComponent) getApplicationComponent()).build().getTopicsPresenter();
        }
        return mTopicsPresenter;
    }

    public TopicsFragment() {
    }

    public static TopicsFragment getInstance() {
        return new TopicsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.topic_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getTopics(0);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        initAdapter();
        getPresenter().getTopics(0);
    }

    @Override
    public void showDatas(List<TopicEntity> topics) {
        mTopicsRVAdapter.setTopics(topics);
    }

    private LoadMoreWrapper mLoadMoreWrapper;

    private void initAdapter() {
        if (null == mTopicsRVAdapter) {
            mTopicsRVAdapter = new TopicsRVAdapter(this);
            mLoadMoreWrapper = new LoadMoreWrapper(getActivity(), mRecyclerView, mTopicsRVAdapter);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    getPresenter().getTopics(mTopicsRVAdapter == null ? 0 : mTopicsRVAdapter.getItemCount());
                }
            });
        }
    }

    public void onItemClick(View view, TopicEntity entity) {
        Intent intent = new Intent(getActivity(), TopicDetailActivity.class);
        intent.putExtra("topic", entity);
        startActivity(intent);
    }

    public void onFavClick(View view, TopicEntity entity) {
        getPresenter().favTopic(entity.getId(), true);
    }

    public void onPraiseClick(View view, TopicEntity entity) {
        getPresenter().followTopic(entity.getId(), true);
    }

    @Override
    public void appendDatas(List<TopicEntity> topics) {
        mTopicsRVAdapter.appendTopics(topics);
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
    public void favSuccess(String topicId, boolean fav) {
        showShortToast("收藏成功");
    }

    @Override
    public void favFailed(String topicId, boolean fav) {
        showShortToast("收藏失败");
    }

    @Override
    public void followSuccess(String topicId, boolean follow) {
        showShortToast("follow成功");
    }

    @Override
    public void followFailed(String topicId, boolean follow) {
        showShortToast("follow失败");
    }

    @Override
    public void reLogin(String message) {
        showShortToast("AccessToken失效，请重新登录");
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.y_no_move);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTopicsRVAdapter = null;
    }

    @Override
    public void showLoadingView(String message) {
        mRefresh.post(new Runnable() {
            @Override
            public void run() {
                if (null != mRefresh) {
                    mRefresh.setRefreshing(true);
                }
            }
        });
    }

    @Override
    public void hideLoadingView(String message) {
        mRefresh.post(new Runnable() {
            @Override
            public void run() {
                if (null != mRefresh) {
                    mRefresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
