package im.hua.diycode.util;

import javax.inject.Inject;

import im.hua.diycode.network.entity.TokenEntity;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by hua on 2016/12/15.
 */

public class AuthUtil {

    private Realm mRealm;

    @Inject
    public AuthUtil(Realm realm) {
        mRealm = realm;
    }

    public String getAccessToken() {
        RealmResults<TokenEntity> all = mRealm.where(TokenEntity.class).findAll();
        if (all.size() <= 0) {
            return "";
        }
        TokenEntity tokenEntity = all.first();
        return null == tokenEntity ? "" : tokenEntity.getAccess_token();
    }
}
