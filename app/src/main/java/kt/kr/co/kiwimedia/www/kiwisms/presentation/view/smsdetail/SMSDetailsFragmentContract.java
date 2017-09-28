package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail;

/**
 * Created by h02 on 2017. 9. 13..
 */

public interface SMSDetailsFragmentContract {

    interface View{
        void showData();

    }

    interface Presenter{
        void loadSMSDetails();
    }


}
