package im.hua.diycode.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import im.hua.diycode.ui.topic.ITopicsRepository;
import im.hua.diycode.ui.topic.TopicsRepository;

/**
 * Created by hua on 2016/10/12.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ITopicsRepository provideToppicsRepository(TopicsRepository topicsRepository) {
        return topicsRepository;
    }
}
