package com.example.budgetservice.service;

import com.example.budgetservice.form.BudgetCreateForm;
import com.example.budgetservice.form.BudgetUpdateForm;
import com.example.budgetservice.response.BudgetResponse;

public interface BudgetService {
	 //예산 생성
    long createBudget(BudgetCreateForm budgetCreateForm);
    BudgetResponse findByUserID(long userId, String requestDate);
    long updateBudget(BudgetUpdateForm budgetUpdateForm);
}
