package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.fcm;

import dagger.Module;
import dagger.Provides;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.UpdateToken;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.mapper.ViewModelDataMapper;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm.FcmActivity;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm.FcmContract;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm.FcmPresenter;

/**
 * Created by andy on 2017. 9. 9..
 */
@Module
public class FcmActivityModule {

    //activity 나 fragment는 AndroidInjector 에 의해서 제공받으므로 LoginActivity를 제공하는 @Provides는 별도로
    //필요가 없다.
    @Provides
    FcmContract.View prvoideFcmView(FcmActivity fcmActivity) {
        return fcmActivity;
    }

    @Provides
    UpdateToken provoideUpdateToken(SMSRepository smsRepository) {
        return new UpdateToken(smsRepository);
    }

    //ViewModelDataMapper 는 ApplicationModule에 의해서 생성되었다
    @Provides
    FcmContract.Presenter providePresenter(FcmContract.View view,
                                           UpdateToken updateToken,
                                           ViewModelDataMapper userModelDataMapper){
        return new FcmPresenter(view,updateToken, userModelDataMapper);
    }



}
