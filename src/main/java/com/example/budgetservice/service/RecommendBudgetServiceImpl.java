package com.example.budgetservice.service;

import com.example.budgetservice.model.BudgetResponse;
import org.springframework.stereotype.Service;

@Service
public class RecommendBudgetServiceImpl implements RecommendBudgetService{
	
	@Override
	public BudgetResponse findByUserID(long userId) {
		BudgetResponse budgetResponse =  new BudgetResponse();

		
		return budgetResponse;		
	}
}
