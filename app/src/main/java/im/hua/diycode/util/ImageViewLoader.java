package im.hua.diycode.util;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static im.hua.diycode.util.ImageViewLoader.Shape.CIRCLE;
import static im.hua.diycode.util.ImageViewLoader.Shape.DEFAULT;

/**
 * Created by hua on 2016/12/12.
 */

public class ImageViewLoader {
    public static int NO_PLACE_HOLDER = -999;


    @IntDef({DEFAULT, CIRCLE})
    @Retention(RetentionPolicy.CLASS)
    public @interface Shape {
        int DEFAULT = 0;
        int CIRCLE = 1;
    }

    public static void loadUrl(Context context, String imageUrl, ImageView target, int resId, @Shape int shape) {
        if (null == context || null == target || TextUtils.isEmpty(imageUrl)) {
            return;
        }
        DrawableTypeRequest<String> request = Glide.with(context)
                .load(imageUrl);
        if (resId == NO_PLACE_HOLDER) {
            request.into(target);
        } else {
            request.placeholder(resId)
                    .into(target);
        }

    }
}
