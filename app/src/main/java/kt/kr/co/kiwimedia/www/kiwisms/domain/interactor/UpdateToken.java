package kt.kr.co.kiwimedia.www.kiwisms.domain.interactor;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import kt.kr.co.kiwimedia.www.kiwisms.domain.User;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.PerActivity;

/**
 * Created by h02 on 2017. 8. 29..
 */

@PerActivity
//@Singleton
public class UpdateToken extends UseCase<User,UpdateToken.Params> {

    private final SMSRepository userRepository;

    @Inject
    public UpdateToken(SMSRepository userRepository) {
        Log.d("DependencyInjection","UpdateToken Construct() : " + this.toString());
        this.userRepository = userRepository;
    }

    @Override
    Observable<User> buildUseCaseOvservable(Params parmas) {
        return this.userRepository.updateToken(parmas.id,parmas.token);
    }

    public static final class Params{
        private final String id;
        private final String token;

        //위부에서 생성을 막기위해 사용 -- 왜 이렇게 만들었을까 ?
        private Params(String id, String token) {
            this.id = id;
            this.token = token;
        }

        public static Params forUpdateToken(String id,String token) {
            return new Params(id,token);
        }
    }


}
