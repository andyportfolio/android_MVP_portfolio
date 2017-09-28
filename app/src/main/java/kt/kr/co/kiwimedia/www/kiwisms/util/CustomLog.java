package kt.kr.co.kiwimedia.www.kiwisms.util;

import android.util.Log;

import kt.kr.co.kiwimedia.www.kiwisms.BuildConfig;

/**
 * Created by h02 on 2017. 9. 27..
 */

public class CustomLog {

    //http://yoo2yoo2yoo2.tistory.com/3
    public static void d(String TAG, String msg){
        if (BuildConfig.DEBUG){
            Log.d(TAG,msg);
        }
    }


}
