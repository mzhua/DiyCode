package im.hua.diycode.ui.home.topic.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.hua.diycode.R;
import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerTopicsComponent;
import im.hua.diycode.network.entity.TopicEntity;
import im.hua.diycode.util.DrawTextUtil;
import im.hua.diycode.util.FileUtil;
import im.hua.diycode.util.ImageViewLoader;
import im.hua.diycode.util.ShowTimeFormatter;
import im.hua.diycode.widget.MarkdownView;
import im.hua.mvp.framework.MVPActivity;

import static im.hua.diycode.R.id.fab;

public class TopicDetailActivity extends MVPActivity<TopicDetailView, TopicDetailPresenter> implements TopicDetailView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(fab)
    FloatingActionButton mFab;
    @BindView(R.id.topic_detail_content)
    MarkdownView mTopicDetailContent;
    @BindView(R.id.topic_detail_title)
    TextView mTopicDetailTitle;
    @BindView(R.id.topic_detail_half_background)
    LinearLayout mTopicDetailHalfBackground;
    @BindView(R.id.topic_detail_head)
    ImageView mTopicDetailHead;
    @BindView(R.id.topic_detail_name)
    TextView mTopicDetailName;
    @BindView(R.id.topic_detail_node_name)
    TextView mTopicDetailNodeName;
    @BindView(R.id.topic_detail_time)
    TextView mTopicDetailTime;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;

    private TopicEntity mTopic;

    private TopicDetailPresenter mTopicDetailPresenter;

    @Override
    public TopicDetailPresenter getPresenter() {
        if (null == mTopicDetailPresenter) {
            mTopicDetailPresenter = DaggerTopicsComponent.builder().applicationComponent((ApplicationComponent) getApplicationComponent())
                    .build().getTopicDetailPresenter();
        }
        return mTopicDetailPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_detail_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (null != mTopic) {
                    getPresenter().getTopicsDetail(mTopic.getId());
                }
            }
        });

        WebSettings settings = mTopicDetailContent.getSettings();
        settings.setJavaScriptEnabled(true);
        String userAgent = "Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86 Build/MASTER; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile Safari/537.36";
        settings.setUserAgentString(userAgent);
        mTopicDetailContent.setOpenUrlInBrowser(true);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicDetailActivity.this, TopicReplyActivity.class);
                intent.putExtra(TopicReplyActivity.EXTRA_KEY_TOPIC_ID, mTopic.getId());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.y_no_move);
            }
        });


        Intent intent = getIntent();
        if (null != intent) {
            mTopic = intent.getParcelableExtra("topic");
            mTopicDetailTitle.setText(mTopic.getTitle());
            mTopicDetailName.setText(mTopic.getUser().getName());
            mTopicDetailNodeName.setText(mTopic.getNode_name());
            mTopicDetailTime.setText(ShowTimeFormatter.getFormatTime(mTopic));
            ImageViewLoader.loadUrl(this, mTopic.getUser().getAvatar_url(), mTopicDetailHead, ImageViewLoader.NO_PLACE_HOLDER, ImageViewLoader.Shape.CIRCLE);
            getPresenter().getTopicsDetail(mTopic.getId());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_topic_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                /*Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, content);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);*/
                shareContent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 截屏分享
     */
    private void shareContent() {
        final View contentView = findViewById(android.R.id.content);
        if (contentView.getDrawingCache() != null) {
            contentView.destroyDrawingCache();
        }

        contentView.post(new Runnable() {
            @Override
            public void run() {
                contentView.setDrawingCacheEnabled(true);
                contentView.buildDrawingCache();

                Bitmap bm = contentView.getDrawingCache();
                Bitmap bmp = null;
                try {

                    bmp = bm.copy(Bitmap.Config.ARGB_8888, true);
                    Canvas c = new Canvas(bmp);
                    String text = "share by Diycode Android";
                    Paint p = new Paint();
                    p.setTypeface(Typeface.DEFAULT_BOLD);
                    p.setTextSize(35);
                    p.setColor(getResources().getColor(R.color.colorAccent));
                    int width = (int) p.measureText(text);
                    int yPos = (int) (c.getHeight()
                            - DrawTextUtil.getTextHeight(text, p) - 10);
                    c.drawText(text, bmp.getWidth() - width - 10, yPos, p);

                    FileOutputStream outputStream;
                    File file = FileUtil.createTempFile(TopicDetailActivity.this, "share.jpg");
                    if (file != null) {
                        outputStream = new FileOutputStream(file);
                        bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        shareImage(Uri.parse("file:///" + file.getAbsolutePath()));
                    }
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                    showShortToast("分享失败");
                } finally {
                    if (null != bm) {
                        bm.recycle();
                        bm = null;
                    }
                    if (null != bmp) {
                        bmp.recycle();
                        bmp = null;
                    }
                }
            }
        });
    }

    private void shareImage(Uri uriToImage) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share_to)));
    }

    @Override
    public void showLoadingView(String message) {
        setRefresh(true,mRefresh);
    }

    @Override
    public void hideLoadingView(String message) {
        setRefresh(false,mRefresh);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        setRefresh(false,mRefresh);
    }

    @Override
    public void showTopicDetailInfo(TopicEntity topic) {
        mTopicDetailTitle.setText(topic.getTitle());
        ImageViewLoader.loadUrl(this, topic.getUser().getAvatar_url(), null, ImageViewLoader.NO_PLACE_HOLDER, ImageViewLoader.Shape.CIRCLE);
//        Glide.with(this)
//                .load(mTopic.getUser().getAvatar_url())
//                .into(mTopicDetailHead);
        mTopicDetailContent.setMarkDownText(topic.getBody());
//        mTopicDetailContent.loadData(body_html, "text/html; charset=UTF-8;", null);
    }
}
