package im.hua.diycode.ui.login;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import im.hua.diycode.R;
import im.hua.diycode.di.component.ApplicationComponent;
import im.hua.diycode.di.component.DaggerLoginComponent;
import im.hua.diycode.network.api.AuthAPI;
import im.hua.diycode.network.entity.TokenEntity;
import im.hua.diycode.network.util.ResponseCompose;
import im.hua.diycode.util.GsonConverterUtil;
import im.hua.mvp.framework.BaseActivity;
import retrofit2.Retrofit;
import rx.Subscriber;

import static im.hua.diycode.R.id.toolbar;

public class LoginActivity extends BaseActivity {

    @Inject
    Retrofit mRetrofit;

    AuthAPI mAuthAPI;

    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(R.id.login_account)
    AutoCompleteTextView mLoginAccount;
    @BindView(R.id.login_pwd)
    EditText mLoginPwd;
    @BindView(R.id.login_btn)
    Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLoginComponent.builder()
                .applicationComponent((ApplicationComponent) getApplicationComponent())
                .build().inject(this);

        mAuthAPI = mRetrofit.create(AuthAPI.class);

        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishWithAnimation();
            }
        });

        Pattern emailAddress = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(this).getAccounts();
        List<String> emails = new ArrayList<>();
        for (Account account : accounts) {
            if (emailAddress.matcher(account.name).matches()) {
                mLoginAccount.setText(account.name);
                emails.add(account.name);
            }
        }
        ArrayAdapter<String> emailAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emails);
        mLoginAccount.setAdapter(emailAdapter);
    }

    @OnClick(R.id.login_btn)
    public void login(View view) {
        String account = mLoginAccount.getText().toString();
        if (TextUtils.isEmpty(account)) {
            mLoginAccount.setError("请输入Email / 用户名");
            return;
        }

        String pwd = mLoginPwd.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            mLoginPwd.setError("请输入密码");
            return;
        }

        this.mAuthAPI.getToken("a27e72bd", "aafa838eeede101e5e2b5862c2087da6a403df1cd485ed0b7b6351adb96b4389", "password", account, pwd)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<TokenEntity>() {
                    @Override
                    public TokenEntity convert(String value) {
                        return GsonConverterUtil.jsonObjectParse(TokenEntity.class, value);
                    }
                }))
                .subscribe(new Subscriber<TokenEntity>() {
                    @Override
                    public void onCompleted() {
                        finishWithAnimation();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(TokenEntity tokenEntity) {
                        Toast.makeText(LoginActivity.this, tokenEntity.getAccess_token(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void finishWithAnimation() {
        finish();
        overridePendingTransition(0, R.anim.slide_out_bottom);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishWithAnimation();
    }
}
