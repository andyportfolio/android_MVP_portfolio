package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by h02 on 2017. 8. 28..
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

// 아래와 같이 쓰면 missing setter라고 나온다.

//Error:(27, 5) error: @Component.Builder is missing setters for required modules or components:
// [kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.ApplicationModule]
//    private final MyApplication application;
//
//    public ApplicationModule(MyApplication application) {
//        this.application = application;
//    }
//
//    @Provides
//    @Singleton
//    Context provideContext(){
//        return this.application;
//    }





}
