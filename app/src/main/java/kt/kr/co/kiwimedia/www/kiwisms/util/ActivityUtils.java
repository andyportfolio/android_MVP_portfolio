package kt.kr.co.kiwimedia.www.kiwisms.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by andy on 2017. 8. 13..
 */

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId){
        //Guava
       // checkNotNull(fragmentManager);
       // checkNotNull(fragment);

        fragmentManager.beginTransaction()
                .add(frameId,fragment)
                .commit();

    }
}
