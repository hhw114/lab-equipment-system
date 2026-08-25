package com.hhw.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.hhw.domain.dto.EquipmentCategoryDTO;
import com.hhw.domain.po.EquipmentCategory;
import com.hhw.domain.result.Result;


/**
 * <p>
 * 设备分类表 服务类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
public interface IEquipmentCategoryService extends IService<EquipmentCategory> {

    Result getCategoryInfo(Long id);

    Result getAllCategoryInfo();

    Result addCategory(EquipmentCategoryDTO equipmentCategoryDTO);

    Result updateCategory(EquipmentCategoryDTO equipmentCategoryDTO);

    Result deleteCategory(Long id);
}
