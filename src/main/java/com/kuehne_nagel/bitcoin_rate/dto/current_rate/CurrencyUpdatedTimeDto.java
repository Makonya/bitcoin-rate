package com.kuehne_nagel.bitcoin_rate.dto.current_rate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyUpdatedTimeDto {

    private String updated;

    private Instant updatedISO;

    private String updateduk;

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Instant getUpdatedISO() {
        return updatedISO;
    }

    public void setUpdatedISO(Instant updatedISO) {
        this.updatedISO = updatedISO;
    }

    public String getUpdateduk() {
        return updateduk;
    }

    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }

    @Override
    public String toString() {
        return "ActualityDataDto{" +
                "updated='" + updated + '\'' +
                ", updatedISO=" + updatedISO +
                ", updateduk='" + updateduk + '\'' +
                '}';
    }
}