package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by andy on 2017. 9. 12..
 */

public class SMSDetailsFragmentPresenter
        implements SMSDetailsFragmentContract.Presenter{


    private final SMSDetailsFragmentContract.View mSMSDetailsFragment;

    @Inject
    public SMSDetailsFragmentPresenter(SMSDetailsFragmentContract.View mSMSDetailsFragment) {
        this.mSMSDetailsFragment = mSMSDetailsFragment;

        Log.d("injectioninjection","SMSDetailsFragmentPresenter constructor");
    }

    @Override
    public void loadSMSDetails(){
        mSMSDetailsFragment.showData();
    }


}
