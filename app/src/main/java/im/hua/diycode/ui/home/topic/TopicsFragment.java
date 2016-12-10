package im.hua.diycode.ui.home.topic;


import android.os.Bundle;
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
import im.hua.diycode.network.entity.TopicEntity;
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
        getPresenter().getTopics(0);
    }

    @Override
    public void showTopics(List<TopicEntity> topics) {
        initAdapter();
        mTopicsRVAdapter.setTopics(topics);
    }

    private void initAdapter() {
        if (null == mTopicsRVAdapter) {
            mTopicsRVAdapter = new TopicsRVAdapter();
            LoadMoreWrapper wrapper = new LoadMoreWrapper(mTopicsRVAdapter);
            wrapper.setLoadMoreView(R.layout.load_more);
            wrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    getPresenter().getTopics(mTopicsRVAdapter == null ? 0 : mTopicsRVAdapter.getItemCount());
                }
            });
            mRecyclerView.setAdapter(wrapper);
        }
    }

    @Override
    public void appendTopics(List<TopicEntity> topics) {
        initAdapter();
        mTopicsRVAdapter.appendTopics(topics);
    }

    @Override
    public void noMoreData() {
        Toast.makeText(getActivity(), "已经到底啦", Toast.LENGTH_SHORT).show();
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
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}