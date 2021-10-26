package com.example.budgetservice.service;

import com.example.budgetservice.mapper.ExpenseMapper;
import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.model.ExpenseDto;
import com.example.budgetservice.model.ExpenseResponseDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<ExpenseResponseDto> getUserExpenses(long userId) {
        List<ExpenseDto> expenses = sqlSession.getMapper(ExpenseMapper.class).findByUserId(userId);
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
}
