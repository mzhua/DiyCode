package im.hua.diycode.util;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;

/**
 * Created by hua on 2016/12/13.
 */

public class DrawTextUtil {
    public static float getTextHeight(String text, Paint paint) {
        if (TextUtils.isEmpty(text) || null == paint) {
            return 0;
        }
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    public static float getTextWidth(String text, Paint paint) {
        if (TextUtils.isEmpty(text) || null == paint) {
            return 0;
        }
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }

    public static float measureTextWidth(String text, Paint paint) {
        if (TextUtils.isEmpty(text) || null == paint) {
            return 0;
        }
        return paint.measureText(text);
    }
}
