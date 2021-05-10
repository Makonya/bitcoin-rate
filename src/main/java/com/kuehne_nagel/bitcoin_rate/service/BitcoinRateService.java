package com.kuehne_nagel.bitcoin_rate.service;

import com.kuehne_nagel.bitcoin_rate.client.BitcoinRateClient;
import com.kuehne_nagel.bitcoin_rate.dto.BitcoinRateResponseDto;
import com.kuehne_nagel.bitcoin_rate.dto.LowestHighestValueDto;
import com.kuehne_nagel.bitcoin_rate.enums.ErrorCode;
import com.kuehne_nagel.bitcoin_rate.exception.DefiningCurrencyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

import static com.kuehne_nagel.bitcoin_rate.enums.ErrorCode.ERROR_LOWEST_HIGHEST_WAS_NOT_DEFINED;

@Service
public class BitcoinRateService {

    private final BitcoinRateClient bitcoinRateClient;

    public BitcoinRateService(BitcoinRateClient bitcoinRateClient) {
        this.bitcoinRateClient = bitcoinRateClient;
    }

    public BitcoinRateResponseDto getBitcoinRateDetails(String exchangeCurrency) {
        var bitcoinRateResponseDto = new BitcoinRateResponseDto();
        try {
            if (exchangeCurrency == null || exchangeCurrency.isEmpty()) {
                throw new DefiningCurrencyException(ErrorCode.ERROR_EMPTY_CURRENCY);
            }

            bitcoinRateResponseDto.setCurrentBitcoinRate(getCurrentBitcoinRate(exchangeCurrency));
            var lowestHighestValueDto = getLowestHighestBitcoinRatePerMonth(exchangeCurrency);
            bitcoinRateResponseDto.setLowestBitcoinRatePerMonth(lowestHighestValueDto.getLowestValue());
            bitcoinRateResponseDto.setHighestBitcoinRatePerMonth(lowestHighestValueDto.getHighestValue());
        } catch (DefiningCurrencyException e) {
            bitcoinRateResponseDto.setErrorMessage(e.getErrorCode().getErrorMessage());
        }
        return bitcoinRateResponseDto;
    }

    private LowestHighestValueDto getLowestHighestBitcoinRatePerMonth(String exchangeCurrency) throws DefiningCurrencyException {
        LowestHighestValueDto lowestHighestValueDto;
        var responseDto = bitcoinRateClient.getBitcoinRateHistory(
                exchangeCurrency,
                LocalDate.now().minusMonths(1),
                LocalDate.now());

        var bpi = responseDto.getBpi();
        if (bpi == null || bpi.isEmpty()) {
            throw new DefiningCurrencyException(ERROR_LOWEST_HIGHEST_WAS_NOT_DEFINED);
        }

        var lowestOptional = bpi.entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getValue);
        var highestOptional = bpi.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue);

        lowestHighestValueDto = new LowestHighestValueDto(lowestOptional.get(), highestOptional.get());

        return lowestHighestValueDto;
    }

    private Float getCurrentBitcoinRate(String exchangeCurrency) throws DefiningCurrencyException {
        var responseDto = bitcoinRateClient.getCurrentBitcoinRate(exchangeCurrency);
        var bpi = responseDto.getBpi();
        return bpi.get(exchangeCurrency.toUpperCase()).getRate_float();
    }
}