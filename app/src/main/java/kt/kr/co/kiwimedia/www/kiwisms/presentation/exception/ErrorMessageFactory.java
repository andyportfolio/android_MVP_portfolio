package kt.kr.co.kiwimedia.www.kiwisms.presentation.exception;

import android.content.Context;

import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.data.exception.NetworkConnectionException;

/**
 * Created by h02 on 2017. 8. 28..
 */

public class ErrorMessageFactory {

    public ErrorMessageFactory() {
    }

    public static String create(Context context, Exception exception){
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException){
            message = context.getString(R.string.exception_message_no_connection);
        }
        //다른 exception이 필요할때 exception을 만들고 , 여기에 해당 exception일경우에
        // 사용할 메세지를 정의한다.

        return message;

    }
}
