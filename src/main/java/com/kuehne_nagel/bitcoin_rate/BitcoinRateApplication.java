package com.kuehne_nagel.bitcoin_rate;

import com.kuehne_nagel.bitcoin_rate.service.BitcoinRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class BitcoinRateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitcoinRateApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(BitcoinRateService bitcoinRateService) {
        return args -> {
            System.out.println("Type from which currency you want to get bitcoin rate:");
            Scanner scanner = new Scanner(System.in);
            String currency = scanner.nextLine().trim();
            System.out.println(bitcoinRateService.getBitcoinRateDetails(currency));
        };
    }
}