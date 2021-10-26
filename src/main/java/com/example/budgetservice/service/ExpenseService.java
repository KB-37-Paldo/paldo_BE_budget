package com.example.budgetservice.service;

import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.form.ExpenseUpdateForm;
import com.example.budgetservice.model.ExpenseResponseDto;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    List<ExpenseResponseDto> getUserExpenses(long userId, String yearMonth);
    Long deleteExpense(long expenseId);
    Long createExpense(long userId, ExpenseCreateForm createForm);
    Long updateExpense(long expenseId, ExpenseUpdateForm updateForm);
}
