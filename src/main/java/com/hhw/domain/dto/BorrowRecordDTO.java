package com.hhw.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BorrowRecordDTO {
    /**
     * 借用记录ID
     */
    private Long id;

    /**
     * 设备ID
     */
    private Long equipmentId;

    /**
     * 借用用户ID
     */
    private Long userId;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 实际借出时间
     */
    private LocalDateTime borrowTime;

    /**
     * 归还时间
     */
    private LocalDateTime returnTime;

    /**
     * 状态：PENDING待审核，APPROVED已通过，REJECTED已拒绝，BORROWED借出中，RETURNED已归还
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}
