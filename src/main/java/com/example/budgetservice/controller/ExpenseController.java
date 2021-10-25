package com.example.budgetservice.controller;

import com.example.budgetservice.model.ExpenseResponseDto;
import com.example.budgetservice.service.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Api(value = "Expense Service")
@RestController
@RequestMapping(value = "/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @ApiOperation(value = "지출 조회", notes = "특정 유저의 지출내역 리스트를 반환")
    @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
            dataType = "long", defaultValue = "None")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<ExpenseResponseDto>> getUserExpenses(@PathVariable("userId") long userId) {
        List<ExpenseResponseDto> expensesResponse = expenseService.getUserExpenses(userId);

        // WebMvcLinkBuilder expenseLink= linkTo(ExpenseController.class).slash(userId);
        return ResponseEntity.ok().body(expensesResponse);
    }
}
