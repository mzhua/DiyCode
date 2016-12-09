package im.hua.diycode.ui.topic;


import android.os.Bundle;
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
import im.hua.mvp.framework.MVPFragment;

public class TopicsFragment extends MVPFragment<TopicsView, TopicsPresenter> implements TopicsView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public TopicsPresenter getPresenter() {
        return DaggerTopicsComponent.builder().applicationComponent((ApplicationComponent) getApplicationComponent()).build().getTopicsPresenter();
    }

    public TopicsFragment() {
    }

    public static TopicsFragment getInstance() {
        TopicsFragment fragment = new TopicsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().getTopicsDetail("485");
//        getPresenter().getTopics();
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
    public void showTopics(List<TopicEntity> topics) {
        Toast.makeText(getActivity(), "topics.size():" + topics.size(), Toast.LENGTH_SHORT).show();

    }

    class TopicsRVAdapter extends RecyclerView.Adapter<TopicsRVAdapter.ItemViewHolder> {

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_item, null, false));
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {

            public ItemViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
