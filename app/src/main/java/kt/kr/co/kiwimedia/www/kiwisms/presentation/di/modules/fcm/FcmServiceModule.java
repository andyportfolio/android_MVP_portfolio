package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.fcm;

import dagger.Module;
import dagger.Provides;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.UpdateSMSSendResult;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.UpdateToken;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;

/**
 * Created by h02 on 2017. 9. 1..
 */
@Module
public class FcmServiceModule {

    //fcm 서비스를 위해서 필요한 클래스를 기술 (서비스에서 생성할것임으로 scope이 다름 => singleton으로 할수 없음)

    @Provides
    //@Singleton
    UpdateToken provideUpdateToken(SMSRepository userRepository){
        return new UpdateToken(userRepository);
    }

    @Provides
    //@Singleton
    UpdateSMSSendResult provideUpdateSMSSendResult(SMSRepository userRepository){
        return new UpdateSMSSendResult(userRepository);
    }


}
