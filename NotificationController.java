package com.solar.monitor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    // 연결된 클라이언트 리스트 관리
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    // 브라우저에서 구독(Subscribe) 요청 시 호출
    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        // 1시간 동안 연결 유지 설정
        SseEmitter emitter = new SseEmitter(60L * 1000 * 60);

        this.emitters.add(emitter);

        // 연결 종료나 타임아웃 시 리스트에서 제거
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));

        // 연결 성공 시 더미 데이터 전송 (연결 확인용)
        try {
            emitter.send(SseEmitter.event().name("connect").data("SSE Connected!"));
        } catch (IOException e) {
            this.emitters.remove(emitter);
        }

        return emitter;
    }

    // 서비스 계층에서 호출하여 실제 알림 전송
    public void sendAlert(Object data) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().name("alert").data(data));
            } catch (IOException e) {
                this.emitters.remove(emitter);
            }
        }
    }
}