package com.example.budgetservice.mapper;

import com.example.budgetservice.model.ExpenseDto;
import com.example.budgetservice.model.ExpenseResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpenseMapper {
    List<ExpenseDto> findByUserIdAndYearMonth(@Param("userId") long userId,@Param("yearMonth") String yearMonth);
    List<ExpenseDto> findByCategoryAndYearMonth(@Param("userId") long userId,
                                                        @Param("category") String category,
                                                        @Param("yearMonth") String yearMonth);
    Long deleteById(long expenseId);
    Long create(ExpenseDto expenseDto);
    Long update(ExpenseDto expenseDto);
    List<Map<String, Integer>> findByUserIdGroupByCategory(long userId, String lastMonthDate);
}
