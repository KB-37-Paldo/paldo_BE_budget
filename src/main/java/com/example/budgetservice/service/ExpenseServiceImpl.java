package com.example.budgetservice.service;

import com.example.budgetservice.form.ExpenseUpdateForm;
import com.example.budgetservice.mapper.ExpenseMapper;
import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.model.ExpenseDto;
import com.example.budgetservice.model.ExpenseResponseDto;
import com.example.budgetservice.model.ExpensesGroupByCategoryDto;
import com.example.budgetservice.model.ExpensesGroupByDayDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<ExpensesGroupByDayDto> getUserExpensesByOutlayMonth(long userId, String outlayYearMonth) {
        List<ExpenseDto> expenses = sqlSession.getMapper(ExpenseMapper.class)
                .findByOutlayYearMonth(userId, outlayYearMonth);

        List<ExpensesGroupByDayDto> groupByDayExpenseList = new ArrayList<>();
        expenses.forEach(expense -> {
            int day = Integer.parseInt(expense.getOutlayDatetime().substring(8, 10));
            int size = groupByDayExpenseList.size();

            if(size == 0 || groupByDayExpenseList.get(size - 1).getDay() > day) {
                groupByDayExpenseList.add(new ExpensesGroupByDayDto(day, 0, new ArrayList<>()));
            }

            size = groupByDayExpenseList.size();
            groupByDayExpenseList.get(size - 1).getExpenseResponses().add(expense.getExpenseResponse());
            groupByDayExpenseList.get(size - 1).addTotalAmount(expense.getAmount());
        });

        return groupByDayExpenseList;
    }


    @Override
    public ExpensesGroupByCategoryDto getUserExpensesByCategory(long userId, String category, String outlayYearMonth) {
        List<ExpenseDto> expenses = sqlSession.getMapper(ExpenseMapper.class)
                .findByCategoryAndOutlayYearMonth(userId, category, outlayYearMonth);

        int totalAmount = expenses.stream()
                .mapToInt(ExpenseDto::getAmount)
                .sum();

        List<ExpenseResponseDto> expenseList = expenses.stream()
                .map(ExpenseDto::getExpenseResponse)
                .collect(Collectors.toList());

        return new ExpensesGroupByCategoryDto(totalAmount, expenseList);
    }


    @Override
    public Long deleteExpense(long expenseId) {
        return sqlSession.getMapper(ExpenseMapper.class).deleteById(expenseId);
    }

    @Override
    public Long createExpense(long userId, ExpenseCreateForm createForm) {
        ExpenseDto expense = new ExpenseDto(userId, createForm);
        return sqlSession.getMapper(ExpenseMapper.class).create(expense);
    }

    @Override
    public Long updateExpense(long expenseId, ExpenseUpdateForm updateForm) {
        ExpenseDto expense = new ExpenseDto(expenseId, updateForm);
        return sqlSession.getMapper(ExpenseMapper.class).update(expense);
    }
}
