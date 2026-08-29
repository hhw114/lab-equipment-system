package com.hhw.controller;

import com.hhw.domain.result.Result;
import com.hhw.domain.vo.BorrowTrendVO;
import com.hhw.domain.vo.OverViewVO;
import com.hhw.domain.vo.RepairStatisVO;
import com.hhw.service.IStatisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisController {

    private final IStatisService statisService;
    /*
    * 信息总览
    *
    * */
    @GetMapping("/overview")
    public Result overView(){
        OverViewVO vo = statisService.overView();
        return Result.ok(vo);
    }

    /*
    * 借用趋势
    *
    * */
    @GetMapping("/borrow-trend")
    public Result borrowTrend(){
        BorrowTrendVO vo = statisService.borrowTrend();
        return Result.ok(vo);
    }



}
