package com.example.budgetservice.mapper;

import com.example.budgetservice.model.BudgetDto;
import org.apache.ibatis.annotations.Mapper;

import com.example.budgetservice.form.BudgetCreateForm;

import java.util.Optional;

@Mapper
public interface BudgetMapper {
	long createBudget(BudgetDto budgetDto);
	Optional<BudgetDto> findByUserId(long user);
}
