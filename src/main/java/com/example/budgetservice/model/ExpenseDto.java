package com.example.budgetservice.model;

import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.form.ExpenseUpdateForm;
import lombok.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
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

    public ExpenseDto(long userId, String yearMonth, ExpenseCreateForm createForm) {
        Date currentDatetime = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format.format(currentDatetime);

        format = new SimpleDateFormat("HH:mm:ss");
        String currentTime = format.format(currentDatetime);

        this.amount = createForm.getAmount();
        this.category = createForm.getCategory();
        this.paymentMethod = createForm.getPaymentMethod();
        this.outlayDatetime = yearMonth;
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
