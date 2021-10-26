package com.example.budgetservice.service;

import com.example.budgetservice.mapper.ExpenseMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.budgetservice.form.BudgetCreateForm;
import com.example.budgetservice.mapper.BudgetMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BudgetServiceImpl implements BudgetService{
	@Autowired
    SqlSession sqlsession;

	@Override
	public long createBudget(BudgetCreateForm budgetCreateForm){

		Map<String, String> category = new HashMap<>();
		category.put("식비", "food");
		category.put("쇼핑", "shopping");
		category.put("카페", "cafe");
		category.put("교통", "traffic");
		category.put("금융", "financial");
		category.put("문화", "culture");
		category.put("의료", "medical");
		category.put("구독", "subscribe");
		category.put("생활", "life");
		category.put("경조", "congratulations");

		Map<String, Integer> map = new HashMap<>();
		map.put("food", 0);
		map.put("shopping", 0);
		map.put("cafe", 0);
		map.put("traffic", 0);
		map.put("financial", 0);
		map.put("culture", 0);
		map.put("medical", 0);
		map.put("subscribe", 0);
		map.put("life", 0);
		map.put("congratulations", 0);

		/*
			Todo 추천지출 AI 연동 넣기
		*/

		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,0);
		String lastMonthDate = df.format(cal.getTime());

		// 저번달 category별 지출조회
		List<Map<String, Integer>> result = sqlsession.getMapper(ExpenseMapper.class)
				.findByUserIdGroupByCategory(budgetCreateForm.getUserId(), lastMonthDate);


		for (Map<String, Integer> item : result) {
			String title = category.get(item.get("category"));
			int itemAmount = Integer.parseInt(String.valueOf(item.get("amount")));
			map.put(title, map.get(title) + itemAmount);
		}

		System.out.println(map);
		sqlsession.getMapper(BudgetMapper.class).createBudget(budgetCreateForm);
//		long id = budgetCreateForm.getBudgetId();
//
//		return id;
		return 1;
	}
}
