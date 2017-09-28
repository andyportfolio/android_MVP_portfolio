package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm;

import android.content.Context;

/**
 * Created by andy on 2017. 9. 8..
 */

public interface FcmContract {

    interface View{
        void navigateToSMSListActivity();
        void showUpdateTokenFail();

        void showTokenError(String message);

        Context context(); //공통의 성격으로 사용 ==> 나중에 BaseView 나 BasePresenter를 만들때 추가하라
    }

    interface Presenter{
        //Token update
        void updateToken(String token);

        /**
         * Method that control the lifecycle of the view. It should be called in the view's
         * (Activity or Fragment) onResume() method.
         */
        void resume();

        /**
         * Method that control the lifecycle of the view. It should be called in the view's
         * (Activity or Fragment) onPause() method.
         */
        void pause();

        /**
         * Method that control the lifecycle of the view. It should be called in the view's
         * (Activity or Fragment) onDestroy() method.
         */
        void destroy();
    }
}
