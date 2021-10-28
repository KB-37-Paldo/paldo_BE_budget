package com.example.budgetservice.service;

import com.example.budgetservice.form.ExpenseUpdateForm;
import com.example.budgetservice.mapper.ExpenseMapper;
import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.model.ExpenseDto;
import com.example.budgetservice.model.ExpenseWeekAmountDto;
import com.example.budgetservice.response.ExpenseResponse;
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

            if(!hasDay(day, groupByDayExpenseList)) {
                ExpensesGroupByDayDto addedExpense = new ExpensesGroupByDayDto(day, 0, new ArrayList<>());
                groupByDayExpenseList.add(addedExpense);
            }

            ExpensesGroupByDayDto updateExpenses = getExpensesByDay(day, groupByDayExpenseList);
            updateExpenses.getExpenseResponses().add(expense.getExpenseResponse());
            updateExpenses.addTotalAmount(expense.getAmount());
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

        List<ExpenseResponse> expenseList = expenses.stream()
                .map(ExpenseDto::getExpenseResponse)
                .collect(Collectors.toList());

        return new ExpensesGroupByCategoryDto(totalAmount, expenseList);
    }


    @Override
    public List<ExpenseWeekAmountDto> getUserWeekExpenses(long userId, String outlayYearMonth) {
        List<ExpenseDto> expenses = sqlSession.getMapper(ExpenseMapper.class)
                .findByOutlayYearMonth(userId, outlayYearMonth);

        List<ExpenseWeekAmountDto> expenseWeekAmounts = new ArrayList<>();
        expenses.forEach(expense -> {
            int week = getWeekOfMonth(expense.getOutlayDatetime());
            if(!hasWeek(week, expenseWeekAmounts)) {
                ExpenseWeekAmountDto addedExpense = new ExpenseWeekAmountDto(week, 0);
                expenseWeekAmounts.add(addedExpense);
            }

            if(expense.getAmount() < 0) {
                ExpenseWeekAmountDto expenseWeekAmount = getExpenseWeekAmountByWeek(week, expenseWeekAmounts);
                expenseWeekAmount.addTotalAmount(expense.getAmount());
            }
        });

        Collections.sort(expenseWeekAmounts);
        return expenseWeekAmounts;
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


    private boolean hasDay(int day, List<ExpensesGroupByDayDto> expensesGroupByDayList) {
        boolean haveDay = false;
        for(int i = 0; i < expensesGroupByDayList.size(); i++) {
            if(expensesGroupByDayList.get(i).getDay() == day) {
                haveDay = true;
                break;
            }
        }
        return haveDay;
    }

    private ExpensesGroupByDayDto getExpensesByDay (int day, List<ExpensesGroupByDayDto> expensesGroupByDay) {
        for(int i = 0; i < expensesGroupByDay.size(); i++) {
            if(expensesGroupByDay.get(i).getDay() == day) {
                return expensesGroupByDay.get(i);
            }
        }
        return null;
    }

    private int getWeekOfMonth(String datetime) {
        int year = Integer.parseInt(datetime.substring(0,4));
        int month = Integer.parseInt(datetime.substring(5, 7)) - 1;
        int day = Integer.parseInt(datetime.substring(8, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        int week = calendar.get(Calendar.WEEK_OF_MONTH);

        return week;
    }

    private boolean hasWeek(int week, List<ExpenseWeekAmountDto> expenseWeekAmounts) {
        boolean haveWeek = false;
        for(int i = 0; i < expenseWeekAmounts.size(); i++) {
            if(expenseWeekAmounts.get(i).getWeek() == week) {
                haveWeek = true;
                break;
            }
        }
        return haveWeek;
    }

    private ExpenseWeekAmountDto getExpenseWeekAmountByWeek(int week, List<ExpenseWeekAmountDto> expenseWeekAmounts) {
        for(int i = 0; i < expenseWeekAmounts.size(); i++) {
            if(expenseWeekAmounts.get(i).getWeek() == week) {
                return expenseWeekAmounts.get(i);
            }
        }
        return null;
    }
}
