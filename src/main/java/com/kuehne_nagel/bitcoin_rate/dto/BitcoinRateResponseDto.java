package com.kuehne_nagel.bitcoin_rate.dto;

import java.util.Objects;

/**
 * Dto for bitcoin rate details
 */
public class BitcoinRateResponseDto {

    private String errorMessage;

    private Float currentBitcoinRate;

    private Float lowestBitcoinRatePerMonth;

    private Float highestBitcoinRatePerMonth;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Float getCurrentBitcoinRate() {
        return currentBitcoinRate;
    }

    public void setCurrentBitcoinRate(Float currentBitcoinRate) {
        this.currentBitcoinRate = currentBitcoinRate;
    }

    public Float getLowestBitcoinRatePerMonth() {
        return lowestBitcoinRatePerMonth;
    }

    public void setLowestBitcoinRatePerMonth(Float lowestBitcoinRatePerMonth) {
        this.lowestBitcoinRatePerMonth = lowestBitcoinRatePerMonth;
    }

    public Float getHighestBitcoinRatePerMonth() {
        return highestBitcoinRatePerMonth;
    }

    public void setHighestBitcoinRatePerMonth(Float highestBitcoinRatePerMonth) {
        this.highestBitcoinRatePerMonth = highestBitcoinRatePerMonth;
    }

    @Override
    public String toString() {
        return Objects.nonNull(errorMessage) ? errorMessage :
                "Current bitcoin rate: " + currentBitcoinRate + "\n" +
                        "Lowest bitcoin rate: " + lowestBitcoinRatePerMonth + "\n" +
                        "Highest bitcoin rate: " + highestBitcoinRatePerMonth;
    }
}