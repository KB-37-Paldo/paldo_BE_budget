package com.example.budgetservice.service;

import com.example.budgetservice.response.BudgetResponse;

public interface RecommendBudgetService {
	// 추천예산 조회
	BudgetResponse findByUserID(long userId);
}
