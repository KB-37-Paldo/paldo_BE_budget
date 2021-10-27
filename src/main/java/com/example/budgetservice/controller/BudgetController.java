package com.example.budgetservice.controller;

import com.example.budgetservice.response.BudgetResponse;
import com.example.budgetservice.service.BudgetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Budget Service")
@RestController
public class BudgetController {
	@Autowired
	BudgetService budgetService;

	// 예산 생성
	@PostMapping(value = "/{userId}/budget")
	public ResponseEntity<Long> createBudget(@PathVariable("userId") long userId) {
		
		long budgetId = budgetService.createBudget(userId);
		return new ResponseEntity<Long>(budgetId, HttpStatus.CREATED);
	}

	// 예산 조회
	@ApiOperation(value = "예산 조회", notes = "특정 유저의 특정년도, 특정월의 예산 정보를 반환")
	@ApiImplicitParam(name = "requestDate", value = "조회 년월", required = true,
			dataType = "String", defaultValue = "2021-10")
	@GetMapping(value = "/{userId}/budget")
	public ResponseEntity<EntityModel<BudgetResponse>> getBudget(
			@PathVariable("userId") long userId, String requestDate) {
		return ResponseEntity.ok().body(
				EntityModel.of(budgetService.findByUserID(userId, requestDate)));
	}

}
