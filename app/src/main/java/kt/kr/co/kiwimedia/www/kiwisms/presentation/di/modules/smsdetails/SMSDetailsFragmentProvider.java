package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.smsdetails;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail.SMSDetailsFragment;

/**
 * Created by h02 on 2017. 8. 31..
 */

@Module
public abstract class SMSDetailsFragmentProvider {

    @ContributesAndroidInjector(modules = SMSDetailsFragmentModule.class)
    abstract SMSDetailsFragment provideSMSDetailsFragmentFactory();
}
