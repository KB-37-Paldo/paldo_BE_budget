package com.example.budgetservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDto {
    private long expenseId;
    private int amount;
    private String category;
    private String paymentMethod;
    private String outlayDatetime;
    private String source;
}
