package com.example.budgetservice.service;

import com.example.budgetservice.response.BudgetResponse;

public interface BudgetService {
	 //예산 생성
    long createBudget(long userId);
    BudgetResponse findByUserID(long userId, String requestDate);
}
