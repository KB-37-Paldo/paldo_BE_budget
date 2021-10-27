package com.example.budgetservice.model;

import com.example.budgetservice.response.ExpenseResponse;
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
    private List<ExpenseResponse> expenseResponses;

    public void addTotalAmount(int addValue) {
        this.totalAmount += addValue;
    }
}
