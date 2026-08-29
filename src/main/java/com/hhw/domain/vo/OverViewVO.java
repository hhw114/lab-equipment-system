package com.hhw.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OverViewVO {
    //设备数量
    private Long equipmentNum;
    //维修中设备数量
    private Long repairingNum;
    //待维修设备数量
    private Long waitRepairNum;
    //分类数量
    private Long categoryNum;
    //用户数量
    private Long userNum;

}
