package com.example.budgetservice.model;

import com.example.budgetservice.response.ExpenseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesGroupByCategoryDto {
    private int totalAmount;
    private List<ExpenseResponse> expenseResponses;
}
