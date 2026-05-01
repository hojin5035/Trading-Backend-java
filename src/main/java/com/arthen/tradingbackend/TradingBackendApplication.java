package com.arthen.tradingbackend;

import com.arthen.tradingbackend.service.TradeLogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TradingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingBackendApplication.class, args);
    }

    // 서버 시작 시 실행될 로직
    @Bean
    CommandLineRunner run(TradeLogService tradeLogService) {
        return args -> {
            // data 폴더 안의 trade_log.csv 경로를 적어줍니다.
            tradeLogService.importCsvToDb("data/trade_log.csv");
        };
    }

}
