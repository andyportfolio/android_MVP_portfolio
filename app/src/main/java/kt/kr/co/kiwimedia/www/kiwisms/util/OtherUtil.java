package kt.kr.co.kiwimedia.www.kiwisms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andy on 2017. 9. 6..
 */

public class OtherUtil {
    public static String getDateTime() {
        //출처: http://liveonthekeyboard.tistory.com/129 [키위남]
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long mNow = System.currentTimeMillis();
        Date mDate = new Date(mNow);

        return mFormat.format(mDate);

    }
}
