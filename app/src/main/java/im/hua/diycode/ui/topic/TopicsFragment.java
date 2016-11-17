package im.hua.diycode.ui.topic;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import im.hua.diycode.R;
import im.hua.mvp.framework.MVPFragment;

public class TopicsFragment extends MVPFragment<TopicsView, TopicsPresenter> {

    @Override
    public TopicsPresenter getPresenter() {
        return null;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.topic_fragment, container, false);
    }
}
