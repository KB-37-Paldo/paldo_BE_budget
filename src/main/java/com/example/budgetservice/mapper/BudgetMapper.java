package com.example.budgetservice.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.budgetservice.form.BudgetCreateForm;

@Mapper
public interface BudgetMapper {
	long createBudget(BudgetCreateForm budgetCreateForm);
}
