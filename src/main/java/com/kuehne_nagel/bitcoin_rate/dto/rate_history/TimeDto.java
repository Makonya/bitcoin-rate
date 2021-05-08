package com.kuehne_nagel.bitcoin_rate.dto.rate_history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeDto {

    private String updated;

    private Instant updatedISO;

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

    @Override
    public String toString() {
        return "TimeDto{" +
                "updated='" + updated + '\'' +
                ", updatedISO=" + updatedISO +
                '}';
    }
}