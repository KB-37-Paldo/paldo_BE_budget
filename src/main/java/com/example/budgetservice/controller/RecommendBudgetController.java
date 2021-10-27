package com.example.budgetservice.controller;

import com.example.budgetservice.response.BudgetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.budgetservice.service.RecommendBudgetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Recommend Budget Service")
@RestController
public class RecommendBudgetController {
	@Autowired
	RecommendBudgetService recommendBudgetService;

	@ApiOperation(value = "예산 추천 조회", notes = "특정 유저의 추천 예산 정보를 반환")
	@GetMapping(value = "/recommend/budget/{userId}")
	public ResponseEntity<EntityModel<BudgetResponse>> getRecommendBudget(
			@PathVariable("userId") long userId){
		return ResponseEntity.ok().body(
				EntityModel.of(recommendBudgetService.findByUserID(userId)));
	}


}
