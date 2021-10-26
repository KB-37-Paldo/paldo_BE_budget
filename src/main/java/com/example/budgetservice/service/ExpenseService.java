package com.example.budgetservice.service;

import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.form.ExpenseUpdateForm;
import com.example.budgetservice.model.ExpenseResponseDto;
import com.example.budgetservice.model.SortedExpensesDto;

import java.util.List;

public interface ExpenseService {

    List<SortedExpensesDto> getUserExpenses(long userId, String yearMonth);
    List<ExpenseResponseDto> getUserExpensesByCatgory(long userId, String category, String yearMonth);
    Long deleteExpense(long expenseId);
    Long createExpense(long userId, ExpenseCreateForm createForm);
    Long updateExpense(long expenseId, ExpenseUpdateForm updateForm);
}
