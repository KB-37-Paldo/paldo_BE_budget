package com.example.budgetservice.model;

import com.example.budgetservice.form.ExpenseCreateForm;
import lombok.*;

import java.util.Date;

@Getter
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

    public ExpenseDto(long userId, ExpenseCreateForm createForm) {
        this.amount = createForm.getAmount();
        this.category = createForm.getCategory();
        this.paymentMethod = createForm.getPaymentMethod();
        this.outlayDatetime = (new Date()).toString();
        this.source = createForm.getSource();
        this.userId = userId;
    }
}
