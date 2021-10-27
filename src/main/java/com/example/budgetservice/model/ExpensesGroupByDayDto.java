package com.example.budgetservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesGroupByDayDto {

    private int day;
    private int totalAmount;
    private List<ExpenseResponseDto> expenseResponses;

    public void addTotalAmount(int addValue) {
        this.totalAmount += addValue;
    }
}
