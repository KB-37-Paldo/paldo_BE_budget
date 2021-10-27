package com.example.budgetservice.service;

import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.form.ExpenseUpdateForm;
import com.example.budgetservice.model.ExpensesGroupByCategoryDto;
import com.example.budgetservice.model.ExpensesGroupByDayDto;

import java.util.List;

public interface ExpenseService {

    List<ExpensesGroupByDayDto> getUserExpensesByOutlayMonth(long userId, String outlayYearMonth);
    ExpensesGroupByCategoryDto getUserExpensesByCategory(long userId, String category, String outlayYearMonth);
    Long deleteExpense(long expenseId);
    Long createExpense(long userId, ExpenseCreateForm createForm);
    Long updateExpense(long expenseId, ExpenseUpdateForm updateForm);
}
