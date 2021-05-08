package com.kuehne_nagel.bitcoin_rate.exception;

import com.kuehne_nagel.bitcoin_rate.enums.ErrorCode;

public class DefiningCurrencyException extends Exception {

    private final ErrorCode errorCode;

    public DefiningCurrencyException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}