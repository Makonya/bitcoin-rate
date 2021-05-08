package com.kuehne_nagel.bitcoin_rate.enums;

public enum ErrorCode {
    ERROR_EMPTY_CURRENCY("Currency should not defined correctly."),
    ERROR_CURRENCY_NOT_CORRECT("Typed currency does not exist."),
    ERROR_FETCHING_DATA_FROM_API("Service for getting currency rate is not responding correctly."),
    ERROR_LOWEST_HIGHEST_WAS_NOT_DEFINED("Could not define lowest and highest value.");

    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
