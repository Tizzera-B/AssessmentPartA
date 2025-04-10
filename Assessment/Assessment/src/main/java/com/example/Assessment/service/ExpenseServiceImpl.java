package com.example.Assessment.service;


import com.example.Assessment.Expense;
import com.example.Assessment.ExpenseRepository;
import com.example.Assessment.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository repository;

    @Override
    public Expense createExpense(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).getContent();
    }

    @Override
    public Expense getExpenseById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
    }

    @Override
    public Expense updateExpense(UUID id, Expense expense) {
        Expense existing = getExpenseById(id);
        existing.setTitle(expense.getTitle());
        existing.setAmount(expense.getAmount());
        existing.setCategory(expense.getCategory());
        existing.setDate(expense.getDate());
        return repository.save(existing);
    }

    @Override
    public void deleteExpense(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not found");
        }
        repository.deleteById(id);
    }
}
