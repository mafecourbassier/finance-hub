package com.financehub.service;

import com.financehub.dto.DashboardDTO;
import com.financehub.model.Transaction;
import com.financehub.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private TransactionRepository transactionRepository;

    public DashboardDTO getDashboard() {

        List<Transaction> all = transactionRepository.findAll();

        double totalIncome = 0.0;
        double totalExpenses = 0.0;

        long recurringCount = 0;
        long pendingCount = 0;

        Map<String, Double> expensesByCategory = new HashMap<>();

        for (Transaction t : all) {

            if (t.getType().equals("INCOME")) {
                totalIncome += t.getAmount();
            } else if (t.getType().equals("EXPENSE")) {
                totalExpenses += t.getAmount();

                String categoryName = t.getCategory().getName();
                expensesByCategory.put(
                        categoryName,
                        expensesByCategory.getOrDefault(categoryName, 0.0) + t.getAmount()
                );
            }

            if (t.isRecurring()) {
                recurringCount++;
            }

            if (t.getStatus().equals("PENDING")) {
                pendingCount++;
            }
        }

        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setTotalIncome(totalIncome);
        dashboard.setTotalExpenses(totalExpenses);
        dashboard.setBalance(totalIncome - totalExpenses);
        dashboard.setRecurringExpensesCount(recurringCount);
        dashboard.setPendingPaymentsCount(pendingCount);
        dashboard.setExpensesByCategory(expensesByCategory);

        return dashboard;
    }
}