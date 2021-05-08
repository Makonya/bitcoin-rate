package com.kuehne_nagel.bitcoin_rate.client;

import com.kuehne_nagel.bitcoin_rate.dto.current_rate.CurrentRateResponseDto;
import com.kuehne_nagel.bitcoin_rate.dto.rate_history.RateHistoryResponseDto;
import com.kuehne_nagel.bitcoin_rate.exception.DefiningCurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import static com.kuehne_nagel.bitcoin_rate.enums.ErrorCode.ERROR_CURRENCY_NOT_CORRECT;
import static com.kuehne_nagel.bitcoin_rate.enums.ErrorCode.ERROR_FETCHING_DATA_FROM_API;

@Component
public class BitcoinRateClient {

    @Value("${client.bitcoin-rate.api}")
    private String bitcoinRateUrl;

    private final RestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(BitcoinRateClient.class);

    public BitcoinRateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrentRateResponseDto getCurrentBitcoinRate(String exchangeCurrency) throws DefiningCurrencyException {
        CurrentRateResponseDto responseDto;
        String url = bitcoinRateUrl.concat("/v1/bpi/currentprice/").concat(exchangeCurrency);
        log.info("Sending REST request to {}", url);
        try {
            responseDto = restTemplate.getForObject(url, CurrentRateResponseDto.class);
            if (responseDto == null) {
                throw new DefiningCurrencyException(ERROR_FETCHING_DATA_FROM_API);
            }
        } catch (HttpClientErrorException e) {
            log.error("HttpClientErrorException", e);
            throw new DefiningCurrencyException(ERROR_CURRENCY_NOT_CORRECT);
        } catch (Exception e){
            log.error("ERROR", e);
            throw new DefiningCurrencyException(ERROR_FETCHING_DATA_FROM_API);
        }
        return responseDto;
    }

    public RateHistoryResponseDto getBitcoinRateHistory(String exchangeCurrency, LocalDate from, LocalDate to) throws DefiningCurrencyException {
        RateHistoryResponseDto responseDto;
        String url = String.format("%s/v1/bpi/historical/close.json?start=%s&end=%s&currency=%s",
                bitcoinRateUrl, from, to, exchangeCurrency);
        log.info("Sending REST request to {}", url);
        try {
            responseDto = restTemplate.getForObject(url, RateHistoryResponseDto.class);
            if (responseDto == null) {
                throw new DefiningCurrencyException(ERROR_FETCHING_DATA_FROM_API);
            }
        } catch (HttpClientErrorException e) {
            log.error("ERROR", e);
            throw new DefiningCurrencyException(ERROR_CURRENCY_NOT_CORRECT);
        } catch (Exception e){
            log.error("ERROR", e);
            throw new DefiningCurrencyException(ERROR_FETCHING_DATA_FROM_API);
        }
        return responseDto;
    }
}