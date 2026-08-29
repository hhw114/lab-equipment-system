package com.hhw.mapper;

import com.hhw.domain.po.BorrowRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhw.domain.vo.DailyBorrowStat;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 设备借用记录表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
public interface BorrowRecordMapper extends BaseMapper<BorrowRecord> {

    BorrowRecord selectBorrowedRecordByEquipmentId(Long equipmentId);

    /**
     * 按天统计借阅量
     */
    List<DailyBorrowStat> selectDailyTrend(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("statusList") List<String> statusList
    );
}
