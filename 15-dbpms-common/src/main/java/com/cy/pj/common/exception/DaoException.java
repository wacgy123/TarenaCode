package com.cy.pj.common.exception;

public class DaoException extends RuntimeException{
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
