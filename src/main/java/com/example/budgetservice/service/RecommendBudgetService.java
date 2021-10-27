package com.example.budgetservice.service;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.budgetservice.model.BudgetResponse;

public interface RecommendBudgetService {
	// 추천예산 조회
	BudgetResponse findByUserID(long userId) throws IOException, ParseException;
}
