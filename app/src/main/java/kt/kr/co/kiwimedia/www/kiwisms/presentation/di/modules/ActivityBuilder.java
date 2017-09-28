package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.fcm.FcmActivityModule;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.fcm.FcmServiceModule;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.login.LoginActivityModule;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.smsdetails.SMSDetailsFragmentProvider;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.smslist.SMSListFragmentProvider;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm.FcmActivity;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm.FirebaseInstanceIDService;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm.FirebaseMessagingService;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.login.LoginActivity;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail.SMSDetailsActivity;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist.SMSListActivity;

/**
 * Created by h02 on 2017. 8. 28..
 * 아래와 같이 해야 LoginActivity에 Inject될것이 있다는 것을 Dagger Graph에 포함할수 있다.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {FcmActivityModule.class})
    abstract FcmActivity bindFcmActivity();

    @ContributesAndroidInjector(modules = {SMSListFragmentProvider.class})
    abstract SMSListActivity bindSMSListActivity();

    @ContributesAndroidInjector(modules = {SMSDetailsFragmentProvider.class})
    abstract SMSDetailsActivity bindSMSDetailsActivity();

    //fcm 서비스 모듈에서 사용하는것을 기술함
    @ContributesAndroidInjector(modules = {FcmServiceModule.class})
    abstract FirebaseInstanceIDService bindFirebaseInstanceIDService();

    @ContributesAndroidInjector(modules = {FcmServiceModule.class})
    abstract FirebaseMessagingService bindFirebaseMessagingService();

}
