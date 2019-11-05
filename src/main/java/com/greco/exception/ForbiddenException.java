package com.greco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenException extends APIException {
    public ForbiddenException(){ super("Forbidden operation"); }
    public ForbiddenException(String message) {
        super(message);
    }
}
