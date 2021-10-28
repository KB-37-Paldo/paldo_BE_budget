package com.example.budgetservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RecommendDetailDto {
	private String category;
	private int amount = 0;
	private int lastAmount = 0;
	
	public RecommendDetailDto(String category) {
		this.category = category;
	}
}
