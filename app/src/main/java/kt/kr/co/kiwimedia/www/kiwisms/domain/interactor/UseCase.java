package kt.kr.co.kiwimedia.www.kiwisms.domain.interactor;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by h02 on 2017. 8. 29..
 */
public abstract class UseCase<T,Params> {
    private final CompositeDisposable disposables;

    public UseCase(){
        this.disposables = new CompositeDisposable();
    }
    /**
     * 자식에서 구현
    */
    abstract Observable<T> buildUseCaseOvservable(Params params);


    public void execute(DisposableObserver<T> observer, Params params){
        Preconditions.checkNotNull(observer,"observer is null at UseCase execute");

        Observable<T> observable = this.buildUseCaseOvservable(params)
                .subscribeOn(Schedulers.io())                   //network IO 처리를 위한 쓰레드(데이터 주입
                .observeOn(AndroidSchedulers.mainThread());     //observer에게 알리는 스케쥴러 (데이터 처리)

        addDisposable(observable.subscribeWith(observer));
    }

    //clear
    public void dispose(){
        if (!disposables.isDisposed()){
            disposables.dispose();
        }
    }

    //향후 clear를 하기위해서 dispose 에 저장
    private void addDisposable(Disposable disposable){
        Preconditions.checkNotNull(disposable,"disposable is null at Usecase addDisposable");
        Preconditions.checkNotNull(disposables,"disposables(member variable) is null at Usecase addDisposable");
        disposables.add(disposable);
    }



}
