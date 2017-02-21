package im.hua.diycode.data.test.repository;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import im.hua.diycode.data.api.NewsAPI;
import im.hua.diycode.data.repository.INewsRepository;
import im.hua.diycode.data.repository.impl.NewsRepository;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertNotNull;

/**
 * Created by hua on 2017/2/20.
 */
public class NewsRepositoryTest {
    private INewsRepository mNewsRepository;
    @Mock
    private NewsAPI mNewsAPI;

    @BeforeClass
    public static void init() {
        /**
         * 让Schedulers.io()变同步
         */
        RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook(){
            @Override
            public Scheduler getIOScheduler() {
                return Schedulers.immediate();
            }
        });
        /**
         * 让AndroidSchedulers.mainThread()返回当前线程，这样就可以不需要Android环境，摆脱Robolectric
         */
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook(){
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mNewsRepository = new NewsRepository(mNewsAPI);
    }

    @Test
    public void testNewApiGetNewsCalled() throws InterruptedException {
        Integer nodeId = 0, offset = 0, limit = 0;

        Response<String> responseStr = Response.success("[\n" +
                "  {\n" +
                "    \"id\": 2009,\n" +
                "    \"title\": \"20个很棒的android开源项目帮助你提升开发技能\",\n" +
                "    \"created_at\": \"2017-02-21T09:42:15.452+08:00\",\n" +
                "    \"updated_at\": \"2017-02-21T09:45:29.674+08:00\",\n" +
                "    \"user\": {\n" +
                "      \"id\": 3789,\n" +
                "      \"login\": \"tangmanong\",\n" +
                "      \"name\": \"唐码农\",\n" +
                "      \"avatar_url\": \"https://diycode.cc/system/letter_avatars/2/T/163_163_163/240.png\"\n" +
                "    },\n" +
                "    \"node_name\": \"Android\",\n" +
                "    \"node_id\": 1,\n" +
                "    \"last_reply_user_id\": null,\n" +
                "    \"last_reply_user_login\": null,\n" +
                "    \"replied_at\": null,\n" +
                "    \"address\": \"http://mp.weixin.qq.com/s/0YyuvWQH-uT-Pp9pOhtePw\",\n" +
                "    \"replies_count\": 0\n" +
                "  }]");
        Observable<Response<String>> response = Observable.just(responseStr);
        assertNotNull(response);
        Mockito.when(mNewsAPI.getNews(nodeId, offset, limit)).thenReturn(response);
        mNewsRepository.getNewsList(nodeId, offset, limit);
        Mockito.verify(mNewsAPI).getNews(nodeId, offset, limit);
    }
}
