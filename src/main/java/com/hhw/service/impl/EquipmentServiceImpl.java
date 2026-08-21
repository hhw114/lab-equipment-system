package com.hhw.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hhw.domain.po.Equipment;
import com.hhw.mapper.EquipmentMapper;
import com.hhw.service.IEquipmentService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 实验室设备表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {

}
