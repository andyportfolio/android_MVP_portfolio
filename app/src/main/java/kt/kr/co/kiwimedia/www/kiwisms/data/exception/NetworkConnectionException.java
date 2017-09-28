package kt.kr.co.kiwimedia.www.kiwisms.data.exception;

/**
 * Created by h02 on 2017. 8. 28..
 */

/**
 * When there is a networkexception, this exception throw by application
 */
public class NetworkConnectionException extends Exception {
    public NetworkConnectionException() {
        super();
    }

    public NetworkConnectionException(final Throwable cause){
        super(cause);
    }
}
