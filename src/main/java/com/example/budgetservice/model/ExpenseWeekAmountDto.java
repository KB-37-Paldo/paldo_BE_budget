package com.example.budgetservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseWeekAmountDto implements Comparable<ExpenseWeekAmountDto>{
    private Integer week;
    private int totalAmount;

    public void addTotalAmount(int addValue) {
        this.totalAmount += addValue;
    }

    @Override
    public int compareTo(ExpenseWeekAmountDto o) {
        return this.week - o.week;
    }
}
