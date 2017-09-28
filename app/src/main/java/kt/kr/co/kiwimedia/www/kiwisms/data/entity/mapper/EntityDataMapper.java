package kt.kr.co.kiwimedia.www.kiwisms.data.entity.mapper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import kt.kr.co.kiwimedia.www.kiwisms.data.entity.SMSEntity;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.SMSListEntity;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.UserEntity;
import kt.kr.co.kiwimedia.www.kiwisms.domain.SMS;
import kt.kr.co.kiwimedia.www.kiwisms.domain.User;

/**
 * Created by h02 on 2017. 8. 30..
 */
@Singleton
public class EntityDataMapper {

    @Inject
    public EntityDataMapper() {
        Log.d("DependencyInjection","EntityDataMapper Construct() : " + this.toString());
    }


    public User transform(UserEntity userEntity){
        User user = null;

        if (userEntity != null){
            user = new User(userEntity.getId(),userEntity.getUsername(),
                    userEntity.getPassword(),userEntity.getToken(),userEntity.getCompany(),userEntity.getResult());
        }

        return user;
    }

    public List<SMS> transform(SMSListEntity smsListEntity){
        List<SMS> smsList = new ArrayList<>();

        List<SMSEntity> items = smsListEntity.items;

        for(SMSEntity smsEntity : items){
            SMS sms = new SMS(smsEntity.num,smsEntity.receiver_number,smsEntity.send_datetime,
                    smsEntity.send_messge,smsEntity.sender_number,smsEntity.from,smsEntity.result);

                    smsList.add(sms);
        }

        return smsList;
    }

    public SMS transform(SMSEntity smsEntity){
        SMS sms = null;

        if (smsEntity != null){
            sms = new SMS(smsEntity.num,smsEntity.receiver_number,smsEntity.send_datetime,
                    smsEntity.send_messge,smsEntity.sender_number,smsEntity.from,smsEntity.result);
        }

        return sms;
    }


//    public List<SMS> transform(Collection<SMSEntity> smsEntityCollection){
//        List<SMS> smsList = new ArrayList<>();
//
//
//
//        for(SMSEntity smsEntity : smsEntityCollection){
//            int itemCount = smsEntity.lists.size();
//
//            for (int i =0 ; i < itemCount-1 ; i++) {
//                smsEntity.lists.get(i);
//
//                SMS sms = new SMS(smsEntity.i(),smsEntity.getReceiver_number(),smsEntity.getSend_datetime(),
//                        smsEntity.getSend_messge(),smsEntity.getSender_number());
//                if (sms != null){
//                    smsList.add(sms);
//                }
//            }
//
//        }
//
//        return smsList;
//    }
//
}
