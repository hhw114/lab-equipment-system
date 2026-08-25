package com.hhw.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hhw.domain.dto.EquipmentCategoryDTO;
import com.hhw.domain.po.EquipmentCategory;
import com.hhw.domain.result.Result;
import com.hhw.exception.BizException;
import com.hhw.mapper.EquipmentCategoryMapper;
import com.hhw.service.IEquipmentCategoryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 设备分类表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@Service
public class EquipmentCategoryServiceImpl extends ServiceImpl<EquipmentCategoryMapper, EquipmentCategory> implements IEquipmentCategoryService {
    /*
    * 获取分类信息
    *
    * */
    @Override
    public Result getCategoryInfo(Long id) {
        EquipmentCategory category = getById(id);
        if (category != null) {
            //1.存在，返回
            return Result.ok(category);
        }
        //2.不存在，返回空
        return Result.ok();
    }
    /*
    * 查询分类列表
    *
    * */
    @Override
    public Result getAllCategoryInfo() {
        List<EquipmentCategory> list = list();
        if (list != null) {
            return Result.ok(list);
        }
        return Result.ok();
    }
    /*
    * 新增分类
    *
    * */
    @Override
    public Result addCategory(EquipmentCategoryDTO equipmentCategoryDTO) {
        //1.新建po类
        EquipmentCategory category = new EquipmentCategory();
        //2.设置属性
        category.setName(equipmentCategoryDTO.getName());
        category.setDescription(equipmentCategoryDTO.getDescription());
        boolean success = save(category);
        if(!success){
            //3.插入失败抛出异常
            throw new BizException("新增分类失败");
        }
        //4.成功，返回
        return Result.ok();
    }

    /*
     * 修改分类
     *
     * */
    @Transactional
    @Override
    public Result updateCategory(EquipmentCategoryDTO equipmentCategoryDTO) {
        Long id = equipmentCategoryDTO.getId();
        //1.检查分类id
        if (id == null) {
            throw new BizException("传入分类id不得为空");
        }
        //2.查询数据库中数据
        EquipmentCategory category = getById(id);
        if (category == null) {
            //3.1.数据库中不存在数据
            throw new BizException("数据库中不存在此分类");
        }
        //3.2.存在，更新
        category.setName(equipmentCategoryDTO.getName());
        category.setDescription(equipmentCategoryDTO.getDescription());
        category.setUpdateTime(LocalDateTime.now());
        boolean success = updateById(category);
        if(!success){
            throw new BizException("更新分类失败");
        }
        return Result.ok();
    }
    /*
    * 删除分类
    *
    * */
    @Transactional
    @Override
    public Result deleteCategory(Long id) {
        boolean success = removeById(id);
        if (!success){
            throw new BizException("删除失败");
        }
        return Result.ok();

        //TODO 删除分类前检查分类下挂的设备是否清理干净
    }
}
