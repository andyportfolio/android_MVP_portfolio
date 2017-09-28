package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail;

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
 * Created by andy on 2017. 9. 12..
 */

public class SMSDetailsActivity extends BaseActivity implements HasSupportFragmentInjector{
    public static final String EXTRA_SMSMODEL = "smsmodel";

    public static Intent getCallingIntent(Context packageContext, SMSModel smsModel){
        Intent intent = new Intent(packageContext,SMSDetailsActivity.class);
        intent.putExtra(EXTRA_SMSMODEL, smsModel);
        return intent;
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this); //inject

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_details);

        Intent intent = getIntent();
        SMSModel smsModel = intent.getParcelableExtra(EXTRA_SMSMODEL);


        if (savedInstanceState == null){
            addFragment(R.id.smsdetail_fragmentContainer,SMSDetailsFragment.newInstance(smsModel));
        }

//        Intent intent = getIntent();
//        SMSModel smsModel = intent.getParcelableExtra(EXTRA_SMSMODEL);
//        Toast.makeText(this, "smsModel : " + smsModel.getId(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
