package com.hhw.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hhw.domain.po.BorrowRecord;
import com.hhw.mapper.BorrowRecordMapper;
import com.hhw.service.IBorrowRecordService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备借用记录表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Service
public class BorrowRecordServiceImpl extends ServiceImpl<BorrowRecordMapper, BorrowRecord> implements IBorrowRecordService {

}
