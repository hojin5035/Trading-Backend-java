package com.arthen.tradingbackend.service;

import com.arthen.tradingbackend.entity.TradeLog;
import com.arthen.tradingbackend.repository.TradeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeLogService {

    private final TradeLogRepository tradeLogRepository;
    // 날짜 형식을 메서드 밖으로 빼서 공통으로 사용합니다.
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 5분(300,000ms)마다 자동으로 실행되는 스케줄러입니다.
    @Scheduled(fixedRate = 300000)
    public void scheduledImport() {
        System.out.println("--- 주기적 데이터 체크 및 이관 시작 ---");
        importCsvToDb("data/trade_log.csv");
    }

    public void importCsvToDb(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 헤더(첫 줄) 스킵

            List<TradeLog> newLogs = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                LocalDateTime time = LocalDateTime.parse(data[0], formatter);
                String symbol = data[1];

                // DB에 해당 시간과 심볼이 없는 경우에만 리스트에 추가
                if (!tradeLogRepository.existsByTimestampAndSymbol(time, symbol)) {
                    TradeLog log = TradeLog.builder()
                            .timestamp(time)
                            .symbol(symbol)
                            .type(data[2])
                            .price(Double.parseDouble(data[3]))
                            .profitRate(Double.parseDouble(data[4]))
                            .reason(data[5])
                            .build();
                    newLogs.add(log);
                }
            }

            // 새로 추가될 데이터가 있을 때만 저장 실행
            if (!newLogs.isEmpty()) {
                tradeLogRepository.saveAll(newLogs);
                System.out.println(newLogs.size() + "개의 새로운 로그가 추가되었습니다.");
            } else {
                System.out.println("새로 추가할 데이터가 없습니다.");
            }

        } catch (Exception e) {
            System.err.println("CSV 읽기 중 오류 발생: " + e.getMessage());
        }
    }
}