package com.kuehne_nagel.bitcoin_rate.dto.current_rate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentRateResponseDto {

    private CurrencyUpdatedTimeDto time;

    private String disclaimer;

    private Map<String, CurrencyDto> bpi;

    public CurrencyUpdatedTimeDto getTime() {
        return time;
    }

    public void setTime(CurrencyUpdatedTimeDto time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Map<String, CurrencyDto> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, CurrencyDto> bpi) {
        this.bpi = bpi;
    }

    @Override
    public String toString() {
        return "CurrentRateResponseDto{" +
                "time=" + time +
                ", disclaimer='" + disclaimer + '\'' +
                ", steps=" + bpi +
                '}';
    }
}
