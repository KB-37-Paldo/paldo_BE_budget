package com.example.budgetservice.service;

import com.example.budgetservice.mapper.ExpenseMapper;
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
}
