package com.kuehne_nagel.bitcoin_rate.service;

import com.kuehne_nagel.bitcoin_rate.client.BitcoinRateClient;
import com.kuehne_nagel.bitcoin_rate.dto.BitcoinRateResponseDto;
import com.kuehne_nagel.bitcoin_rate.dto.LowestHighestValueDto;
import com.kuehne_nagel.bitcoin_rate.dto.current_rate.CurrencyDto;
import com.kuehne_nagel.bitcoin_rate.dto.current_rate.CurrentRateResponseDto;
import com.kuehne_nagel.bitcoin_rate.dto.rate_history.RateHistoryResponseDto;
import com.kuehne_nagel.bitcoin_rate.enums.ErrorCode;
import com.kuehne_nagel.bitcoin_rate.exception.DefiningCurrencyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.kuehne_nagel.bitcoin_rate.enums.ErrorCode.ERROR_LOWEST_HIGHEST_WAS_NOT_DEFINED;

@Service
public class BitcoinRateService {

    private final BitcoinRateClient bitcoinRateClient;

    public BitcoinRateService(BitcoinRateClient bitcoinRateClient) {
        this.bitcoinRateClient = bitcoinRateClient;
    }


    public BitcoinRateResponseDto getBitcoinRateDetails(String exchangeCurrency) {
        BitcoinRateResponseDto bitcoinRateResponseDto = new BitcoinRateResponseDto();
        try {
            if (exchangeCurrency.length() == 0) {
                throw new DefiningCurrencyException(ErrorCode.ERROR_EMPTY_CURRENCY);
            }
            bitcoinRateResponseDto.setCurrentBitcoinRate(getCurrentBitcoinRate(exchangeCurrency));
            LowestHighestValueDto lowestHighestValueDto = getLowestHighestBitcoinRatePerMonth(exchangeCurrency);
            bitcoinRateResponseDto.setLowestBitcoinRatePerMonth(lowestHighestValueDto.getLowestValue());
            bitcoinRateResponseDto.setHighestBitcoinRatePerMonth(lowestHighestValueDto.getHighestValue());
        } catch (DefiningCurrencyException e) {
            bitcoinRateResponseDto.setErrorMessage(e.getErrorCode().getErrorMessage());
        }
        return bitcoinRateResponseDto;
    }

    private LowestHighestValueDto getLowestHighestBitcoinRatePerMonth(String exchangeCurrency) throws DefiningCurrencyException {
        LowestHighestValueDto lowestHighestValueDto;
        RateHistoryResponseDto responseDto = bitcoinRateClient.getBitcoinRateHistory(exchangeCurrency, LocalDate.now().minusMonths(1), LocalDate.now());
        LinkedHashMap<String, Float> bpi = responseDto.getBpi();
        Optional<Float> lowestOptional = bpi.entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getValue);
        Optional<Float> highestOptional = bpi.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue);
        if (lowestOptional.isPresent() && highestOptional.isPresent()) {
            lowestHighestValueDto = new LowestHighestValueDto(lowestOptional.get(), highestOptional.get());
        } else {
            throw new DefiningCurrencyException(ERROR_LOWEST_HIGHEST_WAS_NOT_DEFINED);
        }
        return lowestHighestValueDto;
    }

    private Float getCurrentBitcoinRate(String exchangeCurrency) throws DefiningCurrencyException {
        CurrentRateResponseDto responseDto = bitcoinRateClient.getCurrentBitcoinRate(exchangeCurrency);
        Map<String, CurrencyDto> bpi = responseDto.getBpi();
        return bpi.get(exchangeCurrency.toUpperCase()).getRate_float();
    }
}