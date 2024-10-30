package com.split.wise.application.splitwiseapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel {
    private String description;
    private double amount;
    private Currency currency;
    private LocalDateTime expenseTime;
    @ManyToOne
    private User addedBy;

    @OneToMany
    private List<UserExpense> userExpenses;
}
