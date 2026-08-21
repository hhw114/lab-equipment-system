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
 * 设备维修记录表
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("repair_record")
public class RepairRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 维修记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
