package org.ysu.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseException extends Throwable {
    private String cause;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseException.class);

    public BaseException(String cause) {
        this.cause = cause;
    }

    public void printCause(){
        LOGGER.error(cause);
    }
}
