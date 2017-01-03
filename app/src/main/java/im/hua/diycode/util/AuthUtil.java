package im.hua.diycode.util;

import android.util.Log;

import javax.inject.Inject;

import im.hua.diycode.network.entity.TokenEntity;
import im.hua.diycode.network.entity.UserEntity;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by hua on 2016/12/15.
 */

public class AuthUtil {

    private Realm mRealm;

    @Inject
    public AuthUtil(Realm realm) {
        mRealm = realm;
    }

    /**
     * 是否登录
     * @return
     */
    public boolean hasLogin() {
        RealmResults<UserEntity> all = mRealm.where(UserEntity.class).findAll();
        return all.size() > 0;
    }

    public String getAccessToken() {
        RealmResults<TokenEntity> all = mRealm.where(TokenEntity.class).findAll();
        if (all.size() <= 0) {
            return "";
        }
        TokenEntity tokenEntity = all.first();
        return null == tokenEntity ? "" : tokenEntity.getAccess_token();
    }

    /**
     * 获取用户信息
     *
     * @param subscriber
     * @return
     */
    public void getUserInfo(Subscriber<UserEntity> subscriber) {
        mRealm.where(UserEntity.class)
                .findAllAsync()
                .asObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<RealmResults<UserEntity>, Observable<UserEntity>>() {
                    @Override
                    public Observable<UserEntity> call(RealmResults<UserEntity> userEntities) {
                        return Observable.from(userEntities);
                    }
                })
                .first()
                .filter(new Func1<UserEntity, Boolean>() {
                    @Override
                    public Boolean call(UserEntity userEntity) {
                        return userEntity != null;
                    }
                })
                .subscribe(subscriber);
    }

    public void saveUserInfo(UserEntity userEntity) {
        try {
            mRealm.beginTransaction();
            mRealm.copyToRealm(userEntity);
        } catch (IllegalArgumentException e) {
            Log.d("LoginActivity", e.getMessage());
        } finally {
            mRealm.commitTransaction();
        }
    }

    public void saveToken(TokenEntity tokenEntity) {
        try {
            mRealm.beginTransaction();
            mRealm.copyToRealm(tokenEntity);
        } catch (IllegalArgumentException e) {
            Log.e("LoginActivity", e.getMessage());
        } finally {
            mRealm.commitTransaction();
        }
    }
}
