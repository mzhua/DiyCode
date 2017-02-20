package im.hua.diycode.data.test.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import im.hua.diycode.data.api.NewsAPI;
import im.hua.diycode.data.repository.INewsRepository;
import im.hua.diycode.data.repository.impl.NewsRepository;

import static org.junit.Assert.assertNotNull;

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
    public void testGetNewsList() {
        assertNotNull(mNewsRepository);
    }
}
