package com.split.wise.application.splitwiseapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name="SPLITWISE_GROUP")
public class Group extends BaseModel {
    private String name;
    @ManyToOne
    private User createdBy;
    private LocalDate creationDate;
    private double totalAmountSpent;

    @ManyToMany(mappedBy = "groups")
    private List<User> members;
    // As Group - Expense is a one way mapping we do not require mapping table
    @OneToMany
    private List<Expense> expenses;
    @OneToMany
    private List<SettlementTransaction> transactions;
}
