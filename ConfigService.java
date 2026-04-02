package com.solar.monitor.service;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    // 기본값 설정 (주기: 1시간, 기준: 70%)
    private long currentInterval = 3600000L;
    private double currentThreshold = 70.0;

    public long getInterval() { return currentInterval; }
    public void setInterval(long interval) { this.currentInterval = interval; }

    public double getThreshold() { return currentThreshold; }
    public void setThreshold(double threshold) { this.currentThreshold = threshold; }
}
