package com.solar.monitor.controller;

import com.solar.monitor.dto.ConfigDTO;
import com.solar.monitor.service.ConfigService;
import com.solar.monitor.service.FaultTriggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigService configService;
    private final FaultTriggerService faultTriggerService;

    @PostMapping("/update")
    public String updateConfig(@RequestBody ConfigDTO config) {
        // 1. 설정값 업데이트
        configService.setInterval(config.getCollectionInterval());
        configService.setThreshold(config.getFaultThreshold());

        // 2. 스케줄러 재시작 (주기 반영)
        faultTriggerService.resetScheduler();

        return "설정이 성공적으로 변경되었습니다.";
    }
}
