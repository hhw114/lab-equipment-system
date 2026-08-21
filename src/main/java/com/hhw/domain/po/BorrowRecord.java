package com.hhw.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设备借用记录表
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("borrow_record")
public class BorrowRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 借用记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
