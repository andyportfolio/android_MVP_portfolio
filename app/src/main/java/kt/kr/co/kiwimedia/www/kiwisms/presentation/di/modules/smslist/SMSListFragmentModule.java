package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.smslist;

import dagger.Module;
import dagger.Provides;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.GetSMSList;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.mapper.ViewModelDataMapper;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist.SMSListFragment;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist.SMSListFragmentContract;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist.SMSListFragmentPresenter;

/**
 * Created by h02 on 2017. 8. 31..
 */
@Module
public class SMSListFragmentModule {

    //activity 나 fragment는 AndroidInjector 에 의해서 제공받으므로 SMSListFragment를 제공하는 @Provides는 별도로
    //필요가 없다.
    @Provides
    SMSListFragmentContract.View provideSMSListFragmentView(SMSListFragment smsListFragment){
        return smsListFragment;
    }

    @Provides
    GetSMSList provoideGetSMSList(SMSRepository smsRepository) {
        return new GetSMSList(smsRepository);
    }

    @Provides
    SMSListFragmentContract.Presenter provideSMSListFragmentPresenter(SMSListFragmentContract.View listView,
                                                                      GetSMSList getSMSListUsecase,
                                                                      ViewModelDataMapper viewrModelDataMapper) {
        return new SMSListFragmentPresenter(listView,getSMSListUsecase,viewrModelDataMapper);
    }
}


/*
SMSListFragmentContract.Presenter cannot be provided
without an @Provides- or @Produces-annotated method.
SMSListFragmentContract.Presenter is injected at
SMSListFragment.mSMSListFragmentPresenter
SMSListFragment is injected at dagger.android.AndroidInjector.inject(arg0)
*/