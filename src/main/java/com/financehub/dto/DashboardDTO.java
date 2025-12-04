package com.financehub.dto;

import java.util.Map;

public class DashboardDTO {

    private Double totalIncome;
    private Double totalExpenses;
    private Double balance;
    private Long recurringExpensesCount;
    private Long pendingPaymentsCount;
    private Map<String, Double> expensesByCategory;

    public DashboardDTO() {}

    public Double getTotalIncome() { return totalIncome; }
    public void setTotalIncome(Double totalIncome) { this.totalIncome = totalIncome; }

    public Double getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(Double totalExpenses) { this.totalExpenses = totalExpenses; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public Long getRecurringExpensesCount() { return recurringExpensesCount; }
    public void setRecurringExpensesCount(Long recurringExpensesCount) { this.recurringExpensesCount = recurringExpensesCount; }

    public Long getPendingPaymentsCount() { return pendingPaymentsCount; }
    public void setPendingPaymentsCount(Long pendingPaymentsCount) { this.pendingPaymentsCount = pendingPaymentsCount; }

    public Map<String, Double> getExpensesByCategory() { return expensesByCategory; }
    public void setExpensesByCategory(Map<String, Double> expensesByCategory) { this.expensesByCategory = expensesByCategory; }
}
