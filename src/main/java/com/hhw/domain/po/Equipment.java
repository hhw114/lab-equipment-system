package com.hhw.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实验室设备表
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("equipment")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备分类ID
     */
    private Long categoryId;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 存放位置
     */
    private String location;

    /**
     * 设备状态：NORMAL正常，BORROWED借出，REPAIR维修中，SCRAPPED报废
     */
    private String status;

    /**
     * 购买日期
     */
    private LocalDate purchaseDate;

    /**
     * 设备描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
