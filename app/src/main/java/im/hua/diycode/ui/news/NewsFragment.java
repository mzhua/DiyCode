package im.hua.diycode.ui.news;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.data.entity.NewsEntity;
import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerNewsComponent;
import im.hua.diycode.widget.rvwrapper.LoadMoreWrapper;
import im.hua.mvp.framework.MVPFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends MVPFragment<INewView, NewsPresenter> implements INewView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;

    private NewsRVAdapter mNewsRVAdapter;
    private NewsPresenter mNewsPresenter;
    private LoadMoreWrapper mLoadMoreWrapper;

    public NewsFragment() {
    }

    public static NewsFragment getInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int offset = (mNewsRVAdapter == null ? 0 : mNewsRVAdapter.getItemCount());
                getPresenter().getNewsList(null, offset);
            }
        });
        initAdapter();
        getPresenter().getNewsList(null, 0);
    }

    public void onItemClick(View view, NewsEntity newsEntity) {

    }

    @Override
    public NewsPresenter getPresenter() {
        if (null == mNewsPresenter) {
            mNewsPresenter = DaggerNewsComponent.builder()
                    .applicationComponent((ApplicationComponent) getApplicationComponent())
                    .build()
                    .getNewsPresenter();
        }
        return mNewsPresenter;
    }

    @Override
    public void showLoadingView(String message) {

    }

    @Override
    public void hideLoadingView(String message) {
        setRefresh(false, mRefresh);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void showDatas(List<NewsEntity> var1) {
        mNewsRVAdapter.setDatas(var1);
    }

    private void initAdapter() {
        if (mNewsRVAdapter == null) {
            mNewsRVAdapter = new NewsRVAdapter(this);
            mLoadMoreWrapper = new LoadMoreWrapper(getActivity(), mRecyclerView, mNewsRVAdapter);
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    getPresenter().getNewsList(null,mNewsRVAdapter == null ? 0 : mNewsRVAdapter.getItemCount());
                }
            });
            mRecyclerView.setAdapter(mLoadMoreWrapper);
        }
    }

    @Override
    public void appendDatas(List<NewsEntity> var1) {
        mNewsRVAdapter.appendDatas(var1);
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
    public void forceToReLogin(String message) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mNewsRVAdapter = null;
    }
}
