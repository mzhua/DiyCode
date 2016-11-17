package im.hua.diycode.ui.topic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import im.hua.diycode.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends Fragment {

    public TopicFragment() {
    }

    public static TopicFragment getInstance() {
        TopicFragment fragment = new TopicFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.topic_fragment, container, false);
    }

}
