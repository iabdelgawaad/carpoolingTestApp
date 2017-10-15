package com.example.ibrahimabdelgawad.carpoolingtestapp.util;

/**
 * Created by Ibrahim AbdelGawad on 10/11/2017.
 */

public class APIError {

    private String requestId;
    private String errorDomain;
    private Integer errorCode;
    private String message;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorDomain() {
        return errorDomain;
    }

    public void setErrorDomain(String errorDomain) {
        this.errorDomain = errorDomain;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
