package im.hua.diycode.network.util;

import android.support.annotation.NonNull;

import im.hua.diycode.network.MyException;
import retrofit2.Response;
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

    public static <R> Observable.Transformer<Response<String>, R> handleResponse(@NonNull final Converter<R> converter) {
        return new Observable.Transformer<Response<String>, R>() {
            @Override
            public Observable<R> call(Observable<Response<String>> tObservable) {
                return tObservable.flatMap(new Func1<Response<String>, Observable<R>>() {
                    @Override
                    public Observable<R> call(Response<String> t) {
                        return createData(t, converter);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <R> Observable<R> createData(final Response<String> response,final Converter<R> converter) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(Subscriber<? super R> subscriber) {
                try {
                    MyException exception = null;
                    switch (response.code()){
                        case 400:
                            exception = new MyException(response.code(),"参数不符合 API 的要求、或者数据格式验证没有通过");
                            break;
                        case 401:
                            exception = new MyException(response.code(),"用户认证失败，或缺少认证信息");
                            break;
                        case 403:
                            exception = new MyException(response.code(),"当前用户对资源没有操作权限");
                            break;
                        case 404:
                            exception = new MyException(response.code(),"服务器挂啦！！！");
                            break;
                        case 500:
                            exception = new MyException(response.code(),"服务器异常");
                            break;
                    }

                    if (null != exception) {
                        subscriber.onError(exception);
                    } else {
                        if (converter == null) {
                            subscriber.onError(new Throwable("the Converter can not be null"));
                        } else {
                            subscriber.onNext(converter.convert(response.body()));
                            subscriber.onCompleted();
                        }
                    }

                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
