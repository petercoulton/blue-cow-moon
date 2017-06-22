package com.petercoulton.bluecowmoon.core.exceptions;

import java.net.HttpURLConnection;

public class BlueCowMoonNotFoundException extends BlueCowMoonException {
    private static final int ERROR_CODE = HttpURLConnection.HTTP_NOT_FOUND;

    public BlueCowMoonNotFoundException(final String message) {
        super(ERROR_CODE, message);
    }

    public BlueCowMoonNotFoundException(final String message, final Throwable cause) {
        super(ERROR_CODE, message, cause);
    }

    public BlueCowMoonNotFoundException(final Throwable cause) {
        super(ERROR_CODE, cause);
    }
}
