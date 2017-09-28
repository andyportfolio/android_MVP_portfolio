package kt.kr.co.kiwimedia.www.kiwisms.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.SMSModel;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm.FcmActivity;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail.SMSDetailsActivity;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist.SMSListActivity;

/**
 * Created by h02 on 2017. 8. 28..
 */
@Singleton
public class Navigator {
    public static final String TAG ="DependencyInjection";

    @Inject
    public Navigator() {
        //empty
        Log.d(TAG,"Navigator() Constructor : " + this.toString());
    }

    /**
     * Goes to the Smslist screen.
     * @param context
     */
    public void navigateToSmsList(Context context){
        if (context != null){
            Intent intent = SMSListActivity.getCallingIntent(context);
            context.startActivity(intent);
        }
    }

    public void navigateToSmsDetails(Context context, SMSModel smsModel){
        if (context != null){
            Intent intent = SMSDetailsActivity.getCallingIntent(context,smsModel);
            context.startActivity(intent);
        }
    }


    public void navigateToFcm(Context context){
        if (context != null){
            Intent intent = FcmActivity.getCallingIntent(context);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); //stack에 저장하지 않음
            context.startActivity(intent);
        }
    }

}
