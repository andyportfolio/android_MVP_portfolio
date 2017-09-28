package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.databinding.ActivityFcmBinding;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.BaseActivity;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;

public class FcmActivity extends BaseActivity implements FcmContract.View{

    public static Intent getCallingIntent(Context context){
        return new Intent(context,FcmActivity.class);
    }

    //binding의 이름은 layout의 이름에 종속된다 ==> R.layout.activity_fcm
    private ActivityFcmBinding binding;

    @Inject
    FcmContract.Presenter mPresenter;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this); //Inject

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fcm);


        //token을 받아온다
        //elB5WwMnF1Y:APA91bFqWQ79fscgm0umrSec-AEBFtFfCw3gkMP-pKOtQyq5_dyS9m8h8pDoqkiFhKQ4-mRzH3f2820M0VHiIBwfL7sqYDb29wBB9ekT9ze9gepXddor6JIXSHKviBeIWsbEHhMteyFs
        String token = FirebaseInstanceId.getInstance().getToken();

        if (token != null) {
            mPresenter.updateToken(token);
            CustomLog.d("FCM_Token", token);
        }else{
            CustomLog.d("FCM_Token", "token is null");
        }

    }

    @Override
    public void navigateToSMSListActivity() {
        gNavigator.navigateToSmsList(this);
    }

    @Override
    public void showUpdateTokenFail() {
        showMessage(getString(R.string.error_update_token));
    }

    @Override
    public void showTokenError(String message) {
        showMessage(message);
    }

    @Override
    public Context context() {
        return super.context();
    }

    private void showMessage(String message) {
        showSnackbarMessage(binding.fcmLinearlayout,message);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
