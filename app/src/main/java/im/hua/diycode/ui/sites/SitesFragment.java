package im.hua.diycode.ui.sites;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.data.entity.SiteEntity;
import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerSitesComponent;
import im.hua.mvp.framework.MVPFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SitesFragment extends MVPFragment<ISitesView, SitesPresenter> implements ISitesView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    private SitesRVAdapter mSitesRVAdapter;

    private SitesPresenter mSitesPresenter;

    public SitesFragment() {
    }

    public static SitesFragment getInstance() {
        SitesFragment fragment = new SitesFragment();
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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getSiteList();
            }
        });
        getPresenter().getSiteList();
    }

    @Override
    public SitesPresenter getPresenter() {
        if (mSitesPresenter == null) {
            mSitesPresenter = DaggerSitesComponent.builder()
                    .applicationComponent((ApplicationComponent) getApplicationComponent())
                    .build()
                    .getSitesPresenter();
        }
        return mSitesPresenter;
    }

    @Override
    public void showLoadingView(String message) {
        setRefresh(true, mRefresh);
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
    public void showDatas(List<SiteEntity> var1) {
        if (null == mSitesRVAdapter) {
            mSitesRVAdapter = new SitesRVAdapter();
            mRecyclerView.setAdapter(mSitesRVAdapter);
        }
        mSitesRVAdapter.setDatas(var1);
    }

    @Override
    public void appendDatas(List<SiteEntity> var1) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

    }

    @Override
    public void forceToReLogin(String message) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSitesRVAdapter = null;
    }
}
