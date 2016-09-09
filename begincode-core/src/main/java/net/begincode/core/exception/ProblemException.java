package net.begincode.core.exception;

/**
 * Created by Stay on 2016/9/9  15:30.
 */
public class ProblemException extends RuntimeException{

    public ProblemException() {
    }

    public ProblemException(String message) {
        super(message);
    }

    public ProblemException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProblemException(Throwable cause) {
        super(cause);
    }

    public ProblemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
