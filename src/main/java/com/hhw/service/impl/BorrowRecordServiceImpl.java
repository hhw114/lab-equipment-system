package com.hhw.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hhw.domain.dto.BorrowRecordDTO;
import com.hhw.domain.po.BorrowRecord;
import com.hhw.domain.result.Result;
import com.hhw.exception.BizException;
import com.hhw.mapper.BorrowRecordMapper;
import com.hhw.service.IBorrowRecordService;

import com.hhw.service.IEquipmentService;
import com.hhw.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 设备借用记录表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Service
@RequiredArgsConstructor
public class BorrowRecordServiceImpl extends ServiceImpl<BorrowRecordMapper, BorrowRecord> implements IBorrowRecordService {
    private final IEquipmentService equipmentService;
    /*
    * 查询借用记录
    *
    * */
    @Override
    public List<BorrowRecord> queryBorrowRecords() {
        return list();
    }
    /*
    * 根据id查询借用记录
    *
    * */
    @Override
    public BorrowRecord queryBorrowRecordById(Long id) {
        BorrowRecord record = getById(id);
        return record;
    }
    /*
    * 提交借用申请
    *
    * */
    @Override
    public void addBorrowRecord(BorrowRecordDTO dto) {
        //1.初始化po对象
        BorrowRecord record = new BorrowRecord();
        //2.填充数据
        record.setEquipmentId(dto.getEquipmentId());
        record.setUserId(UserContext.getUserId());
        record.setApplyTime(LocalDateTime.now());
        record.setStatus("PENDING");
        record.setRemark(dto.getRemark());
        //3.判断设备是否存在
        Long equipmentId = dto.getEquipmentId();
        if (equipmentId == null){
            throw new BizException("借阅设备id不得为空");
        }
        Result equipmentById = equipmentService.getEquipmentById(equipmentId);
        if (equipmentById.getData() == null){
            throw new BizException("要借阅的设备不存在");
        }
        //4.写入数据库
        boolean success = save(record);
        if (!success){
            throw new BizException("提交借用申请失败");
        }
    }
    /*
    * 批准借用请求
    *
    * */
    @Override
    public void approveBorrow(Long id) {
        //1.查询原记录
        BorrowRecord record = getById(id);
        if (record == null){
            throw new BizException("借用记录不存在");
        }
        //2.只有请求处于pending才能批准
        if (!record.getStatus().equals("PENDING")){
            throw new BizException("当前设备请求不可被批准");
        }
        //3.判断被借用设备是否已经被借出
        BorrowRecord borrowedRecord =
                baseMapper.selectBorrowedRecordByEquipmentId(record.getEquipmentId());
        if (borrowedRecord != null) {
            throw new BizException("该设备当前已被借出");
        }

        //4.写入数据库
        record.setStatus("BORROWED");
        record.setAuditTime(LocalDateTime.now());
        record.setBorrowTime(LocalDateTime.now());
        boolean success = updateById(record);
        if (!success){
            throw new BizException("批准借阅请求失败");
        }
    }
    /*
    * 拒绝借用请求
    *
    * */
    @Override
    public void rejectBorrow(Long id) {
        //1.查询原记录
        BorrowRecord record = getById(id);
        if (record == null){
            throw new BizException("借用记录不存在");
        }
        //2.判断是否处于可批准状态
        if (!record.getStatus().equals("PENDING")){
            throw new BizException("当前设备请求不可被拒绝");
        }
        record.setStatus("REJECTED");
        record.setAuditTime(LocalDateTime.now());
        boolean success = updateById(record);
        if (!success){
            throw new BizException("拒绝借用请求失败");
        }
    }
    /*
    * 归还设备
    *
    * */
    @Override
    public void returnEquipment(Long id) {
        //1.查询原记录
        BorrowRecord record = getById(id);
        if (record == null){
            throw new BizException("借用记录不存在");
        }
        //2.判断归还者和借用是否为同一人
        Long userId = UserContext.getUserId();
        if (!userId.equals(record.getUserId())){
            throw new BizException("不能归还非自己借用的设备");
        }
        //3.判断设备是否处于借出状态
        if (!record.getStatus().equals("BORROWED")){
            throw new BizException("当前设备不能归还");
        }
        //4.归还设备
        record.setStatus("RETURNED");
        record.setReturnTime(LocalDateTime.now());
        boolean success = updateById(record);
        if (!success){
            throw new BizException("归还设备失败");
        }
    }
}
