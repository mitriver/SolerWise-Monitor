package com.solar.monitor.dto;

import lombok.Data;

@Data
public class ConfigDTO {
    private long collectionInterval; // 수집 주기 (ms 단위)
    private double faultThreshold;    // 장애 판단 기준 (%)
}
