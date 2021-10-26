package com.example.budgetservice.mapper;

import com.example.budgetservice.model.ExpenseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpenseMapper {
    List<ExpenseDto> findByUserIdAndYearMonth(@Param("userId") long userId,@Param("yearMonth") String yearMonth);
    Long deleteById(long expenseId);
    Long create(ExpenseDto expenseDto);
    Long update(ExpenseDto expenseDto);
}
