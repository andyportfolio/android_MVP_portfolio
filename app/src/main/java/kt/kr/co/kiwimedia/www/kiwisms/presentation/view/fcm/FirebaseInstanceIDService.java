package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import kt.kr.co.kiwimedia.www.kiwisms.domain.User;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.DefaultObserver;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.UpdateToken;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;

/**
 * Created by andy on 2017. 9. 6..
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    private SharedPreferences mSharedPreferences;

    @Inject
    UpdateToken mUpdateTokenUsecase;


    @Override
    public void onCreate() {
        AndroidInjection.inject(this); //Inject
        super.onCreate();
    }

    /**
     * https://firebase.google.com/docs/cloud-messaging/android/client?hl=ko
     * 다음과 같은 경우에 등록 토큰이 변경될 수 있습니다.
     1. 앱에서 인스턴스 ID 삭제
     2. 새 기기에서 앱 복원
     3. 사용자가 앱 삭제/재설치
     4. 사용자가 앱 데이터 소거
     */



    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        CustomLog.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.

        //서버에 토큰을 update 시킨다
        //// TODO: 2017. 9. 6.

        CustomLog.d("Token","변경으로 인한 sendRegistrationToServer");

        mSharedPreferences  = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userid      = mSharedPreferences.getString("userid","");

        if (!userid.isEmpty()) {
            this.mUpdateTokenUsecase.execute(new UpdateTokenObserver(), UpdateToken.Params.forUpdateToken(userid, token));
        }else{
            //// TODO: 2017. 9. 9.
            //userID Notfound Exception을 던진다
        }

    }

    private void updateSharedPreference(User user) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        if ("0".equals(user.getResult())) {
            editor.putString("token", user.getToken());
        }else{
            editor.putString("token", "NA");  //serve에 token이 update되지 못함
        }

        editor.apply();
    }


    /**
     * UpdateToken Observer
     */
    private final class UpdateTokenObserver extends DefaultObserver<User> {
        @Override
        public void onNext(User user) {
            updateSharedPreference(user);
        }

        @Override
        public void onError(Throwable e) {
            //
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CustomLog.d(TAG,"onDestroy() => mUpdateTokenUsecase.dispose();");
       // mUpdateTokenUsecase.dispose();
    }
}
