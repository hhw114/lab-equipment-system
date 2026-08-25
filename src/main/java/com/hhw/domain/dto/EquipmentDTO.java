package com.hhw.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EquipmentDTO {
    /**
     * 设备ID
     */
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
}
