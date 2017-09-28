package kt.kr.co.kiwimedia.www.kiwisms.data.repository.datasource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.SMSEntity;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.SMSListEntity;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.UserEntity;
import kt.kr.co.kiwimedia.www.kiwisms.data.exception.NetworkConnectionException;
import kt.kr.co.kiwimedia.www.kiwisms.data.net.NetApi;
import kt.kr.co.kiwimedia.www.kiwisms.data.net.NetApiService;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;
import retrofit2.Retrofit;

/**
 * Created by h02 on 2017. 8. 29..
 */

@Singleton
public class CloudUserDataStore {
    private static final String TAG = "CloudUserDataStore";
    public static final boolean TEST_FLAG = true;
    private final Context context;

    private Retrofit mRetrofit;
    private NetApi mNetApi;

    @Inject
    public CloudUserDataStore(Context context) {
        CustomLog.d("DependencyInjection","CloudUserDataStore Construct() : " + this.toString());
        if (context == null) {
            throw new IllegalArgumentException("The constructor parameters(context) cannot be null!!!");
        }
        this.context = context;
        this.mRetrofit = NetApiService.createRestrofit();
        this.mNetApi = this.mRetrofit.create(NetApi.class);

    }


    public Observable<UserEntity> doLogin(String username, String password) {
//        Retrofit retrofit = NetApiService.createRestrofit();
//        NetApi netApi = retrofit.create(NetApi.class);
        Observable<UserEntity> observable = null;

        if (isThereInternetConnection()) {
            observable = mNetApi.doLogin(username, password);

        }else{
            //https://brunch.co.kr/@lonnie/18
            //RxJava, Observable 생성하기
            observable = Observable.error(new NetworkConnectionException());
        }

        return observable;
    }

    public Observable<SMSListEntity> getSMSList(String id){
//        Retrofit retrofit = NetApiService.createRestrofit();
//        NetApi netApi = retrofit.create(NetApi.class);
        Observable<SMSListEntity> observable = null;

        if (isThereInternetConnection()) {
            observable = mNetApi.getSMSList(id);

//            observable.subscribeOn(Schedulers.computation())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                    new Observer<SMSListEntity>() {
//                        @Override
//                        public void onSubscribe(@NonNull Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(@NonNull SMSListEntity smsListEntity) {
//                            List<SMSEntity> items = smsListEntity.items;
//                            for(SMSEntity smsEntity : items){
//                                Log.d("LOG", "onNext====> " + smsEntity.toString());
//                            }
//                        }
//
//                        @Override
//                        public void onError(@NonNull Throwable e) {
//                            Log.d("LOG", "OnError====> " + e.toString());
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    }
//
//            );

        }else{
            observable = Observable.error(new NetworkConnectionException());
        }
        return observable;
    }

//    public Observable<List<SMSEntity>> getSMSList(){
//        Retrofit retrofit = NetApiService.createRestrofit();
//        NetApi netApi = retrofit.create(NetApi.class);
//        Observable<List<SMSEntity>> observable = null;
//
//        if (isThereInternetConnection()) {
//            observable = netApi.getSMSList();
//
//            observable.subscribeOn(Schedulers.computation())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            new Observer<List<SMSEntity>>() {
//                                @Override
//                                public void onSubscribe(@NonNull Disposable d) {
//
//                                }
//
//                                @Override
//                                public void onNext(@NonNull List<SMSEntity> smsEntities) {
//                                    for(SMSEntity s : smsEntities){
//                                        Log.d("LOG", "onNext" + s.toString());
//                                    }
//                                }
//
//                                @Override
//                                public void onError(@NonNull Throwable e) {
//                                    Log.d("LOG", "OnError" + e.toString());
//                                }
//
//                                @Override
//                                public void onComplete() {
//
//                                }
//                            }
//
//                    );
//
//        }else{
//            new NetworkConnectionException();
//        }
//        return observable;
//    }


    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    public Observable<UserEntity> updateToken(String id, String token) {
//        Retrofit retrofit = NetApiService.createRestrofit();
//        NetApi netApi = retrofit.create(NetApi.class);
        Observable<UserEntity> observable = null;

        if (isThereInternetConnection()) {
            observable = mNetApi.updateToken(id, token);

        }else{
            observable = Observable.error(new NetworkConnectionException());
        }
        return observable;
    }

    public Observable<SMSEntity> updateSMSSendResult(String num, String send_datetime, String from) {
//        Retrofit retrofit = NetApiService.createRestrofit();
//        NetApi netApi = retrofit.create(NetApi.class);
        Observable<SMSEntity> observable = null;

        if (isThereInternetConnection()) {
            observable = mNetApi.updateSMSSendResult(num,send_datetime,from);

        }else{
            observable = Observable.error(new NetworkConnectionException());
        }
        return observable;

    }
}
