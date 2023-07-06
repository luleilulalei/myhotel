package org.ysu.exceptions;

public class BeanNotFound extends BaseException {
    public BeanNotFound(String cause) {
        super(cause + " does not founded");
    }
}
