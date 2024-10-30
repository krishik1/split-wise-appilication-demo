package com.split.wise.application.splitwiseapplication.strategy;

import com.split.wise.application.splitwiseapplication.dto.UserAmount;
import com.split.wise.application.splitwiseapplication.model.*;

import java.util.*;

public class MinimumSettlementStrategy implements SettleUpStrategy{

    PriorityQueue<UserExpense> paid = new PriorityQueue<>((a,b)-> (int) (b.getAmount()-a.getAmount()));
    PriorityQueue<UserExpense> payee = new PriorityQueue<>((a,b)-> (int) (b.getAmount()-a.getAmount()));
    @Override
    public List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses) {
        HashMap<User,Double> addUserBalances = addOutstandingBalances(expenses);
        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>((a,b) ->  Double.compare(a.getAmount(),b.getAmount()));
        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>((a,b) ->  Double.compare(b.getAmount(),a.getAmount()));
        List<SettlementTransaction> transactions = new ArrayList<>();
        for(Map.Entry<User, Double> entry : addUserBalances.entrySet()) {
            if(entry.getValue() < 0){
                minHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            } else if(entry.getValue() > 0){
                maxHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            } else {
                System.out.println("user does not need to participate in settle up");
            }
        }

        while(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            UserAmount lender = maxHeap.poll();
            UserAmount borrower = minHeap.poll();
            if(lender.getAmount()> Math.abs(borrower.getAmount())) {
                double amount = borrower.getAmount() + lender.getAmount();
                lender.setAmount(amount);
                maxHeap.add(lender);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(),lender.getUser(),amount);
                transactions.add(settlementTransaction);
            } else if(lender.getAmount()< Math.abs(borrower.getAmount())) {
                double amount = borrower.getAmount() + lender.getAmount();
                borrower.setAmount(amount);
                minHeap.add(borrower);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(),lender.getUser(),amount);
                transactions.add(settlementTransaction);
            }
        }
        return transactions;
    }

    public HashMap<User,Double> addOutstandingBalances(List<Expense> expenses) {
        HashMap<User,Double> addUserBalances = new HashMap<>();
        for(Expense expense : expenses) {
            for(UserExpense userExpense : expense.getUserExpenses()) {
                User participant = userExpense.getUser();
                double amount = userExpense.getAmount();
                if (addUserBalances.containsKey(participant)) {
                    if(userExpense.getUserExpenseTye().equals(UserExpenseTye.PAID)) {
                        addUserBalances.put(participant,addUserBalances.get(participant)+amount);
                    } else {
                        addUserBalances.put(participant,addUserBalances.get(participant)-amount);
                    }
                } else {
                    if(userExpense.getUserExpenseTye().equals(UserExpenseTye.PAID)) {
                        addUserBalances.put(participant,0 + amount);
                    } else {
                        addUserBalances.put(participant,0 - amount);
                    }
                }
            }
        }
        return addUserBalances;
    }


}
