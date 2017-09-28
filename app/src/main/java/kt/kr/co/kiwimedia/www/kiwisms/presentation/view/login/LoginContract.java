package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.login;

import android.content.Context;

/**
 * Created by andy on 2017. 8. 17..
 */

public interface LoginContract {

    interface View {
        //presenter가 호출할 method를 기술.

        void setInitValue(String userid);
        void showProgress();
        void hideProgress();
        void enableButton();
        void disableButton();

        void setUserIdError();
        void setUserPasswordError();

        void navigateToFcmActivity();
        void navigateToSMSListActivity();

        void showLoginFail();
        void showLoginError(String message);

        Context context();

    }

    interface Presenter {
        //viw에 의해 불려졌을때(주로 click event) 해야 할 method를 기술한다.
        void validateCredentials(String userId, String userPassword);

        void requestSetInitValue();

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
