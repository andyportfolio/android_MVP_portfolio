package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.smslist;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist.SMSListFragment;

/**
 * Created by h02 on 2017. 8. 31..
 */

@Module
public abstract class SMSListFragmentProvider {

    @ContributesAndroidInjector(modules = SMSListFragmentModule.class)
    abstract SMSListFragment provideSMSListFragmentFactory();
}
