package com.petercoulton.bluecowmoon.web.controllers;

import com.petercoulton.bluecowmoon.core.exceptions.BlueCowMoonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class BlueCowMoonExceptionMapper {
    private static final Logger log = LoggerFactory.getLogger(BlueCowMoonExceptionMapper.class);

    @ExceptionHandler(BlueCowMoonException.class)
    public void handleGenieException(
            final HttpServletResponse response,
            final BlueCowMoonException e
    ) throws IOException {
        response.sendError(e.getErrorCode(), e.getLocalizedMessage());
    }
}
