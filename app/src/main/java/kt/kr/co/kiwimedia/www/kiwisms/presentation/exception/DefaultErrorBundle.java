package kt.kr.co.kiwimedia.www.kiwisms.presentation.exception;

/**
 * Created by h02 on 2017. 8. 28..
 */

/**
 * Wrapper around Exception used to manage default errors
 */
public class DefaultErrorBundle implements ErrorBundle {
    private static final String DEFAULT_ERROR_MSG = "Unknow error";

    private final Exception exception;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        return (exception != null) ? this.exception.getMessage() : DEFAULT_ERROR_MSG;
    }
}
