package com.example.budgetservice.service;

import com.example.budgetservice.model.ExpenseResponseDto;

import java.util.List;

public interface ExpenseService {

    List<ExpenseResponseDto> getUserExpenses(long userId);
}
