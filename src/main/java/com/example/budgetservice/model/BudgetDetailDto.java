package com.example.budgetservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BudgetDetailDto {
    private String category;
    private int outlay = 0;
    private int amount = 0;
    private int lastMonthOutlay = 0;

    public BudgetDetailDto(String category) {
        this.category = category;
    }
}
