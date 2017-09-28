package kt.kr.co.kiwimedia.www.kiwisms.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import kt.kr.co.kiwimedia.www.kiwisms.domain.SMS;
import kt.kr.co.kiwimedia.www.kiwisms.domain.User;

/**
 * Created by h02 on 2017. 8. 29..
 */

public interface SMSRepository {

    Observable<User> doLogin(String userid, String password );

    Observable<List<SMS>> getSMSList(String id);

    Observable<SMS> getSMSDetail();

    Observable<User> updateToken(String id, String token);

    Observable<SMS> updateSMSSendResult(String id, String send_datetime, String from);

}
