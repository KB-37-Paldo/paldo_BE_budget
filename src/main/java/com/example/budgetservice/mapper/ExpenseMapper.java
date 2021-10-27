package com.example.budgetservice.mapper;

import com.example.budgetservice.model.ExpenseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpenseMapper {
    List<ExpenseDto> findByOutlayYearMonth(@Param("userId") long userId,@Param("yearMonth") String outlayYearMonth);
    List<ExpenseDto> findByCategoryAndOutlayYearMonth(@Param("userId") long userId,
                                                        @Param("category") String category,
                                                        @Param("yearMonth") String outlayYearMonth);
    Long deleteById(long expenseId);
    Long create(ExpenseDto expenseDto);
    Long update(ExpenseDto expenseDto);
    List<Map<String, Integer>> findByUserIdGroupByCategory(long userId, String lastMonthDate);
}
