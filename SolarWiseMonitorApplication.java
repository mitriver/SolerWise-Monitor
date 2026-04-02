package com.solar.monitor;

import org.mybatis.spring.annotation.MapperScan; // 이 패키지 확인!
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.solar.monitor.mapper") // 매퍼가 있는 패키지 경로를 정확히 적어주세요.
public class SolarWiseMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SolarWiseMonitorApplication.class, args);
    }
}
