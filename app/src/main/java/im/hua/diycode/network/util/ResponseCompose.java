package im.hua.diycode.network.util;

import android.support.annotation.Nullable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hua on 2016/12/9.
 */

public class ResponseCompose {

    public interface Converter<R> {
        R convert(String value);
    }

    public static <R> Observable.Transformer<String, R> handleResponse(@Nullable final Converter<R> converter) {
        return new Observable.Transformer<String, R>() {
            @Override
            public Observable<R> call(Observable<String> tObservable) {
                return tObservable.flatMap(new Func1<String, Observable<R>>() {
                    @Override
                    public Observable<R> call(String t) {
                        return createData(t, converter);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <R> Observable<R> createData(final String data, final Converter<R> converter) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    subscriber.onNext(converter.convert(data));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
