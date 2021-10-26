package com.example.budgetservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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
