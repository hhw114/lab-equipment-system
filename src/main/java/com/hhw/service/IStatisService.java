package com.hhw.service;

import com.hhw.domain.vo.BorrowTrendVO;
import com.hhw.domain.vo.OverViewVO;
import com.hhw.domain.vo.RepairStatisVO;

public interface IStatisService {
    OverViewVO overView();

    BorrowTrendVO borrowTrend();

}
