package com.example.budgetservice.mapper;

import com.example.budgetservice.model.BudgetDto;
import org.apache.ibatis.annotations.Mapper;

import com.example.budgetservice.form.BudgetCreateForm;

@Mapper
public interface BudgetMapper {
	long createBudget(BudgetDto budgetDto);
	BudgetDto findByUserId(long user);
}
