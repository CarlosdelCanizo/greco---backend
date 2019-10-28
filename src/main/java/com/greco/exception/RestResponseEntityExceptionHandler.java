package com.greco.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import java.sql.SQLException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataException.class)
    protected ResponseEntity<Object> handleDataException( DataException ex) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
        apiError.setDebugMessage(ex.getMessage());
        apiError.setMessage("exception.data");

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException( ConstraintViolationException ex) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST);

        if (ex.getSQLException() != null) {
            SQLException sqlEx = ex.getSQLException();
            apiError.setDebugMessage(sqlEx.getMessage());

            switch( sqlEx.getSQLState()) {
                case "23503":
                    String strEntity = getChildEntity(sqlEx);
                    apiError.setMessage("exception.delete.parent" + strEntity);

                    break;
                case "23505":
                    apiError.setMessage("exception.unique.constrain");
                    break;
                default:
                    apiError.setMessage("exception.constraint.violation");
            }
        }
        else {
            apiError.setDebugMessage(ex.getMessage());
            apiError.setMessage("exception.constraint.violation");
        }

        return buildResponseEntity(apiError);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    protected ResponseEntity<Object> handleConflictException( ConstraintViolationException ex) {
//        APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
//        if (ex.getCause() instanceof DataException) {
//            apiError.setDebugMessage(ex.getMessage());
//            apiError.setMessage("exception.data");
//        }
//        else if (ex.getCause() instanceof ConstraintViolationException) {
//            apiError.setDebugMessage(ex.getMessage());
//            apiError.setMessage("exception.constraint.violation");
//        }
//        else {
//            apiError.setDebugMessage(ex.getMessage());
//            apiError.setMessage("");
//
//        }
//        return buildResponseEntity(apiError);
//    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
        apiError.setDebugMessage(ex.getMessage());
        apiError.setMessage("Bad.Request");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    protected ResponseEntity<Object> handleInternalServerError(InternalServerErrorException ex) {
        APIError apiError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setDebugMessage(ex.getMessage());
        apiError.setMessage("Internal.Server.Error");
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private String getChildEntity(SQLException ex) {
        String message = ex.getMessage();
        String[] tokens;
        if (message != null || ! message.equals("")) {
            tokens = message.split("\"");
            if (tokens.length > 5) {
                return "|" + tokens[5];
            }
        }
        return "";
    }
}
