package im.hua.diycode.data.test.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import im.hua.diycode.data.api.NewsAPI;
import im.hua.diycode.data.entity.NewsEntity;
import im.hua.diycode.data.repository.INewsRepository;
import im.hua.diycode.data.repository.impl.NewsRepository;
import im.hua.diycode.data.util.GsonConverterUtil;
import im.hua.diycode.data.util.ResponseCompose;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

import static android.R.attr.offset;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;

/**
 * Created by hua on 2017/2/20.
 */
public class NewsRepositoryTest {
    private INewsRepository mNewsRepository;
    @Mock
    private NewsAPI mNewsAPI;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mNewsRepository = new NewsRepository(mNewsAPI);
    }

    @Test
    public void testNewApiGetNewsCalled() throws InterruptedException {
        Integer nodeId = null,offset = null,limit = null;

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
        Mockito.when(mNewsAPI.getNews(anyInt(), anyInt(), anyInt())).thenReturn(response);
        Observable<Response<String>> news = mNewsAPI.getNews(nodeId, offset, limit);
        assertNotNull(news);
        mNewsRepository.getNewsList(nodeId, offset, limit);
        Mockito.verify(mNewsAPI).getNews(nodeId, offset, limit);
    }
}
