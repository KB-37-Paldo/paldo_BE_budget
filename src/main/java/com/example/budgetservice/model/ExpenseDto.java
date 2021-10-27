package com.example.budgetservice.model;

import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.form.ExpenseUpdateForm;
import lombok.*;

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
        this.outlayDatetime = createForm.getOutlayDatetime();
        this.source = createForm.getSource();
        this.userId = userId;
    }

    public ExpenseDto(long expenseId, ExpenseUpdateForm updateForm) {
        this.historyId = expenseId;
        this.amount = updateForm.getAmount();
        this.category = updateForm.getCategory();
        this.paymentMethod = updateForm.getPaymentMethod();
        this.outlayDatetime = updateForm.getOutlayDatetime();
        this.source = updateForm.getSource();
    }
}
