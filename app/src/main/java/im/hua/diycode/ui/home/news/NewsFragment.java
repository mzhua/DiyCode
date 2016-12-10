package im.hua.diycode.ui.home.news;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import im.hua.diycode.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

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
        return inflater.inflate(R.layout.topic_fragment, container, false);
    }

}
