package im.hua.diycode.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by hua on 2016/12/10.
 */

public class MessageShowTimeUtil {
    private static SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
    private static SimpleDateFormat ymdhFormat = new SimpleDateFormat("yyyy-MM-dd HH", Locale.CHINESE);
    private static SimpleDateFormat mdFormat = new SimpleDateFormat("MM-dd", Locale.CHINESE);
    private static SimpleDateFormat hmFormat = new SimpleDateFormat("HH:mm", Locale.CHINESE);
    private static SimpleDateFormat mFormat = new SimpleDateFormat("mm", Locale.CHINESE);

    public static String getFormatTime(Date date) {
        if (isTheSameDay(date)) {
            if (isTheSameHour(date)) {
                return getIntervalTime(date,Calendar.getInstance().getTime(),TimeUnit.MINUTES) + "分钟前";
            } else {
                return hmFormat.format(date);
            }
        } else {
            return mdFormat.format(date);
        }
    }

    private static boolean isTheSameDay(Date oldDate) {
        String old = ymdFormat.format(oldDate);
        String current = ymdFormat.format(Calendar.getInstance().getTime());

        return old.equals(current);
    }

    private static boolean isTheSameHour(Date oldDate) {
        String old = ymdhFormat.format(oldDate);
        String current = ymdhFormat.format(Calendar.getInstance().getTime());

        return old.equals(current);
    }

    private static long getIntervalTime(Date date0, Date date1, TimeUnit timeUnit) {
        if (timeUnit == TimeUnit.MINUTES) {
            return Math.abs(Integer.parseInt(mFormat.format(date0)) - Integer.parseInt(mFormat.format(date1)));
        }
        return 0;
    }
}
