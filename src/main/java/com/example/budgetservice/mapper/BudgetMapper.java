package com.example.budgetservice.mapper;

import com.example.budgetservice.form.BudgetCreateForm;
import com.example.budgetservice.form.BudgetUpdateForm;
import com.example.budgetservice.model.BudgetDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface BudgetMapper {
	long createBudget(BudgetCreateForm budgetCreateForm);
	Optional<BudgetDto> findByUserId(long userId);
    long updateBudget(BudgetUpdateForm budgetUpdateForm);
	long deleteBudget(long userId);
}
