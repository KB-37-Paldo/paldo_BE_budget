package com.example.budgetservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.budgetservice.form.BudgetCreateForm;
import com.example.budgetservice.service.BudgetService;

import io.swagger.annotations.Api;

@Api(value = "Budget Service")
@RestController
public class BudgetController {
	 @Autowired
	    BudgetService budgetService;

		/*
		 * // 예산 생성
		 * 
		 * @PostMapping(value = "/{userId}/budget/{dvn}") public ResponseEntity<Long>
		 * createBudget(@PathVariable("userId") long userId,
		 * 
		 * @RequestBody @Valid BudgetCreateForm budgetCreateForm) {
		 * budgetCreateForm.setUserId(userId); long budgetId =
		 * budgetService.createBudget(budgetCreateForm); return new
		 * ResponseEntity<Long>(budgetId, HttpStatus.CREATED); }
		 */
}
