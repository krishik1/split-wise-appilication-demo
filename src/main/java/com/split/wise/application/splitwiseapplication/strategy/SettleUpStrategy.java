package com.split.wise.application.splitwiseapplication.strategy;

import com.split.wise.application.splitwiseapplication.model.Expense;
import com.split.wise.application.splitwiseapplication.model.SettlementTransaction;

import java.util.List;

public interface SettleUpStrategy {
    List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses);
}
