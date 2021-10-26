package com.example.budgetservice.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCreateForm {
    private int amount;
    private String category;
    private String paymentMethod;
    private String source;
}
