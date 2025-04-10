package com.example.Assessment;


import com.example.Assessment.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService service;

    @PostMapping
    public Expense addExpense(@Valid @RequestBody Expense expense) {
        return service.createExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAllExpenses(page, size);
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable UUID id) {
        return service.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable UUID id, @Valid @RequestBody Expense expense) {
        return service.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable UUID id) {
        service.deleteExpense(id);
    }
}

