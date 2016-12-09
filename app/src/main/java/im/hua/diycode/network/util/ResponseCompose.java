package im.hua.diycode.network.util;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hua on 2016/12/9.
 */

public class ResponseCompose {
    public static <T, R> Observable.Transformer<T, R> handleResponse(final Converter<T, R> converter) {
        return new Observable.Transformer<T, R>() {
            @Override
            public Observable<R> call(Observable<T> tObservable) {
                return tObservable.flatMap(new Func1<T, Observable<R>>() {
                    @Override
                    public Observable<R> call(T t) {
                        return createData(t, converter);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T, R> Observable<R> createData(final T data, final Converter<T, R> converter) {
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

    public interface Converter<T, R> {
        R convert(T value);
    }
}
