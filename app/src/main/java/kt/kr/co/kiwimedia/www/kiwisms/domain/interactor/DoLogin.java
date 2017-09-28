package kt.kr.co.kiwimedia.www.kiwisms.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import kt.kr.co.kiwimedia.www.kiwisms.domain.User;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.PerActivity;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;

/**
 * Created by h02 on 2017. 8. 29..
 */

@PerActivity
public class DoLogin extends UseCase<User,DoLogin.Params> {

    private final SMSRepository userRepository;

    @Inject
    public DoLogin(SMSRepository userRepository) {
        CustomLog.d("DependencyInjection","DoLogin Construct() : " + this.toString());
        this.userRepository = userRepository;
    }

    @Override
    Observable<User> buildUseCaseOvservable(Params parmas) {
        return this.userRepository.doLogin(parmas.userid,parmas.password);
    }

    public static final class Params{
        private final String userid;
        private final String password;

        //내부에서 사용할 파라메터를 내부에서 생성
        private Params(String userid, String password) {
            this.userid = userid;
            this.password = password;
        }

        public static Params forLogin(String userid,String password) {
            return new Params(userid,password);
        }
    }


}
