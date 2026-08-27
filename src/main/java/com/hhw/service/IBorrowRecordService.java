package com.hhw.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.hhw.domain.dto.BorrowRecordDTO;
import com.hhw.domain.po.BorrowRecord;

import java.util.List;


/**
 * <p>
 * 设备借用记录表 服务类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
public interface IBorrowRecordService extends IService<BorrowRecord> {

    List<BorrowRecord> queryBorrowRecords();

    BorrowRecord queryBorrowRecordById(Long id);

    void addBorrowRecord(BorrowRecordDTO dto);

    void approveBorrow(Long id);

    void rejectBorrow(Long id);

    void returnEquipment(Long id);
}
