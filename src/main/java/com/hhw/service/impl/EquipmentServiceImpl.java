package com.hhw.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hhw.domain.dto.EquipmentDTO;
import com.hhw.domain.po.Equipment;
import com.hhw.domain.result.Result;
import com.hhw.exception.BizException;
import com.hhw.mapper.EquipmentMapper;
import com.hhw.service.IEquipmentCategoryService;
import com.hhw.service.IEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 实验室设备表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-19
 */
@RequiredArgsConstructor
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {

    private final IEquipmentCategoryService categoryService;
    /*
    * 获取全部设备信息
    *
    * */
    @Override
    public Result getAllEquipments() {
        List<Equipment> list = list();
        return Result.ok(list);
    }

    /*
    * 获取单个设备信息
    *
    * */
    @Override
    public Result getEquipmentById(Long id) {
        Equipment equipment = getById(id);
        return Result.ok(equipment);
    }

    /*
    * 新增设备
    *
    * */
    @Override
    public Result addEquipment(EquipmentDTO dto) {
        //1.新建po对象
        Equipment equipment = new Equipment();
        equipment.setName(dto.getName());
        equipment.setModel(dto.getModel());
        equipment.setLocation(dto.getLocation());
        equipment.setStatus(dto.getStatus());
        equipment.setPurchaseDate(dto.getPurchaseDate());
        equipment.setDescription(dto.getDescription());
        //2.检查分类是否存在
        Long categoryId = dto.getCategoryId();
        if (categoryId != null) {
            Result categoryInfo = categoryService.getCategoryInfo(categoryId);
            if (categoryInfo.getData() == null){
                //不存在，报错
                throw new BizException("分类不存在，新增设备失败");
            }
        }

        //分类存在或未设置分类
        equipment.setCategoryId(categoryId);
        //3.插入数据库
        boolean success = save(equipment);
        if (!success) {
            //3.1.插入失败
            throw new BizException("新增设备失败");
        }
        //3.2.插入成功
        return Result.ok();

    }

    /*
    * 更新设备
    *
    * */
    @Override
    public Result updateEquipment(EquipmentDTO dto) {
        //1.新建po对象
        Equipment equipment = new Equipment();
        if (dto.getId() == null) {
            throw new BizException("传入的设备id不能为空");
        }
        equipment.setId(dto.getId());
        equipment.setName(dto.getName());
        equipment.setModel(dto.getModel());
        equipment.setLocation(dto.getLocation());
        equipment.setStatus(dto.getStatus());
        equipment.setPurchaseDate(dto.getPurchaseDate());
        equipment.setDescription(dto.getDescription());
        //2.检查分类是否存在
        Long categoryId = dto.getCategoryId();
        if (categoryId != null) {
            Result categoryInfo = categoryService.getCategoryInfo(categoryId);
            if (categoryInfo.getData() == null){
                //不存在，报错
                throw new BizException("分类不存在，更新设备失败");
            }
        }
        //未修改分类或分类存在
        equipment.setCategoryId(categoryId);
        //3.写入数据库
        boolean success = updateById(equipment);
        if (!success) {
            throw new BizException("更新设备信息失败");
        }
        return Result.ok();
    }

    /*
    * 删除设备
    *
    * */
    @Override
    public Result deleteEquipment(Long id) {
        boolean success = removeById(id);
        if (!success) {
            throw new BizException("删除设备失败");
        }
        return Result.ok();
    }
}
