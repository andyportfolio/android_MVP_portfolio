package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.SMSModel;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.BaseActivity;

/**
 * Created by h02 on 2017. 8. 31..
 */

public class SMSListActivity extends BaseActivity implements HasSupportFragmentInjector,
        SMSListFragment.SMSListListner{
    public static final String TAG ="DenpendenyInjection2";

    public static Intent getCallingIntent(Context context){
        return new Intent(context,SMSListActivity.class);
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this); //inject

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_list);

        if (savedInstanceState == null){
            addFragment(R.id.smslist_fragmentContainer,SMSListFragment.newInstance());
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onSMSClicked(SMSModel smsModel) {
        this.gNavigator.navigateToSmsDetails(this, smsModel);
    }
}
