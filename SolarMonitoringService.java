package com.solar.monitor.service;

import com.solar.monitor.dto.AlertDTO;
import com.solar.monitor.mapper.SolarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolarMonitoringService {

    private final SolarMapper solarMapper;
    private final NotificationService notificationService;

    // 5초마다 실행 (메인 클래스에 @EnableScheduling 필수)
    @Scheduled(fixedRate = 5000)
    public void checkFault() {
        // DB에서 최신 효율 조회 (DB명: mitriver)
        Double efficiency = solarMapper.getCurrentEfficiency();

        // 효율이 10% 미만일 때 장애로 판정
        if (efficiency != null && efficiency < 10.0) {

            // [체크!] 여기서 변수명을 'history'로 선언해야 아래에서 빨간 줄이 안 생깁니다.
            AlertDTO history = new AlertDTO();
            history.setLocation("대전 본사 발전소");
            history.setEfficiency(efficiency);
            history.setMessage("발전 효율 저하 감지! (" + efficiency + "%)");
            history.setStatus("미확인");

            // 1. DB에 장애 로그 저장 (매퍼 호출)
            // SolarMapper 인터페이스에도 insertAlertLog(AlertDTO history)가 있어야 함!
            solarMapper.insertAlertLog(history);

            // 2. 실시간 알림 전송 (서비스 호출)
            notificationService.sendFaultNotification(history);

            System.out.println(">>> [장애발생] history 데이터 전송 완료: " + efficiency + "%");
        }
    }
}