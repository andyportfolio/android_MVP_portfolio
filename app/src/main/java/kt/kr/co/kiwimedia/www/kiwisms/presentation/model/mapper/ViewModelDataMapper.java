package kt.kr.co.kiwimedia.www.kiwisms.presentation.model.mapper;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Singleton;

import kt.kr.co.kiwimedia.www.kiwisms.domain.SMS;
import kt.kr.co.kiwimedia.www.kiwisms.domain.User;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.SMSModel;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.UserModel;

/**
 * Created by h02 on 2017. 8. 29..
 */

@Singleton
public class ViewModelDataMapper {
    private static final String TAG = "DependencyInjection";

    @Inject
    public ViewModelDataMapper() {
        Log.d(TAG,"ViewModelDataMapper construct()");
    }

    /**
     * user to usermodel
     * @param user
     * @return
     */
    public UserModel transform(User user){
        if (user == null){
            throw new IllegalArgumentException("Cannot transform a null User Class");
        }
        final UserModel userModel = new UserModel(user.getId(),user.getUsername(),
                user.getPassword(),user.getToken(),user.getCompany());

        return userModel;
    }


    public SMSModel transform(SMS sms){
        if (sms == null){
            throw new IllegalArgumentException("Cannot transform a null SMS Class");
        }
        final SMSModel smsModel = new SMSModel(sms.getNum(),sms.getReceiver_number(),
                sms.getSend_datetime(),sms.getSend_message(),sms.getSender_number(),sms.getFrom());

        return smsModel;
    }

    public Collection<SMSModel> transform(Collection<SMS> smsCollection){
        Collection<SMSModel> smsModelCollection;

        if (smsCollection != null && !smsCollection.isEmpty()){
            smsModelCollection = new ArrayList<>();

            for(SMS sms: smsCollection){
                smsModelCollection.add(transform(sms));
            }
        }else{
            smsModelCollection = Collections.emptyList();
        }

        return smsModelCollection;
    }

}
