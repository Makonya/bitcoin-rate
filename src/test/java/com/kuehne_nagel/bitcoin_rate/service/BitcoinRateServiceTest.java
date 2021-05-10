package com.kuehne_nagel.bitcoin_rate.service;

import com.kuehne_nagel.bitcoin_rate.client.BitcoinRateClient;
import com.kuehne_nagel.bitcoin_rate.dto.current_rate.CurrencyDto;
import com.kuehne_nagel.bitcoin_rate.dto.current_rate.CurrentRateResponseDto;
import com.kuehne_nagel.bitcoin_rate.dto.rate_history.RateHistoryResponseDto;
import com.kuehne_nagel.bitcoin_rate.enums.ErrorCode;
import com.kuehne_nagel.bitcoin_rate.exception.DefiningCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BitcoinRateServiceTest {

    @InjectMocks
    private BitcoinRateService bitcoinRateService;

    @Mock
    private BitcoinRateClient bitcoinRateClient;

    @Test
    public void getBitcoinRateDetailsGetCurrentBitcoinRateTest() throws DefiningCurrencyException {
        var expected = 1f;
        var mockParam = "TEST";
        var mockHistoryDto = new RateHistoryResponseDto();
        var mockBpi = new LinkedHashMap<String, Float>();
        mockBpi.put(mockParam, 1f);
        mockHistoryDto.setBpi(mockBpi);

        when(bitcoinRateClient.getBitcoinRateHistory(eq(mockParam), any(), any())).thenReturn(mockHistoryDto);

        var currentRateResponseDtoMock = new CurrentRateResponseDto();
        var mapMock = new HashMap<String, CurrencyDto>();
        var currencyDtoMock = new CurrencyDto();
        currencyDtoMock.setRate_float(1);
        mapMock.put(mockParam, currencyDtoMock);
        currentRateResponseDtoMock.setBpi(mapMock);

        when(bitcoinRateClient.getCurrentBitcoinRate(mockParam)).thenReturn(currentRateResponseDtoMock);

        var response = bitcoinRateService.getBitcoinRateDetails(mockParam);

        Assertions.assertEquals(expected, response.getCurrentBitcoinRate());
    }

    @Test
    public void getBitcoinRateDetailsWhenCurrencyValueIsEmptyNullThenShowErrorMessageTest() {
        var expected = ErrorCode.ERROR_EMPTY_CURRENCY.getErrorMessage();
        var mockParam = "";
        var response = bitcoinRateService.getBitcoinRateDetails(mockParam);

        Assertions.assertEquals(expected, response.getErrorMessage());
    }

    @Test
    public void getCurrentBitcoinRateWhenLowestValueIsNullThenShowErrorCodeMessageTest() throws DefiningCurrencyException {
        var expected = ErrorCode.ERROR_LOWEST_HIGHEST_WAS_NOT_DEFINED.getErrorMessage();

        var mockParam = "TEST";
        var mockHistoryDto = new RateHistoryResponseDto();

        when(bitcoinRateClient.getBitcoinRateHistory(eq(mockParam), any(), any())).thenReturn(mockHistoryDto);

        var currentRateResponseDtoMock = new CurrentRateResponseDto();
        var mapMock = new HashMap<String, CurrencyDto>();
        var currencyDtoMock = new CurrencyDto();
        currencyDtoMock.setRate_float(1);
        mapMock.put(mockParam, currencyDtoMock);
        currentRateResponseDtoMock.setBpi(mapMock);

        when(bitcoinRateClient.getCurrentBitcoinRate(mockParam)).thenReturn(currentRateResponseDtoMock);

        var response = bitcoinRateService.getBitcoinRateDetails(mockParam);

        Assertions.assertEquals(expected, response.getErrorMessage());

    }
}