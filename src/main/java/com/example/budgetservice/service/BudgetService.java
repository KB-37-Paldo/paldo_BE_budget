package com.example.budgetservice.service;

import com.example.budgetservice.form.BudgetCreateForm;

public interface BudgetService {
	 //예산 생성
    long createBudget(BudgetCreateForm BudgetCreateForm);
    
}
