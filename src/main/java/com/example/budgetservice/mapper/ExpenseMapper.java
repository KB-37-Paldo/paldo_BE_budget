package com.example.budgetservice.mapper;

import com.example.budgetservice.model.ExpenseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpenseMapper {
    List<ExpenseDto> findByUserId(long userId);
    Long deleteById(long expenseId);
    Long create(ExpenseDto expenseDto);
}
