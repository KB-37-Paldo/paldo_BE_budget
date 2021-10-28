package com.example.budgetservice.response;

import com.example.budgetservice.model.RecommendDetailDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RecommendResponse {
	private long userId;
    private long totalAmount;
    private long lastTotalAmount;
    private RecommendDetailDto shopping = new RecommendDetailDto("쇼핑");
    private RecommendDetailDto food = new RecommendDetailDto("식비");
    private RecommendDetailDto cafe = new RecommendDetailDto("카페");
    private RecommendDetailDto traffic = new RecommendDetailDto("교통");
    private RecommendDetailDto culture = new RecommendDetailDto("문화");
    private RecommendDetailDto medical = new RecommendDetailDto("의료");
    private RecommendDetailDto life = new RecommendDetailDto("생활");
    private RecommendDetailDto congratulations = new RecommendDetailDto("경조");
}
