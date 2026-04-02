package com.solar.monitor.service;

import com.solar.monitor.dto.AlertDTO;
import com.solar.monitor.mapper.SolarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaultTriggerService {

    private final SolarMapper solarMapper;
    private final NotificationService notificationService;

    // 5초마다 실행 (메인 클래스에 @EnableScheduling 필수)
    @Scheduled(fixedRate = 5000)
    public void checkFault() {
        // DB에서 최신 효율 데이터 조회 (DB: mitriver)
        Double efficiency = solarMapper.getCurrentEfficiency();

        // 효율이 10% 미만일 때 장애로 판정
        if (efficiency != null && efficiency < 10.0) {
            AlertDTO alert = new AlertDTO();
            alert.setLocation("대전 본사 발전소");
            alert.setEfficiency(efficiency);
            alert.setMessage("태양광 발전 효율 저하 감지! (" + efficiency + "%)");

            // 1. DB에 장애 로그 저장
            solarMapper.insertAlertLog(alert);

            // 2. 실시간 알림 서비스 호출
            notificationService.sendFaultNotification(alert);

            System.out.println(">>> 장애 감지 및 알림 발송 완료: " + efficiency + "%");
        }
    }
    public void resetScheduler() {
    }
}