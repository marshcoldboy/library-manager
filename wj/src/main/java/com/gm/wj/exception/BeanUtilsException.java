package com.gm.wj.exception;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public class BeanUtilsException extends RuntimeException {

    public BeanUtilsException(String message) {
        super(message);
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }
}

