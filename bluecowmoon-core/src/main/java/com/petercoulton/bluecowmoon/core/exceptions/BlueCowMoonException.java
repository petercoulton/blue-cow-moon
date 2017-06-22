package com.petercoulton.bluecowmoon.core.exceptions;

public class BlueCowMoonException extends Exception {
    private final int errorCode;

    public BlueCowMoonException(final int errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BlueCowMoonException(final int errorCode, final String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BlueCowMoonException(final int errorCode, final Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
