package com.example.budgetservice.service;

import com.example.budgetservice.mapper.BudgetMapper;
import com.example.budgetservice.mapper.ExpenseMapper;
import com.example.budgetservice.model.BudgetDto;
import com.example.budgetservice.model.BudgetResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BudgetServiceImpl implements BudgetService{
	@Autowired
    SqlSession sqlsession;

	@Override
	public long createBudget(long userId){

		BudgetDto form = new BudgetDto();
		form.setUserId(userId);
		Map<String, Integer> map = new HashMap<>();

		/*
			Todo 추천지출 AI 연동 넣기
		*/

		// 저번달 구하기
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,0);
		String lastMonthDate = df.format(cal.getTime());

		// 저번달 category별 지출조회
		List<Map<String, String>> result = sqlsession.getMapper(ExpenseMapper.class)
				.findByUserIdGroupByCategory(userId, lastMonthDate);

		form.addAmount(result);

		sqlsession.getMapper(BudgetMapper.class).createBudget(form);
		return 1;
	}

	@Override
	public BudgetResponse findByUserID(long userId, String requestDate) {
		BudgetResponse budgetResponse = new BudgetResponse();

		// category별 지출조회
		List<Map<String, String>> result = sqlsession.getMapper(ExpenseMapper.class)
				.findByUserIdGroupByCategory(userId, requestDate);
		budgetResponse.addOutlay(result);
		System.out.println("budgetResponse = " + budgetResponse.getTraffic().getOutlay());

		BudgetDto budgetDto = sqlsession.getMapper(BudgetMapper.class).findByUserId(userId);
		budgetResponse.addAmount(budgetDto);
		budgetResponse.setTotal();
		return budgetResponse;
	}
}
