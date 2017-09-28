package kt.kr.co.kiwimedia.www.kiwisms.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import kt.kr.co.kiwimedia.www.kiwisms.domain.SMS;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.PerActivity;

/**
 * Created by h02 on 2017. 8. 29..
 */

@PerActivity
public class GetSMSList extends UseCase<List<SMS>,GetSMSList.Params> {

    private final SMSRepository userRepository;

    @Inject
    public GetSMSList(SMSRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    Observable<List<SMS>> buildUseCaseOvservable(Params parmas) {
        return this.userRepository.getSMSList(parmas.id);
    }

    public static final class Params{
        private final String id;

        private Params(String id) {
            this.id = id;
        }

        public static Params forGetSMSList(String id) {
            return new Params(id);
        }
    }


}
