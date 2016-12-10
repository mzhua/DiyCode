package im.hua.mvp.framework;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by hua on 16/10/12.
 */

public interface IMVPView {
    Context context();

    void showLoadingView(String message);

    void hideLoadingView(String message);

    void showErrorMessage(@NonNull String message);
}
