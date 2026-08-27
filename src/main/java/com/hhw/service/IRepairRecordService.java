package com.hhw.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.hhw.domain.dto.RepairRecordDTO;
import com.hhw.domain.po.RepairRecord;

import java.util.List;


/**
 * <p>
 * 设备维修记录表 服务类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
public interface IRepairRecordService extends IService<RepairRecord> {

    List<RepairRecord> getRepairRecords();

    RepairRecord getRepairRecordById(Long id);

    void applyRepair(RepairRecordDTO dto);

    void doRepair(Long id);

    void finishRepair(Long id);

    void deleteById(Long id);
}
