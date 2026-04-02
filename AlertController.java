package com.solar.monitor.controller;

import com.solar.monitor.dto.AlertLog;
import com.solar.monitor.mapper.SolarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final SolarMapper solarMapper;

    // 8단계: 기록 조회 페이지 내역 조회 API
    @GetMapping("/history")
    public List<AlertLog> getAlertHistory() {
        return solarMapper.findAllAlertLogs();
    }
}
