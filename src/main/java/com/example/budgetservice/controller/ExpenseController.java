package com.example.budgetservice.controller;

import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.model.ExpenseResponseDto;
import com.example.budgetservice.service.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Api(value = "Expense Service")
@RestController
@RequestMapping(value = "/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @ApiOperation(value = "지출 내역 조회", notes = "특정 유저의 지출내역 리스트를 반환")
    @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
            dataType = "long", defaultValue = "None")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<CollectionModel<ExpenseResponseDto>> getUserExpenses(@PathVariable("userId") long userId) {
        List<ExpenseResponseDto> expensesResponse = expenseService.getUserExpenses(userId);

        WebMvcLinkBuilder expenseLink= linkTo(ExpenseController.class).slash(userId);
        return ResponseEntity.ok()
                .body(CollectionModel.of(expensesResponse)
                        .add(expenseLink.withSelfRel()));
    }


    @ApiOperation(value = "지출 내역 삭제", notes = "특정 지출내역을 삭제")
    @ApiImplicitParam(name = "expenseId", value = "지출 내역 아이디", required = true,
            dataType = "long", defaultValue = "None")
    @DeleteMapping(value = "/{expenseId}")
    public ResponseEntity<Long> deleteExpense(@PathVariable("expenseId") long expenseId) {
        Long deletedExpenseId = expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok()
                .body(deletedExpenseId);
    }


    @ApiOperation(value = "지출 내역 생성", notes = "지출 생성 정보를 입력받아 지출 내역을 생성하고 결과를 반환")
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
                    dataType = "long", defaultValue = "None"),
            @ApiImplicitParam(name = "expenseCreateForm", value = "지출 내역 생성 정보", required = true,
                    dataType = "long", defaultValue = "None")})
    @PostMapping(value = "/{userId}")
    public ResponseEntity<Long> createExpense(@PathVariable("userId") long userId,
                                              @RequestBody ExpenseCreateForm expenseCreateForm) {
        Long createdExpenseId = expenseService.createExpense(userId, expenseCreateForm);

        WebMvcLinkBuilder expenseLink= linkTo(ExpenseController.class).slash(userId);
        return ResponseEntity.created(expenseLink.toUri())
                .body(createdExpenseId);
    }
}
