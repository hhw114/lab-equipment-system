package com.hhw.mapper;

import com.hhw.domain.po.BorrowRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 设备借用记录表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
public interface BorrowRecordMapper extends BaseMapper<BorrowRecord> {

    BorrowRecord selectBorrowedRecordByEquipmentId(Long equipmentId);
}
