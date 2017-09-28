package kt.kr.co.kiwimedia.www.kiwisms.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by h02 on 2017. 8. 29..
 */

public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {
        // no-op by default.
    }

    @Override
    public void onError(Throwable e) {
        // no-op by default.
    }

    @Override
    public void onComplete() {
        // no-op by default.
    }
}
