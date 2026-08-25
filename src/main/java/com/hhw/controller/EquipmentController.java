package com.hhw.controller;


import com.hhw.domain.dto.EquipmentDTO;
import com.hhw.domain.po.Equipment;
import com.hhw.domain.result.Result;
import com.hhw.service.IEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 实验室设备表 前端控制器
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final IEquipmentService equipmentService;

    /*
    * 查询所有设备
    *
    * */
    @GetMapping()
    public Result getAllEquipments(){
        return equipmentService.getAllEquipments();
    }

    /*
    * 根据id查询设备信息
    *
    * */
    @GetMapping("/{id}")
    public Result getEquipmentById(@PathVariable Long id){
        return equipmentService.getEquipmentById(id);
    }

    /*
    * 新增设备
    *
    * */
    @PostMapping()
    public Result addEquipment(@RequestBody EquipmentDTO dto){
        return equipmentService.addEquipment(dto);
    }

    /*
    * 修改设备
    *
    * */
    @PutMapping()
    public Result updateEquipment(@RequestBody EquipmentDTO dto){
        return equipmentService.updateEquipment(dto);
    }

    /*
    * 删除设备
    *
    * */
    @DeleteMapping("/{id}")
    public Result deleteEquipment(@PathVariable Long id){
        return equipmentService.deleteEquipment(id);
    }
}
