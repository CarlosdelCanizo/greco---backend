package com.greco.exception;

import org.springframework.http.HttpStatus;

public class ServerException extends RuntimeException {
    private final ServerErrorEnum serverErrorEnum;
    private final String annexMessage;

    public ServerException(ServerErrorEnum serverErrorEnum) {
        this.serverErrorEnum = serverErrorEnum;
        this.annexMessage = "";
    }

    public ServerException(ServerErrorEnum serverErrorEnum,
                           String annexMessage) {
        this.serverErrorEnum = serverErrorEnum;
        this.annexMessage = annexMessage;
    }


    @Override
    public String getMessage() {
        return this.serverErrorEnum.message();
    }

    /**
     * Additional message completing the error information.
     * This message is optional and may not come informed.
     *
     * @return The annexed message.
     */
    public String getAnnexMessage() {
        return this.annexMessage;
    }

    /**
     * Internal code defined for application.
     *
     * @return The internal code.
     */
    public String getCode() {
        return this.serverErrorEnum.code();
    }

    /**
     * Http status bounded to the error.
     *
     * @return The <code>HttpStatus</code> object.
     */
    public HttpStatus getHttpStatus() {
        return this.serverErrorEnum.httpStatus();
    }
}
