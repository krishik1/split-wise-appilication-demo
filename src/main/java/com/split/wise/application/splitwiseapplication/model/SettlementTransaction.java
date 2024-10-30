package com.split.wise.application.splitwiseapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@Entity
public class SettlementTransaction extends BaseModel {
    @ManyToOne
    private User borrower;
    @ManyToOne
    private User lender;
    private Currency currency;
    private double amount;

    public SettlementTransaction(User borrower, User lender, double amount) {
        this.borrower = borrower;
        this.lender = lender;
        this.amount = amount;
    }
}
