package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.mapper.ViewModelDataMapper;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.navigation.Navigator;

/**
 * Created by h02 on 2017. 9. 1..
 */
@Module
public class PresentationModule {
    @Provides
    @Singleton
    ViewModelDataMapper provideUserModelDataMapper() {
        return new ViewModelDataMapper();
    }

    //
    //Navigator 의 생성자에 @Inject가 없으면 여기에 구현해야 한다
    @Provides
    @Singleton
    Navigator provideNavigator(){
        return new Navigator();
    }

}
