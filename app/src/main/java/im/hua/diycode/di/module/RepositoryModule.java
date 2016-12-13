package im.hua.diycode.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import im.hua.diycode.data.remote.repository.ITopicsRepository;
import im.hua.diycode.data.remote.repository.impl.TopicsRepository;

/**
 * Created by hua on 2016/10/12.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ITopicsRepository provideTopicsRepository(TopicsRepository topicsRepository) {
        return topicsRepository;
    }
}
