package im.hua.diycode.ui.home.topic;


import android.os.Bundle;
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
import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerTopicsComponent;
import im.hua.diycode.network.entity.TopicEntity;
import im.hua.mvp.framework.MVPFragment;

public class TopicsFragment extends MVPFragment<TopicsView, TopicsPresenter> implements TopicsView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        getPresenter().getTopics();
    }

    @Override
    public void showTopics(List<TopicEntity> topics) {
        if (null == mTopicsRVAdapter) {
            mTopicsRVAdapter = new TopicsRVAdapter();
            mRecyclerView.setAdapter(mTopicsRVAdapter);
        }
        mTopicsRVAdapter.setTopics(topics);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTopicsRVAdapter = null;
    }

}
