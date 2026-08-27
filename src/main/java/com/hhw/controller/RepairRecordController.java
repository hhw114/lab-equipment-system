package com.hhw.controller;


import com.hhw.domain.dto.RepairRecordDTO;
import com.hhw.domain.po.RepairRecord;
import com.hhw.domain.result.Result;
import com.hhw.service.IRepairRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 设备维修记录表 前端控制器
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@RestController
@RequestMapping("/repairs")
@RequiredArgsConstructor
public class RepairRecordController {
    private final IRepairRecordService repairService;
    /*
    * 查询所有维修记录
    *
    * */
    @GetMapping()
    public Result getRepairRecords(){
        List<RepairRecord> list = repairService.getRepairRecords();
        return Result.ok(list);
    }

    /*
    * 根据id查询维修记录详情
    *
    * */
    @GetMapping("/{id}")
    public Result getRepairRecordById(@PathVariable Long id){
        RepairRecord r = repairService.getRepairRecordById(id);
        return Result.ok(r);
    }

    /*
    * 报修
    *
    * */
    @PostMapping()
    public Result applyRepair(@RequestBody RepairRecordDTO dto){
        repairService.applyRepair(dto);
        return Result.ok();
    }

    /*
    * 进行维修
    *
    * */
    @PutMapping("/{id}/repair")
    public Result doRepair(@PathVariable Long id){
        repairService.doRepair(id);
        return Result.ok();
    }
    /*
    * 完成维修
    *
    * */
    @PutMapping("/{id}/finish")
    public Result finishRepair(@PathVariable Long id){
        repairService.finishRepair(id);
        return Result.ok();
    }

    /*
    * 删除维修记录
    *
    * */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        repairService.deleteById(id);
        return Result.ok();
    }
}
