package com.hhw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hhw.domain.po.Equipment;
import com.hhw.domain.po.RepairRecord;
import com.hhw.domain.vo.BorrowTrendVO;
import com.hhw.domain.vo.DailyBorrowStat;
import com.hhw.domain.vo.OverViewVO;
import com.hhw.domain.vo.RepairStatisVO;
import com.hhw.mapper.BorrowRecordMapper;
import com.hhw.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisServiceImpl implements IStatisService {
    private final IUserService userService;

    private final IEquipmentService equipmentService;

    private final IEquipmentCategoryService categoryService;

    private final IRepairRecordService repairService;

    private final BorrowRecordMapper borrowRecordMapper;
    /*
    * 数据总览
    *
    * */
    @Override
    public OverViewVO overView() {
        //1.查询设备总数
        Long equipmentNum = equipmentService.count();
        //2.查询维修中设备数
        Long repairingNum = repairService.lambdaQuery().eq(RepairRecord::getStatus, "REPAIRING").count();
        //3.查询待维修设备数量
        Long waitingNum = repairService.lambdaQuery().eq(RepairRecord::getStatus, "PENDING").count();
        //4.查询分类数量
        Long categoryNum = categoryService.count();
        //5.查询用户数量
        Long userNum = userService.count();
        //6.封装VO
        OverViewVO vo = new OverViewVO();
        vo.setUserNum(userNum);
        vo.setEquipmentNum(equipmentNum);
        vo.setRepairingNum(repairingNum);
        vo.setCategoryNum(categoryNum);
        vo.setWaitRepairNum(waitingNum);
        return vo;
    }
    /*
    * 过去一周借用趋势
    *
    * */
    @Override
    public BorrowTrendVO borrowTrend() {
        // 1. 计算起始时间
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusDays(7);

        // 2. 查询数据库：按天分组统计
        List<DailyBorrowStat> stats = borrowRecordMapper.selectDailyTrend(
                startTime,
                endTime,
                Arrays.asList("APPROVED", "BORROWED", "RETURNED") // 只统计已通过/借出/归还的记录
        );

        // 3. 组装VO
        BorrowTrendVO vo = new BorrowTrendVO();
        List<String> dates = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        // 4. 补全缺失的日期（确保每天都有数据）
        Map<String, Integer> dateMap = stats.stream()
                .collect(Collectors.toMap(
                        DailyBorrowStat::getDate,
                        DailyBorrowStat::getCount
                ));

        LocalDate current = startTime.toLocalDate();
        LocalDate end = endTime.toLocalDate();
        while (!current.isAfter(end)) {
            String dateStr = current.toString();
            dates.add(dateStr);
            values.add(dateMap.getOrDefault(dateStr, 0)); // 没数据的补0
            current = current.plusDays(1);
        }

        vo.setDates(dates);
        vo.setValues(values);
        return vo;
    }
}
