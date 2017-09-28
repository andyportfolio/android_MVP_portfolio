package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.login;

import dagger.Module;
import dagger.Provides;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.DoLogin;
import kt.kr.co.kiwimedia.www.kiwisms.domain.repository.SMSRepository;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.mapper.ViewModelDataMapper;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.login.LoginActivity;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.login.LoginContract;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.login.LoginPresenter;

/**
 * Created by h02 on 2017. 8. 28..
 * LoginActivity에서는 Presenter를 생성해야 한다 . new LoginPresenter(this);
 * 이것을 Dagger를 통해서 Injection하기 위해서 view 를 제공하는 메소드와 그것을 통해서 presenter를 생성하는
 * 메소드를 만드는 것이다.
 * view 와 presenter를 제공 => inject 되게 한다
 */
@Module
public class LoginActivityModule {

    //activity 나 fragment는 AndroidInjector 에 의해서 제공받으므로 LoginActivity를 제공하는 @Provides는 별도로
    //필요가 없다.
    @Provides
    LoginContract.View prvoideLoginView(LoginActivity loginActivity) {
        return loginActivity;
    }

    @Provides
    DoLogin provoideDoLogin(SMSRepository smsRepository) {
        return new DoLogin(smsRepository);
    }

    //ViewModelDataMapper 는 ApplicationModule에 의해서 생성되었다
    @Provides
    LoginContract.Presenter providePresenter(LoginContract.View view,
                                             DoLogin doLogin,
                                             ViewModelDataMapper userModelDataMapper){
        return new LoginPresenter(view,doLogin, userModelDataMapper);
    }

//    LoginContract.Presenter providePresenter(LoginContract.View view,
//                                             DoLogin doLogin,
//                                             ViewModelDataMapper userModelDataMapper){
//        return new LoginPresenter(view,doLogin, userModelDataMapper);
//    }

}