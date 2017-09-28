package kt.kr.co.kiwimedia.www.kiwisms.domain.interactor;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import kt.kr.co.kiwimedia.www.kiwisms.domain.SMS;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.PerActivity;

/**
 * Created by h02 on 2017. 8. 29..
 */

@PerActivity
//@Singleton
public class UpdateSMSSendResult extends UseCase<SMS,UpdateSMSSendResult.Params> {

    private final SMSRepository userRepository;

    @Inject
    public UpdateSMSSendResult(SMSRepository userRepository) {
        Log.d("DependencyInjection","UpdateToken Construct() : " + this.toString());
        this.userRepository = userRepository;
    }

    @Override
    Observable<SMS> buildUseCaseOvservable(Params parmas) {
        return this.userRepository.updateSMSSendResult(parmas.id,parmas.send_datetime,parmas.from);
    }

    public static final class Params{
        private final String id;
        private final String send_datetime;
        private final String from;


        //위부에서 생성을 막기위해 사용 -- 왜 이렇게 만들었을까 ?
        private Params(String id, String send_datetime, String from) {
            this.id = id;
            this.send_datetime = send_datetime;
            this.from = from;
        }

        public static Params forUpdateSMSSendResult(String id,String send_datetime,String from) {
            return new Params(id,send_datetime,from);
        }
    }


}
