package com.hhw.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hhw.domain.dto.RepairRecordDTO;
import com.hhw.domain.po.Equipment;
import com.hhw.domain.po.RepairRecord;
import com.hhw.exception.BizException;
import com.hhw.mapper.RepairRecordMapper;
import com.hhw.service.IEquipmentService;
import com.hhw.service.IRepairRecordService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 设备维修记录表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Service
@RequiredArgsConstructor
public class RepairRecordServiceImpl extends ServiceImpl<RepairRecordMapper, RepairRecord> implements IRepairRecordService {
    private final IEquipmentService  equipmentService;
    /*
    * 查询所有维修记录
    *
    * */
    @Override
    public List<RepairRecord> getRepairRecords() {
        return list();
    }
    /*
    * 根据id查询维修记录详情
    *
    * */
    @Override
    public RepairRecord getRepairRecordById(Long id) {
        RepairRecord record = getById(id);
        return record;
    }
    /*
    * 报修
    *
    * */
    @Override
    public void applyRepair(RepairRecordDTO dto) {
        //1.创建po
        RepairRecord record = new RepairRecord();
        //2.填充数据
        if (dto.getEquipmentId() == null){
            throw new BizException("设备id不能为空");
        }
        if (dto.getDescription() == null){
            throw new BizException("描述信息不能为空");
        }
        record.setEquipmentId(dto.getEquipmentId());
        record.setDescription(dto.getDescription());
        record.setRepairTime(LocalDateTime.now());
        record.setStatus("PENDING");
        //3.判断维修记录表里是否已有该设备记录
        RepairRecord ishad = lambdaQuery().eq(RepairRecord::getEquipmentId, dto.getEquipmentId())
                .in(RepairRecord::getStatus,"PENDING","REPAIRING").one();
        if (ishad != null){
            throw new BizException("该设备已经保修或在维修中");
        }
        //4.判断设备是否存在
        Equipment equipment = equipmentService.getById(dto.getEquipmentId());
        if (equipment == null){
            throw new BizException("设备不存在");
        }
        //5.插入记录
        boolean success = save(record);
        if (!success){
            throw new BizException("保修失败");
        }
    }
    /*
    * 进行维修
    *
    * */
    @Override
    public void doRepair(Long id) {
        //1.查询数据库
        RepairRecord record = getById(id);
        if (record == null){
            throw new BizException("维修记录不存在");
        }
        String status = record.getStatus();
        if (!status.equals("PENDING")){
            throw new BizException("设备正在维修或维修已完成");
        }
        //2.设置状态
        record.setStatus("REPAIRING");
        //3.写入数据库
        boolean success = updateById(record);
        if (!success){
            throw new BizException("维修失败");
        }
    }
    /*
    * 完成维修
    *
    * */
    @Override
    public void finishRepair(Long id) {
        //1.查询数据库
        RepairRecord record = getById(id);
        if (record == null){
            throw new BizException("维修记录不存在");
        }
        String status = record.getStatus();
        if (!status.equals("REPAIRING")){
            throw new BizException("设备待维修或维修已完成");
        }
        //2.设置状态
        record.setStatus("FINISHED");
        record.setFinishTime(LocalDateTime.now());
        //TODO 传入维修结果
        record.setResult("维修成功");
        //3.写入数据库
        boolean success = updateById(record);
        if (!success){
            throw new BizException("维修失败");
        }
    }
    /*
    * 删除维修记录
    *
    * */
    @Override
    public void deleteById(Long id) {
        //1.查询数据库
        RepairRecord record = getById(id);
        if (record == null){
            throw new BizException("维修记录不存在");
        }
        //2.删除记录
        boolean success = removeById(id);
        if (!success){
            throw new BizException("删除维修记录失败");
        }
    }


}
