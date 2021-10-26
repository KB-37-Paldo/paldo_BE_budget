package com.example.budgetservice.service;

import com.example.budgetservice.form.ExpenseUpdateForm;
import com.example.budgetservice.mapper.ExpenseMapper;
import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.model.ExpenseDto;
import com.example.budgetservice.model.ExpenseResponseDto;
import com.example.budgetservice.model.SortedExpensesDto;
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
    public List<SortedExpensesDto> getUserExpenses(long userId, String yearMonth) {
        List<ExpenseDto> expenses = sqlSession.getMapper(ExpenseMapper.class)
                .findByUserIdAndYearMonth(userId, yearMonth);

        List<SortedExpensesDto> expenseList = new ArrayList<>();
        expenses.forEach(expense -> {
            int day = Integer.parseInt(expense.getOutlayDatetime().substring(8, 10));
            if(expenseList.size() == 0 || expenseList.get(expenseList.size() - 1).getDay() > day) {
                expenseList.add(new SortedExpensesDto(day, new ArrayList<>()));
            }
            expenseList.get(expenseList.size() - 1).getExpenseResponses().add(expense.getExpenseResponse());
        });

        return expenseList;
    }


    @Override
    public List<ExpenseResponseDto> getUserExpensesByCatgory(long userId, String category, String yearMonth) {
        List<ExpenseDto> expenses = sqlSession.getMapper(ExpenseMapper.class)
                .findByCategoryAndYearMonth(userId, category, yearMonth);

        return expenses.stream()
                .map(ExpenseDto::getExpenseResponse)
                .collect(Collectors.toList());
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
