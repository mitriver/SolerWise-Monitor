package com.solar.monitor.service;

import com.solar.monitor.dto.AlertDTO;
import com.solar.monitor.controller.NotificationController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationController notificationController;

    /**
     * 장애 알림을 처리하고 브라우저로 전송합니다.
     * 나중에 알림 이력을 DB에 저장하는 로직을 여기에 추가하면 됩니다.
     */
    public void sendFaultNotification(AlertDTO alert) {
        // 1. (선택사항) 콘솔에 알림 발생 로그 출력
        System.out.println("[알림 서비스] 장애 발생 전송 시작 - ID: " + alert.getLogId());

        // 2. 컨트롤러를 호출하여 실시간 SSE 전송 수행
        notificationController.sendAlert(alert);

        // 3. (참고) 만약 알림 이력 테이블이 있다면 여기서 mapper.insert() 호출 가능
    }
}
