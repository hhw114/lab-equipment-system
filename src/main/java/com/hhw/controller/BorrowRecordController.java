package com.hhw.controller;


import com.hhw.domain.dto.BorrowRecordDTO;
import com.hhw.domain.po.BorrowRecord;
import com.hhw.domain.result.Result;
import com.hhw.service.IBorrowRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 设备借用记录表 前端控制器
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@RestController
@RequestMapping("/borrows")
@RequiredArgsConstructor
public class BorrowRecordController {

    private final IBorrowRecordService borrowService;

    /*
    * 查询借阅记录
    *
    * */
    @GetMapping()
    public Result queryBorrowRecords(){
        List<BorrowRecord> list = borrowService.queryBorrowRecords();
        return Result.ok(list);
    }

    /*
    * 根据id查询借阅记录
    *
    * */
    @GetMapping("/{id}")
    public Result queryBorrowRecordById(@PathVariable Long id){
        BorrowRecord record = borrowService.queryBorrowRecordById(id);
        return Result.ok(record);
    }
    /*
    * 提交借用申请
    *
    * */
    @PostMapping()
    public Result addBorrowRecord(@RequestBody BorrowRecordDTO dto){
        borrowService.addBorrowRecord(dto);
        return Result.ok();
    }

    /*
    * 通过借用申请
    *
    * */
    @PutMapping("/{id}/approve")
    public Result approveBorrow(@PathVariable Long id){
        borrowService.approveBorrow(id);
        return Result.ok();
    }
    /*
    * 拒绝借用申请
    *
    * */
    @PutMapping("/{id}/reject")
    public Result rejectBorrow(@PathVariable Long id){
        borrowService.rejectBorrow(id);
        return Result.ok();
    }
    /*
    * 归还设备
    *
    * */
    @PutMapping("/{id}/return")
    public Result returnEquipment(@PathVariable Long id){
        borrowService.returnEquipment(id);
        return Result.ok();
    }

}
