package com.hhw.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.hhw.domain.dto.EquipmentDTO;
import com.hhw.domain.po.Equipment;
import com.hhw.domain.result.Result;


/**
 * <p>
 * 实验室设备表 服务类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
public interface IEquipmentService extends IService<Equipment> {

    Result getAllEquipments();

    Result getEquipmentById(Long id);

    Result addEquipment(EquipmentDTO dto);

    Result updateEquipment(EquipmentDTO dto);

    Result deleteEquipment(Long id);
}
