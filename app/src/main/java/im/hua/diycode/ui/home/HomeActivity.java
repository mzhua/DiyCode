package im.hua.diycode.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.data.entity.UserEntity;
import im.hua.diycode.data.util.AuthUtil;
import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerHomeComponent;
import im.hua.diycode.ui.login.LoginActivity;
import im.hua.diycode.util.ImageViewLoader;
import im.hua.mvp.framework.BaseAppCompatActivity;
import rx.Subscriber;

public class HomeActivity extends BaseAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    AuthUtil mAuthUtil;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.home_view_pager)
    ViewPager mHomeViewPager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.home_tab_layout)
    TabLayout mHomeTabLayout;

    private ImageView mIvUserHead;
    private TextView mTvUserName;

    private HomeVPAdapter mHomeVPAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHomeComponent.builder()
                .applicationComponent((ApplicationComponent) getApplicationComponent())
                .build()
                .inject(this);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
        View headerView = mNavView.getHeaderView(0);
        mIvUserHead = (ImageView) headerView.findViewById(R.id.nav_user_head);
        mTvUserName = (TextView) headerView.findViewById(R.id.nav_user_name);
        mIvUserHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mAuthUtil.hasLogin()) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.y_no_move);
                }
            }
        });
        mHomeVPAdapter = new HomeVPAdapter(getFragmentManager());
        mHomeViewPager.setAdapter(mHomeVPAdapter);

        mHomeTabLayout.setupWithViewPager(mHomeViewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuthUtil.getUserInfo(new Subscriber<UserEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserEntity userEntity) {
                mTvUserName.setText(userEntity.getName());
                ImageViewLoader.loadUrl(HomeActivity.this,userEntity.getAvatar_url(),mIvUserHead);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_post) {
            // Handle the camera action
        } else if (id == R.id.nav_my_collection) {

        } else if (id == R.id.nav_my_comment) {

        } else if (id == R.id.nav_my_share) {

        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
