package kt.kr.co.kiwimedia.www.kiwisms.presentation.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.MyApplication;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.ActivityBuilder;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.ApplicationModule;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.DataModule;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.DomainModule;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.modules.PresentationModule;

/**
 * Created by h02 on 2017. 8. 28..
 * ApplicationModule 은 실제 injection하려는 provide를 가짐
 * ActivityBuilder 는 어떤 activity들이 injection 대상인지를 나타낸다
 * AndroidInjectionModule 는 dagger inner class
 */

@Component (modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        PresentationModule.class,
        DomainModule.class,
        DataModule.class,
        ActivityBuilder.class})
@Singleton
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance Builder application(Application application);
        ApplicationComponent build();
    }

    void inject(MyApplication target);
    //void inject(BaseActivity target);

//    void inject(FirebaseInstanceIDService target);
//    void inject(FirebaseMessagingService target);
}

/*
하기의 순서대로 생성됨
Application: 모두 singlton
   Navigator           -  생성자에 파라메터가 없어서 ApplicationModule에 기술하지 않음
   CloudUserDataStore
   EntityDataMapper
   UserDataRepository

LoginActivity의 LoginPresenter를 Injection할때 생성
 DoLogin - PerActivity
 ViewModelDataMapper  -  생성자에 파라메터가 없어서
                         ApplicationModule 나 LoginActivityModule에 기술하지 않음
 LoginPresenter <<- LoginPresenter의 생성자가 마지막에 호출된다
 */