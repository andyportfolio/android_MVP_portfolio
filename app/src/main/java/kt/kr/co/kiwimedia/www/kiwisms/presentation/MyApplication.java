package kt.kr.co.kiwimedia.www.kiwisms.presentation;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.di.components.DaggerApplicationComponent;


/**
 * Created by andy on 2017. 8. 27..
 */

public class MyApplication extends Application implements HasActivityInjector ,HasServiceInjector {

    //activity에 injecting하기 위해서 이것을 생성한다
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    //service에서 injecting하기 위해서 이것을 생성한다
    @Inject
    DispatchingAndroidInjector<Service> serviceAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);

    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }


    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceAndroidInjector;
    }
}
