package com.hhw.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hhw.domain.po.RepairRecord;
import com.hhw.mapper.RepairRecordMapper;
import com.hhw.service.IRepairRecordService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备维修记录表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Service
public class RepairRecordServiceImpl extends ServiceImpl<RepairRecordMapper, RepairRecord> implements IRepairRecordService {

}
