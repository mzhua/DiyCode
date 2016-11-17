package im.hua.diycode.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import im.hua.diycode.network.api.AuthAPI;
import im.hua.diycode.network.api.NewsAPI;
import im.hua.diycode.network.api.SitesAPI;
import im.hua.diycode.network.api.TopicAPI;
import im.hua.diycode.network.api.UserAPI;
import retrofit2.Retrofit;

/**
 * Created by hua on 2016/10/12.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    UserAPI provideUserAPI(Retrofit retrofit) {
        return retrofit.create(UserAPI.class);
    }

    @Provides
    @Singleton
    AuthAPI provideAuthAPI(Retrofit retrofit) {
        return retrofit.create(AuthAPI.class);
    }

    @Provides
    @Singleton
    TopicAPI provideTopicAPI(Retrofit retrofit) {
        return retrofit.create(TopicAPI.class);
    }

    @Provides
    @Singleton
    NewsAPI provideNewsAPI(Retrofit retrofit) {
        return retrofit.create(NewsAPI.class);
    }

    @Provides
    @Singleton
    SitesAPI provideSitesAPI(Retrofit retrofit) {
        return retrofit.create(SitesAPI.class);
    }
}
