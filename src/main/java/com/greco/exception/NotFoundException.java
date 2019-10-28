package com.greco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends APIException {

    public NotFoundException() {
        super("not.found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
