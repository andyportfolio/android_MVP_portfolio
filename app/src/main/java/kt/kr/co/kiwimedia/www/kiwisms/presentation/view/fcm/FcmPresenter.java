package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import kt.kr.co.kiwimedia.www.kiwisms.domain.User;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.DefaultObserver;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.UpdateToken;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.DefaultErrorBundle;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.ErrorBundle;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.ErrorMessageFactory;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.mapper.ViewModelDataMapper;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;

/**
 * Created by andy on 2017. 9. 8..
 */

public class FcmPresenter implements FcmContract.Presenter {
    private static final String TAG ="FcmPresenter";


    private  FcmContract.View mFcmView; //interface
    private final UpdateToken mUpdateTokenUsecase;
    private final ViewModelDataMapper mViewModelDataMapper;

    private SharedPreferences mSharedPreferences;
    private String mToken;

    @Inject
    public FcmPresenter(@NonNull FcmContract.View fcmView,
                        UpdateToken updateTokenUsecase,
                        ViewModelDataMapper viewModelDataMapper) {

        this.mFcmView = fcmView;
        this.mUpdateTokenUsecase = updateTokenUsecase;
        this.mViewModelDataMapper = viewModelDataMapper;
    }

    private void showErrorMessage(ErrorBundle errorBundle){
        String errorMessage = ErrorMessageFactory.create(this.mFcmView.context(),
                errorBundle.getException());
        mFcmView.showTokenError(errorMessage);
    }

    @Override
    public void updateToken(String token) {

        CustomLog.d("Token","신규 토큰등록 updateToken");
        this.mToken = token;

        mSharedPreferences  = PreferenceManager.getDefaultSharedPreferences(mFcmView.context());
        String userid      = mSharedPreferences.getString("userid","");

        if (!userid.isEmpty()) {
            this.mUpdateTokenUsecase.execute(new UpdateTokenObserver(), UpdateToken.Params.forUpdateToken(userid, token));
        }else{
            //// TODO: 2017. 9. 9.
            //userID Notfound Exception을 던진다
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mUpdateTokenUsecase.dispose();
        mFcmView = null;

    }

    private void updateTokenResult(User user) {

        //sucess
        if (user.getResult().equals("0")) {
            updateSharedPreference(user);
            mFcmView.navigateToSMSListActivity();

        } else {
            mFcmView.showUpdateTokenFail();
        }

    }

    private void updateSharedPreference(User user) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        if ("0".equals(user.getResult())) {
            editor.putString("token", mToken); //token저장
        }else{
            editor.putString("token", "NA");  //serve에 token이 update되지 못함
        }

        editor.apply();

    }

    /**
     *  UpdateToken Observer
     */
    private final class UpdateTokenObserver extends DefaultObserver<User> {
        @Override
        public void onNext(User user) {
            FcmPresenter.this.updateTokenResult(user);
        }

        @Override
        public void onError(Throwable e) {
            FcmPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
