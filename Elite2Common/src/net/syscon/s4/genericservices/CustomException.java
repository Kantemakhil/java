package net.syscon.s4.genericservices;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    private ErrorCodes errorCodes;

    public CustomException() {
        // to be used if necessary
    }

    public CustomException(final String message) {
        super(message);
    }

    public CustomException(HttpStatus httpStatus, ErrorCodes errorCodes) {
        super(String.format(errorCodes.getErrorMessage(), ""));
        this.httpStatus = httpStatus;
        this.errorCodes = errorCodes;
    }

    public CustomException(HttpStatus httpStatus, ErrorCodes errorCodes, String message) {
        super(String.format(errorCodes.getErrorMessage(), null == message ? "" : message));
        this.httpStatus = httpStatus;
        this.errorCodes = errorCodes;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }
}
