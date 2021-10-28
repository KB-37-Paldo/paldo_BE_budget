package com.example.budgetservice.controller;

import com.example.budgetservice.form.BudgetCreateForm;
import com.example.budgetservice.response.BudgetResponse;
import com.example.budgetservice.service.BudgetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Budget Service")
@RestController
public class BudgetController {

	@Autowired
	BudgetService budgetService;

	@ApiOperation(value = "예산 생성", notes = "특정 유저의 나이, 소득, 지출 등의 데이터로 한달 예산을 생성하고 결과를 반환")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "유저 아이디", required = true,
					dataType = "long", defaultValue = "None"),
			@ApiImplicitParam(name = "budgetCreateForm", value = "예산 생성 정보", required = true,
					dataType = "BudgetCreatedForm", defaultValue = "None")})
	@PostMapping(value = "/{userId}/budget")
	public ResponseEntity<Long> createBudget(@PathVariable("userId") long userId,
											 @RequestBody @Valid BudgetCreateForm budgetCreateForm) {
		budgetCreateForm.setUserId(userId);
		long budgetId = budgetService.createBudget(budgetCreateForm);
		return new ResponseEntity<Long>(budgetId, HttpStatus.CREATED);
	}


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
