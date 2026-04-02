package com.solar.monitor.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertDTO {
    private String logId;
    private String detectTime;
    private String regionCode;
    private double efficiency;
    @Builder.Default
    private String status = "미확인";
    private String message;

    public void setLocation(String 대전_본사_발전소) {

    }
}