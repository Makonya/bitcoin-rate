package com.kuehne_nagel.bitcoin_rate.dto;

public class LowestHighestValueDto {

    private Float lowestValue;

    private Float highestValue;

    public LowestHighestValueDto(Float lowestValue, Float highestValue) {
        this.lowestValue = lowestValue;
        this.highestValue = highestValue;
    }

    public Float getLowestValue() {
        return lowestValue;
    }

    public void setLowestValue(Float lowestValue) {
        this.lowestValue = lowestValue;
    }

    public Float getHighestValue() {
        return highestValue;
    }

    public void setHighestValue(Float highestValue) {
        this.highestValue = highestValue;
    }

    @Override
    public String toString() {
        return "LowestHighestValueDto{" +
                "lowestValue=" + lowestValue +
                ", highestValue=" + highestValue +
                '}';
    }
}