package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.login;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.databinding.ActivityLoginBinding;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.UserModel;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.BaseActivity;


public class LoginActivity extends BaseActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";

    //binding의 이름은 layout의 이름에 종속된다 ==> R.layout.activity_login
    private ActivityLoginBinding binding;


    @Inject
    LoginContract.Presenter mPresenter; //new LoginPresenter(something);

    public static Intent getCallingIntent(Context context){
        return new Intent(context,LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this); //Inject

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        UserModel userModel = new UserModel("jblee27@naver.com", "dlwltn3651");
        binding.setUser(userModel);


        mPresenter.requestSetInitValue();

    }


    public void onLoginBtnClick(View view){
        mPresenter.validateCredentials(binding.etUserId.getText().toString(),
                binding.etUserPassword.getText().toString());
    }



    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestSetInitValue();

    }

    @Override
    public void setInitValue(String userid) {

        if (userid.isEmpty()){
            binding.etUserId.requestFocus();
        }else{
            binding.etUserPassword.requestFocus();
        }
    }


    @Override
    public void showProgress() {
        binding.loginProgress.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        binding.loginProgress.setVisibility(View.GONE);

    }

    @Override
    public void enableButton() {
        binding.btnLogin.setEnabled(true);
    }

    @Override
    public void disableButton() {
        binding.btnLogin.setEnabled(false);
    }

    @Override
    public void setUserIdError() {
        binding.etUserId.setError(getString(R.string.error_invalid_userid));
        binding.etUserId.requestFocus();
    }

    @Override
    public void setUserPasswordError() {
        binding.etUserPassword.setError(getString(R.string.error_invalid_password));
        binding.etUserPassword.requestFocus();
    }

    @Override
    public void navigateToFcmActivity() {
        this.gNavigator.navigateToFcm(this);

    }

    @Override
    public void navigateToSMSListActivity() {
        this.gNavigator.navigateToSmsList(this);
    }


    @Override
    public void showLoginFail() {
        showSnackbarMessage(binding.linearLaoyout,getString(R.string.error_invlaid_login));
    }

    @Override
    public void showLoginError(String message) {
        showSnackbarMessage(binding.linearLaoyout,message);
    }


    @Override
    public Context context() {
        return super.context();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}

/*
1. DI를 사용하지 않을경우
    new LoginPresenter(this);

2. Dagger old style
    component를 만들고 reference를 통하여 repository를 가져오고
    두번째로 실제 presenter를 생성한다
    DaggerLoginComponent.builder()
        .repositoryComponent(((MyApplication)getApplication()).getRepositoryComponent())
        .loginPresenterModule(new LoginPresenterModule(fragment))
        .build()
        .inject(this);
*/