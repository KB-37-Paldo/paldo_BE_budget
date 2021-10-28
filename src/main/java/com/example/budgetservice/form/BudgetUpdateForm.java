package com.example.budgetservice.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BudgetUpdateForm {
    @JsonIgnore
    private long userId;

    private int food = 0;
    private int shopping = 0;
    private int cafe = 0;
    private int traffic = 0;
    private int culture = 0;
    private int medical = 0;
    private int life = 0;
    private int congratulations = 0;
}
