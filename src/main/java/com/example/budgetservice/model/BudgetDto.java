package com.example.budgetservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BudgetDto {
    private long userId;
    private int food = 0;
    private int shopping = 0;
    private int cafe = 0;
    private int traffic = 0;
    private int financial = 0;
    private int culture = 0;
    private int medical = 0;
    private int subscribe = 0;
    private int life = 0;
    private int congratulations = 0;

    public void addAmount(List<Map<String, String>> items) {
        items.forEach((item)->{
            if (item.get("category").equals("식비")) {this.food += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("쇼핑")) {this.shopping += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("카페")) {this.cafe += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("교통")) {this.traffic += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("금융")) {this.financial += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("문화")) {this.culture += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("의료")) {this.medical += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("구독")) {this.subscribe += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("생활")) {this.life += Integer.parseInt(String.valueOf(item.get("amount")));}
            else if (item.get("category").equals("경조")) {this.congratulations += Integer.parseInt(String.valueOf(item.get("amount")));}
        });
    }

}
