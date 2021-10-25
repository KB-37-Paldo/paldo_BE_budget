package com.example.budgetservice.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.budgetservice.form.BudgetCreateForm;
import com.example.budgetservice.mapper.BudgetMapper;

@Service
public class BudgetServiceImpl implements BudgetService{
	@Autowired
    SqlSession sqlsession;
	
	
	//TODO - 예산 생성 
	 @Override
	    public long createBudget(BudgetCreateForm budgetCreateForm){
	        sqlsession.getMapper(BudgetMapper.class).createBudget(budgetCreateForm);
	        long id = budgetCreateForm.getBudgetId();

	        return id;
	    }
}
