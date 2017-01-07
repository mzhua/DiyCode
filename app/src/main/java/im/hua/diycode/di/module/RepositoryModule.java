package im.hua.diycode.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import im.hua.diycode.data.repository.INewsRepository;
import im.hua.diycode.data.repository.ITopicsRepository;
import im.hua.diycode.data.repository.impl.NewsRepository;
import im.hua.diycode.data.repository.impl.TopicsRepository;

/**
 * Created by hua on 2016/10/12.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    ITopicsRepository provideTopicsRepository(TopicsRepository topicsRepository) {
        return topicsRepository;
    }

    @Provides
    @Singleton
    INewsRepository provideNewsRepository(NewsRepository repository) {
        return repository;
    }
}
