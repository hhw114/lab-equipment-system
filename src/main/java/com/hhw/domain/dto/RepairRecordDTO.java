package com.hhw.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairRecordDTO {
    /**
     * 设备ID
     */
    private Long equipmentId;

    /**
     * 故障描述
     */
    private String description;

    /**
     * 报修时间
     */
    private LocalDateTime repairTime;

    /**
     * 维修完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 维修状态：PENDING待维修，REPAIRING维修中，FINISHED已完成
     */
    private String status;

    /**
     * 维修结果
     */
    private String result;
}
