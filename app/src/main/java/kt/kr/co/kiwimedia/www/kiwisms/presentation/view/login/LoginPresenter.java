package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.login;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import kt.kr.co.kiwimedia.www.kiwisms.domain.User;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.DefaultObserver;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.DoLogin;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.DefaultErrorBundle;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.ErrorBundle;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.exception.ErrorMessageFactory;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.mapper.ViewModelDataMapper;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;


/**
 * Created by andy on 2017. 8. 17..
 */


public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG ="LoginPresenter";


    @NonNull
    private  LoginContract.View mLoginView; //interface


    private final DoLogin mDoLoginUseCase;
    private final ViewModelDataMapper mViewModelDataMapper;

    private SharedPreferences mSharedPreferences;


    @Inject
    public LoginPresenter(LoginContract.View loginView ,
                          DoLogin doLoginUsecase,
                          ViewModelDataMapper viewModelDataMapper) {
        CustomLog.d("DependencyInjection","LoginPresenter Construct() : " + this.toString());

        this.mLoginView = loginView;
        this.mDoLoginUseCase = doLoginUsecase;
        this.mViewModelDataMapper = viewModelDataMapper;

    }

    private void setInitValue() {
        //preferece에서 값 가져오기
        //Activity,Service 와 같이 contextwarraper를 상속받은 곳에서는 getApplicationContext가
        //this 를 return하지만 view에서는 해당액티비티.this 또는 getApplicationContext를 쓴다
        mSharedPreferences  = PreferenceManager.getDefaultSharedPreferences(mLoginView.context());

        String userid    = mSharedPreferences.getString("userid", "");
        String loginflag    = mSharedPreferences.getString("loginflag", "");


        //Test 용으로 하위 로직을 막는다
        // Loginfloag가 있으면 login을 다시하지 않고, 다음 페이지로 진행한다
//        if ("Y".equals(loginflag)){
//            navigationDecision();
//        }else{
//            mLoginView.setInitValue(userid);
//            mLoginView.enableButton();
//        }


        //Test용으로  넣어라라
       mLoginView.setInitValue(userid);
        mLoginView.enableButton();

    }


    @Override
    public void validateCredentials(@NonNull String userId, @NonNull String userPassword) {

        mLoginView.disableButton();

        if (userId.isEmpty()){
            mLoginView.setUserIdError();
            mLoginView.enableButton();
        }else if(userPassword.isEmpty()){
            mLoginView.setUserPasswordError();
            mLoginView.enableButton();
        }else {

            mLoginView.showProgress();
            this.mDoLoginUseCase.execute(new LoginObserver(), DoLogin.Params.forLogin(userId,userPassword));

        }

    }



    @Override
    public void requestSetInitValue() {
        setInitValue();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mDoLoginUseCase.dispose(); //메모리 해제
        mLoginView = null;
    }

    private void loginResult(User user){

        if (!user.getId().equals("null")) {
            mLoginView.enableButton();
            setSharedPreferenceValue(user);
            navigationDecision();


        }else {
            mLoginView.showLoginFail();
            mLoginView.enableButton();
        }

    }

    private void navigationDecision() {

        CustomLog.d(TAG,"token : " + mSharedPreferences.getString("token", "NA"));

        //token이 등록되지 않은경우 token등록 activity로 이동
        if ("NA".equals(mSharedPreferences.getString("token", "NA"))) {
            mLoginView.navigateToFcmActivity();
        }else{
            mLoginView.navigateToSMSListActivity();
        }
    }


    private void setSharedPreferenceValue(User user) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("userid",user.getId());
        editor.putString("username", user.getUsername());
        editor.putString("company", user.getCompany());
        editor.putString("loginflag", "Y"); //Login이 성공됨
        editor.apply();
    }

    private void showErrorMessage(ErrorBundle errorBundle){
        String errorMessage = ErrorMessageFactory.create(this.mLoginView.context(),
                errorBundle.getException());

        mLoginView.showLoginError(errorMessage);

        //hideProcess - enableButton
        mLoginView.hideProgress();
        mLoginView.enableButton();

    }


    /**
     * Login Observer
     */
    private final class LoginObserver extends DefaultObserver<User>{
        @Override
        public void onNext(User user) {
            LoginPresenter.this.loginResult(user);
        }

        @Override
        public void onError(Throwable e) {
            LoginPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onComplete() {
            super.onComplete();
            mLoginView.hideProgress();
        }
    }

}
