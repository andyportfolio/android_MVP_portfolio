package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.smsdetails;

import dagger.Module;
import dagger.Provides;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail.SMSDetailsFragment;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail.SMSDetailsFragmentContract;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail.SMSDetailsFragmentPresenter;

/**
 * Created by h02 on 2017. 8. 31..
 */
@Module
public class SMSDetailsFragmentModule {

    //activity 나 fragment는 AndroidInjector 에 의해서 제공받으므로 SMSListFragment를 제공하는 @Provides는 별도로
    //필요가 없다.
    @Provides
    SMSDetailsFragmentContract.View provideSMSDetailsFragmentView(SMSDetailsFragment smsDetailsFragment){
        return smsDetailsFragment;
    }


    @Provides
    SMSDetailsFragmentContract.Presenter provideSMSDetailsFragmentPresenter(SMSDetailsFragmentContract.View detailView) {
        return new SMSDetailsFragmentPresenter(detailView);
    }
}

