package im.hua.diycode.data.test.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import im.hua.diycode.data.util.AuthUtil;
import io.realm.Realm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by hua on 2017/2/21.
 */

public class AuthUtilTest {
    private AuthUtil mAuthUtil;

    @Mock
    Realm mRealm;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mAuthUtil = new AuthUtil(Realm.getDefaultInstance());
    }

    @Test
    public void testHasLogin_returnFalse() {
        assertThat(mAuthUtil.hasLogin(),is(false));
    }
}
