package com.kuehne_nagel.bitcoin_rate.dto.rate_history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateHistoryResponseDto {

    private LinkedHashMap<String, Float> bpi;

    private String disclaimer;

    private TimeDto time;

    public LinkedHashMap<String, Float> getBpi() {
        return bpi;
    }

    public void setBpi(LinkedHashMap<String, Float> bpi) {
        this.bpi = bpi;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public TimeDto getTime() {
        return time;
    }

    public void setTime(TimeDto time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RateHistoryResponseDto{" +
                "bpi=" + bpi +
                ", disclaimer='" + disclaimer + '\'' +
                ", time=" + time +
                '}';
    }
}