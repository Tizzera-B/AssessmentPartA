package com.example.Assessment.service;

import com.example.Assessment.Expense;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    Expense createExpense(Expense expense);
    List<Expense> getAllExpenses(int page, int size);
    Expense getExpenseById(UUID id);
    Expense updateExpense(UUID id, Expense expense);
    void deleteExpense(UUID id);
}
