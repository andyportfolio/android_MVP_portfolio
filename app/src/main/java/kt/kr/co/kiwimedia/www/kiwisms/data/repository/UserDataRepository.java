package kt.kr.co.kiwimedia.www.kiwisms.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.mapper.EntityDataMapper;
import kt.kr.co.kiwimedia.www.kiwisms.data.repository.datasource.CloudUserDataStore;
import kt.kr.co.kiwimedia.www.kiwisms.domain.SMS;
import kt.kr.co.kiwimedia.www.kiwisms.domain.User;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;

/**
 * Created by h02 on 2017. 8. 29..
 */

@Singleton
public class UserDataRepository implements SMSRepository {

    private  CloudUserDataStore mCloudUserDataStore;
    private EntityDataMapper mEntityDataMapper;


    @Inject
    public UserDataRepository(CloudUserDataStore cloudUserDataStore,
                       EntityDataMapper entityDataMapper) {
        CustomLog.d("DependencyInjection","UserDataRepository Construct() : " + this.toString());
        mCloudUserDataStore = cloudUserDataStore;
        mEntityDataMapper = entityDataMapper;
    }

    @Override
    public Observable<User> doLogin(String userid, String password) {
        return mCloudUserDataStore.doLogin(userid,password).map(this.mEntityDataMapper::transform);
    }

    @Override
    public Observable<List<SMS>> getSMSList(String id) {

        return mCloudUserDataStore.getSMSList(id).map(this.mEntityDataMapper::transform);
    }


//    public Observable<List<SMS>> getSMSList() {
//        return mCloudUserDataStore.getSMSList().map(this.mEntityDataMapper::transform);
//    }

    @Override
    public Observable<SMS> getSMSDetail() {
        return null;
    }

    @Override
    public Observable<User> updateToken(String id, String token) {
        return mCloudUserDataStore.updateToken(id,token).map(this.mEntityDataMapper::transform);
    }

    @Override
    public Observable<SMS> updateSMSSendResult(String num, String send_datetime, String from) {
        return mCloudUserDataStore.updateSMSSendResult(num,send_datetime,from).map(this.mEntityDataMapper::transform);
    }
}
