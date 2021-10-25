package com.example.budgetservice.form;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetCreateForm {
	private int foodBgt ;
	private int shoppingBgt;
	private int cafeBgt;
	private int trafficBgt;
	private int financialBgt;
	private int cultureBgt;
	private int medicalBgt;
	private int subscribeBgt;
	private int   lifeBgt;
}
