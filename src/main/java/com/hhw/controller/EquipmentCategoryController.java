package com.hhw.controller;


import com.hhw.domain.dto.EquipmentCategoryDTO;
import com.hhw.domain.result.Result;
import com.hhw.service.IEquipmentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 设备分类表 前端控制器
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class EquipmentCategoryController {

    private final IEquipmentCategoryService categoryService;
    /*
    * 查询分类信息
    *
    * */
    @GetMapping("/{id}")
    public Result getCategoryInfo(@PathVariable Long id){
        return categoryService.getCategoryInfo(id);
    }

    /*
    * 查询所有分类
    *
    * */
    @GetMapping()
    public Result getAllCategoryInfo(){
        return categoryService.getAllCategoryInfo();
    }

    /*
    * 新增分类
    *
    * */
    @PostMapping()
    public Result addCategory(@RequestBody EquipmentCategoryDTO equipmentCategoryDTO){
        return categoryService.addCategory(equipmentCategoryDTO);
    }
    /*
    * 修改分类
    *
    * */
    @PutMapping()
    public Result updateCategory(@RequestBody EquipmentCategoryDTO equipmentCategoryDTO){
        return categoryService.updateCategory(equipmentCategoryDTO);
    }

    /*
    * 删除分类
    *
    * */
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
