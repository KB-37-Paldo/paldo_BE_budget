package com.example.budgetservice.service;


import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.budgetservice.response.RecommendResponse;

public interface RecommendBudgetService {
	// 추천예산 조회
	RecommendResponse findByUserID(long userId) throws IOException, ParseException;
}
