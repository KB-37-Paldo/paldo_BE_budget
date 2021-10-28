package com.example.budgetservice.service;

import com.example.budgetservice.exception.ErrorCode;
import com.example.budgetservice.exception.NotBudgetException;
import com.example.budgetservice.form.BudgetCreateForm;
import com.example.budgetservice.mapper.BudgetMapper;
import com.example.budgetservice.mapper.ExpenseMapper;
import com.example.budgetservice.model.BudgetDto;
import com.example.budgetservice.response.BudgetResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BudgetServiceImpl implements BudgetService{
	@Autowired
    SqlSession sqlsession;

	@Override
	public long createBudget(BudgetCreateForm budgetCreateForm){
		return sqlsession.getMapper(BudgetMapper.class).createBudget(budgetCreateForm);
	}

	@Override
	public BudgetResponse findByUserID(long userId, String requestDate) {
		BudgetResponse budgetResponse = new BudgetResponse();
		BudgetDto budgetDto = sqlsession.getMapper(BudgetMapper.class).findByUserId(userId)
				.orElseThrow(() -> new NotBudgetException("Not found budget", ErrorCode.NOT_FOUND_BUDGET));

		// category별 지출조회
		List<Map<String, String>> result = sqlsession.getMapper(ExpenseMapper.class)
				.findByUserIdGroupByCategory(userId, requestDate);
		budgetResponse.addOutlay(result);

		budgetResponse.addAmount(budgetDto);
		budgetResponse.setTotal();
		return budgetResponse;
	}
}
