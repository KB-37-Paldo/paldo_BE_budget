package com.example.budgetservice.response;

import com.example.budgetservice.model.BudgetDetailDto;
import com.example.budgetservice.model.BudgetDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class BudgetResponse {
    private long userId;
    private long totalOutlay;
    private long totalIncome;
    private BudgetDetailDto food = new BudgetDetailDto("식비");
    private BudgetDetailDto shopping = new BudgetDetailDto("쇼핑");
    private BudgetDetailDto cafe = new BudgetDetailDto("카페");
    private BudgetDetailDto traffic = new BudgetDetailDto("교통");
    private BudgetDetailDto financial = new BudgetDetailDto("금융");
    private BudgetDetailDto culture = new BudgetDetailDto("문화");
    private BudgetDetailDto medical = new BudgetDetailDto("의료");
    private BudgetDetailDto subscribe = new BudgetDetailDto("구독");
    private BudgetDetailDto life = new BudgetDetailDto("생활");
    private BudgetDetailDto congratulations = new BudgetDetailDto("경조");

    public void addOutlay(List<Map<String, String>> items) {
        items.forEach((item)->{
            if (item.get("category").equals("수입")) {this.totalIncome = Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("식비")) {this.food.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("쇼핑")) {this.shopping.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("카페")) {this.cafe.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("교통")) {this.traffic.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("금융")) {this.financial.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("문화")) {this.culture.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("의료")) {this.medical.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("구독")) {this.subscribe.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("생활")) {this.life.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
            else if (item.get("category").equals("경조")) {this.congratulations.setOutlay(-Integer.parseInt(String.valueOf(item.get("amount"))));}
        });
    }
    public void addAmount(BudgetDto budgetDto) {
        this.food.setAmount(budgetDto.getFood());
        this.shopping.setAmount(budgetDto.getShopping());
        this.cafe.setAmount(budgetDto.getCafe());
        this.traffic.setAmount(budgetDto.getTraffic());
        this.financial.setAmount(budgetDto.getFinancial());
        this.culture.setAmount(budgetDto.getCulture());
        this.medical.setAmount(budgetDto.getMedical());
        this.subscribe.setAmount(budgetDto.getSubscribe());
        this.life.setAmount(budgetDto.getLife());
        this.congratulations.setAmount(budgetDto.getCongratulations());
    }
    public void setTotal() {
        this.totalOutlay = 0;
        this.totalOutlay += this.food.getOutlay();
        this.totalOutlay += this.shopping.getOutlay();
        this.totalOutlay += this.cafe.getOutlay();
        this.totalOutlay += this.traffic.getOutlay();
        this.totalOutlay += this.financial.getOutlay();
        this.totalOutlay += this.culture.getOutlay();
        this.totalOutlay += this.medical.getOutlay();
        this.totalOutlay += this.subscribe.getOutlay();
        this.totalOutlay += this.life.getOutlay();
        this.totalOutlay += this.congratulations.getOutlay();
    }
}
