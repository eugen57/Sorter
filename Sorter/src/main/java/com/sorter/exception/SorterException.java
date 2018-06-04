package com.sorter.exception;

import com.sorter.enums.SorterErrorCode;

public class SorterException extends Exception {
    
    private SorterErrorCode errorCode;

    public SorterException(SorterErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public SorterErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(SorterErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
