package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist;

import android.content.Context;

import java.util.Collection;

import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.SMSModel;

/**
 * Created by h02 on 2017. 8. 31..
 */

public interface SMSListFragmentContract {

    interface View {
        //Render a sms list in the UI
        void renderSMSList(Collection<SMSModel> smsModelCollection);

        //View a details
        void viewSMS(SMSModel smsModel);

        void showLoading();
        void hideLoading();

        void showRetry();
        void hideRetry();

        void showError(String errorMessage);

        Context context(); //공통의 성격으로 사용 ==> 나중에 BaseView 나 BasePresenter를 만들때 추가하라


    }

    interface Presenter {
        /**
         * Method that control the lifecycle of the view. It should be called in the view's
         * (Activity or Fragment) onResume() method.
         */
        void resume();
        void pause();
        void destroy();

        void onSMSClicked(SMSModel smsModel);

        void initialize();

    }
}
