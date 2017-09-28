package kt.kr.co.kiwimedia.www.kiwisms.presentation.exception;

/**
 * Created by h02 on 2017. 8. 28..
 */

/**
 * Inteface to represent a wrapper around an java.lang.Exception to manage errors.
 */
public interface ErrorBundle {
    Exception getException();
    String getErrorMessage();
}
