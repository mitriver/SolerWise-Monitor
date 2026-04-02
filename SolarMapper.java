package com.solar.monitor.mapper;

import com.solar.monitor.dto.AlertDTO;
import com.solar.monitor.dto.AlertLog;
import com.solar.monitor.dto.GenerationLog;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SolarMapper {

    // 1. 실시간 발전 효율 조회
    Double getCurrentEfficiency();

    // 2. 장애 로그 저장
    int insertAlertLog(AlertDTO alert);

    // 3. 마지막 INSERT ID 조회
    int selectLastLogId();

    // 4. 모든 장애 로그 조회
    List<AlertLog> findAllAlertLogs();

    // 5. 대기 중인 발전 로그 조회
    List<GenerationLog> findPendingLogs();

    // 6. 로그 상태 업데이트 (파라미터 2개이므로 XML에서 순서 주의)
    void updateLogStatus(Long id, String status);

    // 7. 인버터 ID로 설치 위치 조회
    String findLocationByInverterId(Long inverterId);
}