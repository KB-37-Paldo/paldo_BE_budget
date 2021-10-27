package com.example.budgetservice.controller;

import com.example.budgetservice.form.ExpenseCreateForm;
import com.example.budgetservice.form.ExpenseUpdateForm;
import com.example.budgetservice.model.ExpenseResponseDto;
import com.example.budgetservice.model.ExpensesGroupByCategoryDto;
import com.example.budgetservice.model.ExpensesGroupByDayDto;
import com.example.budgetservice.service.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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


    @ApiOperation(value = "연월별 지출 내역 조회", notes = "특정 유저의 특정년도, 특정월의 지출내역 리스트를 반환")
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
                    dataType = "long", defaultValue = "None"),
            @ApiImplicitParam(name = "requestDate", value = "조회 년월", required = true,
                    dataType = "String", defaultValue = "2021-10")})
    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<ExpensesGroupByDayDto>> getUserExpensesByYearMonth
            (@PathVariable("userId") long userId, String requestDate) {
        List<ExpensesGroupByDayDto> expensesResponse = expenseService.getUserExpensesByOutlayMonth(userId, requestDate);

        return ResponseEntity
                .ok()
                .body(expensesResponse);
    }


    @ApiOperation(value = "항목/연월별 지출 내역 조회", notes = "특정 유저의 특정연도, 특정월의 항목별 지출내역 리스트를 반환")
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true,
                    dataType = "long", defaultValue = "None"),
            @ApiImplicitParam(name = "category", value = "예산 항목", required = true,
                    dataType = "String", defaultValue = "None"),
            @ApiImplicitParam(name = "requestDate", value = "조회 년월", required = true,
                    dataType = "String", defaultValue = "2021-10")})
    @GetMapping(value = "/{userId}/category")
    public ResponseEntity<ExpensesGroupByCategoryDto> getUserExpensesByCategoryAndYearMonth
            (@PathVariable("userId") long userId, String category, String requestDate) {
        ExpensesGroupByCategoryDto expensesResponse =
                expenseService.getUserExpensesByCategory(userId, category, requestDate);

        return ResponseEntity
                .ok()
                .body(expensesResponse);
    }


    @ApiOperation(value = "지출 내역 삭제", notes = "특정 지출내역을 삭제")
    @ApiImplicitParam(name = "expenseId", value = "지출 내역 아이디", required = true,
            dataType = "long", defaultValue = "None")
    @DeleteMapping(value = "/{expenseId}")
    public ResponseEntity<Long> deleteExpense(@PathVariable("expenseId") long expenseId) {
        Long deletedExpenseId = expenseService.deleteExpense(expenseId);

        return ResponseEntity
                .ok()
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

        WebMvcLinkBuilder expenseLink = linkTo(ExpenseController.class);
        return ResponseEntity
                .created(expenseLink.slash(userId).toUri())
                .body(createdExpenseId);
    }


    @ApiOperation(value = "지출 내역 수정", notes = "지출 수정 정보를 입력받아 지출 내역을 수정하고 결과를 반환")
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "expenseId", value = "지출 내역 아이디", required = true,
                    dataType = "long", defaultValue = "None"),
            @ApiImplicitParam(name = "expenseUpdateForm", value = "지출 내역 수정 정보", required = true,
                    dataType = "ExpenseUpdateForm", defaultValue = "None")})
    @PutMapping(value = "/{expenseId}")
    public ResponseEntity<Long> updateExpense(@PathVariable("expenseId") long expenseId,
                                              @RequestBody ExpenseUpdateForm expenseUpdateForm) {
        Long updatedExpenseId = expenseService.updateExpense(expenseId, expenseUpdateForm);

        return ResponseEntity
                .ok()
                .body(updatedExpenseId);
    }
}
