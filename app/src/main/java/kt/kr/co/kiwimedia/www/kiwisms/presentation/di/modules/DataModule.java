package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.mapper.EntityDataMapper;
import kt.kr.co.kiwimedia.www.kiwisms.data.repository.UserDataRepository;
import kt.kr.co.kiwimedia.www.kiwisms.data.repository.datasource.CloudUserDataStore;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;

/**
 * Created by h02 on 2017. 9. 1..
 */
@Module
public class DataModule {
    @Provides
    @Singleton
    CloudUserDataStore provideCloudUserDataStore(Context context){
        return new CloudUserDataStore(context);
    }

    @Provides
    @Singleton
    EntityDataMapper provideEntityDataMapper(){
        return new EntityDataMapper();
    }

    @Provides
    @Singleton
    SMSRepository provideSMSRepository(CloudUserDataStore cloudUserDataStore,
                                        EntityDataMapper entityDataMapper){
        return new UserDataRepository(cloudUserDataStore,entityDataMapper);
    }


}
