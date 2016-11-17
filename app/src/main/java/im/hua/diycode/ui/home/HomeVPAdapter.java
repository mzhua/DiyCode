package im.hua.diycode.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import im.hua.diycode.ui.topic.NewsFragment;
import im.hua.diycode.ui.topic.SitesFragment;
import im.hua.diycode.ui.topic.TopicsFragment;

/**
 * Created by hua on 2016/11/17.
 */

public class HomeVPAdapter extends FragmentStatePagerAdapter {
    public HomeVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = NewsFragment.getInstance();
                break;
            case 2:
                fragment = SitesFragment.getInstance();
                break;
            default:
                fragment = TopicsFragment.getInstance();

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case 1:
                title = "News";
                break;
            case 2:
                title = "Sites";
                break;
            default:
                title = "Topics";

        }
        return title;
    }
}
