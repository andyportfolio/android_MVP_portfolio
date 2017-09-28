package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import kt.kr.co.kiwimedia.www.kiwisms.domain.SMS;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.DefaultObserver;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.GetSMSList;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.DefaultErrorBundle;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.ErrorBundle;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.ErrorMessageFactory;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.SMSModel;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.mapper.ViewModelDataMapper;

/**
 * Created by h02 on 2017. 8. 31..
 */

public class SMSListFragmentPresenter implements SMSListFragmentContract.Presenter {
    public static final String TAG = "DenpendenyInjection2";

    private SMSListFragmentContract.View mSMSListFragment;
    private final GetSMSList mGetSMSListUsecase;
    private final ViewModelDataMapper mViewModelDataMapper;

    private SharedPreferences mSharedPreferences;

    @Inject
    public SMSListFragmentPresenter(SMSListFragmentContract.View smsListFragment,
                                    GetSMSList getSMSListUsecase,
                                    ViewModelDataMapper viewModelDataMapper) {

        this.mSMSListFragment = smsListFragment;
        this.mGetSMSListUsecase = getSMSListUsecase;
        this.mViewModelDataMapper = viewModelDataMapper;
        Log.d(TAG,"SMSListFragmentPresenter construct()");
        Log.d("injectioninjection","SMSListFragmentPresenter constructor");
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mGetSMSListUsecase.dispose();
        mSMSListFragment = null;
    }


    @Override
    public void onSMSClicked(SMSModel smsModel){
        mSMSListFragment.viewSMS(smsModel);
    }

    @Override
    public void initialize() {
        loadSMSList();
    }




    public void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(mSMSListFragment.context(),
                errorBundle.getException());
        mSMSListFragment.showError(errorMessage);
    }

    private void loadSMSList(){
        hideViewRetry();
        showViewLoading();
        getSMSList();
    }

    private void showViewLoading() {
        mSMSListFragment.showLoading();
    }

    private void hideViewLoading() {
        mSMSListFragment.hideLoading();
    }

    private void hideViewRetry() {

        mSMSListFragment.hideRetry();
    }

    private void showViewRetry() {

        mSMSListFragment.showRetry();
    }

    public void getSMSList() {

        mSharedPreferences  = PreferenceManager.getDefaultSharedPreferences(mSMSListFragment.context());
        String userid    = mSharedPreferences.getString("userid", "");

        mGetSMSListUsecase.execute(new SMSListObserver(), GetSMSList.Params.forGetSMSList(userid));
    }

    private void showSMScollectionInView(Collection<SMS> smsCollection) {
        Collection<SMSModel> smsModelCollection = mViewModelDataMapper.transform(smsCollection);
        mSMSListFragment.renderSMSList(smsModelCollection);

    }

    /**
     * SMSList Observer
     */
    private final class SMSListObserver extends DefaultObserver<List<SMS>> {
        @Override
        public void onNext(List<SMS> smses) {
            SMSListFragmentPresenter.this.showSMScollectionInView(smses);
        }

        @Override
        public void onError(Throwable e) {
            SMSListFragmentPresenter.this.hideViewLoading();
            SMSListFragmentPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            SMSListFragmentPresenter.this.showViewRetry();
        }

        @Override
        public void onComplete() {
            SMSListFragmentPresenter.this.hideViewLoading();
        }


    }



}
