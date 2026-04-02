package com.solar.monitor.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GenerationLog {
    private Long id;                // 기록 ID
    private Long inverterId;        // 인버터 ID
    private LocalDateTime baseTime; // 기준 시각
    private BigDecimal performanceRatio; // 효율 (실측/기대)
    private String status;          // 상태 (PENDING, NORMAL, ERROR)
}