package com.greco.exception;

import org.springframework.http.HttpStatus;

public enum ServerErrorEnum {
    DEFAULT_ERROR("000000", "An unexpected error has occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    UPLOAD_FILE_MISSING_FILE("000001", "The file is missing", HttpStatus.NOT_FOUND),
    UPLOAD_FILE_MISSING_FOLDER("000002", "The folder is missing", HttpStatus.NOT_FOUND),
    UPLOAD_FILE_EMPTY_FOLDER("000003", "The folder route is empty", HttpStatus.INTERNAL_SERVER_ERROR),
    MISSING_FIELD("000004", "A required field is missing", HttpStatus.NOT_FOUND),
    FILE_NOT_FOUND("000005", "The supplied file couldn't be found", HttpStatus.NOT_FOUND),
    FILE_READ_FAILURE("000006", "The supplied file exists but it couldn't be read", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND("000007", "The user name couldn't be found", HttpStatus.NOT_FOUND),
    DATABASE_TIMEOUT("000008", "The current database operation has timedout", HttpStatus.GATEWAY_TIMEOUT),
    INSERT_PROCEDURE_ERROR("000009","The insert procedure has failed", HttpStatus.BAD_GATEWAY),
    UPDATE_PROCEDURE_ERROR("000010","The update procedure has failed", HttpStatus.BAD_GATEWAY),
    DELETE_PROCEDURE_ERROR("000011","The delete procedure has failed", HttpStatus.BAD_GATEWAY),
    ENTITY_NOT_FOUND("000012", "The desired entity couldn't be found", HttpStatus.NOT_FOUND),
    ENTITY_SAVED_FAILED_RETRIEVAL("000013", "The desired entity was inserted but it couldn't be retrieved", HttpStatus.INTERNAL_SERVER_ERROR),
    JPA_GENERIC_ERROR("000014", "A generic JPA error has happened", HttpStatus.INTERNAL_SERVER_ERROR);

    private String code;
    private String message;
    private HttpStatus httpStatus;

    ServerErrorEnum(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
