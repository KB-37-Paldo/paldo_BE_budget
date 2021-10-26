package com.example.budgetservice.service;

import com.example.budgetservice.form.ExpenseUpdateForm;
import com.example.budgetservice.mapper.ExpenseMapper;
import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.model.ExpenseDto;
import com.example.budgetservice.model.ExpenseResponseDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<ExpenseResponseDto> getUserExpenses(long userId, String yearMonth) {
        List<ExpenseDto> expenses = sqlSession.getMapper(ExpenseMapper.class)
                .findByUserIdAndYearMonth(userId, yearMonth);
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
        ExpenseDto expense = new ExpenseDto(userId, getCurrentDatetime(), createForm);
        return sqlSession.getMapper(ExpenseMapper.class).create(expense);
    }

    @Override
    public Long updateExpense(long expenseId, ExpenseUpdateForm updateForm) {
        ExpenseDto expense = new ExpenseDto(expenseId, updateForm);
        return sqlSession.getMapper(ExpenseMapper.class).update(expense);
    }

    private String getCurrentDatetime() {
        Date date = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDatetime = format.format(date);

        return currentDatetime;
    }
}
