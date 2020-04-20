package com.armydocs.server.exception;

public class InvaildTimeToChangeError extends RuntimeException {
    public InvaildTimeToChangeError() {
        super();
    }

    public InvaildTimeToChangeError(String message) {
        super(message);
    }

    public InvaildTimeToChangeError(String message, Throwable cause) {
        super(message, cause);
    }

    public InvaildTimeToChangeError(Throwable cause) {
        super(cause);
    }
}
