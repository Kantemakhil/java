package net.syscon.s4.genericservices;

public enum ErrorCodes {
    
    REQUEST_INPUT_ERROR("E001", "Request input is not valid '%s'"),
    RQUEST_INPUT_DATE_FORMAT_ERROR("E002", "Date format process error '%s'"),
    REPORT_PROCESSING_ERROR("E050", "Not able to process report '%s'"),
	REPORT_ASSETS_MISSING_ERROR("E051", "Assets are missing in system, Upload assets to resolve the error.");

    private String errorCode;
    private String errorMessage;

    ErrorCodes(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
