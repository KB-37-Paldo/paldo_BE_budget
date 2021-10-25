package com.example.budgetservice.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService{
	@Autowired
    SqlSession sqlsession;
	
	
	//TODO - 각 기능 OVERRIDE 
	
}
