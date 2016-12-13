package im.hua.mvp.framework;

import android.app.Application;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.widget.Toast;

import im.hua.mvp.framework.di.FApplicationComponent;

/**
 * Created by hua on 16/10/11.
 */

public class BaseFragment extends Fragment {
    private ProgressDialog mDialog;

    public interface OnDialogDismissListener {
        void onDismiss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    public void showShortToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(@Nullable String title, @NonNull String message, @Nullable final BaseActivity.OnDialogDismissListener listener) {
        if (null == mDialog) {
            mDialog = new ProgressDialog(getActivity());
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

    public void setRefresh(final boolean refreshing, final SwipeRefreshLayout refreshLayout){
        if (null == refreshLayout) {
            return;
        }
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link FApplicationComponent}
     */
    protected FApplicationComponent getApplicationComponent() {
        Application application = getActivity().getApplication();
        if (!(application instanceof FApplication)) {
            throw new IllegalStateException("Application 一定要继承 FApplication");
        }
        return ((FApplication) application).getApplicationComponent();
    }
}
