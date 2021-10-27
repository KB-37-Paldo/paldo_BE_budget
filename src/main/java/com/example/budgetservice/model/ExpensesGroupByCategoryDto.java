package com.example.budgetservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesGroupByCategoryDto {
    private int totalAmount;
    private List<ExpenseResponseDto> expenseResponses;
}
