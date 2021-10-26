package com.example.budgetservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private long historyId;
    private int amount;
    private String category;
    private String paymentMethod;
    private String outlayDatetime;
    private String source;
    private long userId;

    public ExpenseResponseDto getExpenseResponse() {
        return new ExpenseResponseDto(this.historyId, this.amount, this.category,
                this.paymentMethod, this.outlayDatetime, this.source);
    }
}
