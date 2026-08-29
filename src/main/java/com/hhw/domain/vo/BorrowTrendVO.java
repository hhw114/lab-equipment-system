package com.hhw.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class BorrowTrendVO {
    private List<String> dates;
    private List<Integer> values;
}
