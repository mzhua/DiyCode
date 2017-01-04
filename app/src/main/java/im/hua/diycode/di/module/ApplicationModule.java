package im.hua.diycode.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import im.hua.diycode.Constants;
import im.hua.diycode.data.util.stringconverter.StringConverterFactory;
import im.hua.diycode.util.AuthUtil;
import im.hua.mvp.framework.BuildConfig;
import im.hua.mvp.framework.FApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by hua on 16/10/12.
 */
@Module
public class ApplicationModule {
    private FApplication mApplication;

    public ApplicationModule(FApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPref() {
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//必须加上
                .client(builder.build())
                .build();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        // The realm file will be located in Context.getFilesDir() with name "default.realm"
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    AuthUtil provideAuthUtil(Realm realm) {
        return new AuthUtil(realm);
    }
}
