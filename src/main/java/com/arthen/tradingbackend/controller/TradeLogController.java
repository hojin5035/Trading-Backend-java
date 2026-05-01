package com.arthen.tradingbackend.controller;

import com.arthen.tradingbackend.entity.TradeLog;
import com.arthen.tradingbackend.repository.TradeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // JSON 데이터를 주고받는 컨트롤러임을 선언
@RequestMapping("/api/logs") // 이 주소로 들어오는 요청을 처리함
@RequiredArgsConstructor
public class TradeLogController {

    private final TradeLogRepository tradeLogRepository;

    // 모든 매매 로그를 가져오는 API
    @GetMapping
    public List<TradeLog> getAllLogs() {
        return tradeLogRepository.findAll();
    }
}