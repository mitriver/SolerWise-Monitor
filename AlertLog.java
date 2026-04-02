package com.solar.monitor.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AlertLog {
    private Long alertId;      // 알림 고유 식별자
    private Long logId;        // 관련 발전 기록 ID
    private String location;   // 발전소 위치
    private double efficiency; // 당시 효율
    private String message;    // 알림 메시지
    private LocalDateTime detectTime; // 감지 시간
}
