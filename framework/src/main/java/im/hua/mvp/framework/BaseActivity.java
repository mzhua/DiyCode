package im.hua.mvp.framework;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import im.hua.mvp.framework.di.FApplicationComponent;


/**
 * Created by hua on 16/10/11.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mDialog;

    public interface OnDialogDismissListener {
        void onDismiss();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    public void showProgressDialog(@Nullable String title, @NonNull String message, @Nullable final OnDialogDismissListener listener) {
        if (null == mDialog) {
            mDialog = new ProgressDialog(this);
        }
        if (null != listener) {
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    listener.onDismiss();
                }
            });
        }

        if (!TextUtils.isEmpty(title)) {
            mDialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mDialog.setMessage(message);
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }

    }

    public void dismissProgressDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void showShortToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link FApplicationComponent}
     */
    protected FApplicationComponent getApplicationComponent() {
        Application application = getApplication();
        if (!(application instanceof FApplication)) {
            throw new IllegalStateException("Application 一定要继承 FApplication");
        }
        return ((FApplication) application).getApplicationComponent();
    }
}
