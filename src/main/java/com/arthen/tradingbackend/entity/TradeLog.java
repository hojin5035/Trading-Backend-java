package com.arthen.tradingbackend.entity; // 호진님 실제 패키지명에 맞췄습니다.

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade_logs")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String symbol;
    private String type; // BUY or SELL
    private Double price;
    private Double profitRate;
    private String reason;
}