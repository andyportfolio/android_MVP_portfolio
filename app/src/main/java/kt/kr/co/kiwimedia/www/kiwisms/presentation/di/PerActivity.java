package kt.kr.co.kiwimedia.www.kiwisms.presentation.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by h02 on 2017. 8. 30..
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
