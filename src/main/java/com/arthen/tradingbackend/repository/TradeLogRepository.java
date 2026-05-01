package com.arthen.tradingbackend.repository;

import com.arthen.tradingbackend.entity.TradeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TradeLogRepository extends JpaRepository<TradeLog, Long> {
    // 스프링 JPA가 메서드 이름을 분석해서 자동으로 중복 체크 쿼리를 만들어줍니다!
    boolean existsByTimestampAndSymbol(LocalDateTime timestamp, String symbol);
}