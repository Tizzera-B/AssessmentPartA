package com.example.Assessment;

import com.example.Assessment.service.ExpenseServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseServiceImplTest {

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Mock
    private ExpenseRepository repository;

    private Expense expense;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        expense = new Expense(UUID.randomUUID(), "Lunch", BigDecimal.valueOf(10.5), Expense.Category.FOOD, LocalDate.now());
    }

    @Test
    void testCreateExpense() {
        when(repository.save(expense)).thenReturn(expense);

        Expense savedExpense = expenseService.createExpense(expense);
        assertNotNull(savedExpense);
        assertEquals("Lunch", savedExpense.getTitle());
        assertEquals(BigDecimal.valueOf(10.5), savedExpense.getAmount());
    }


    @Test
    void testGetExpenseById() {
        UUID id = expense.getId();
        when(repository.findById(id)).thenReturn(Optional.of(expense));

        Expense fetchedExpense = expenseService.getExpenseById(id);
        assertNotNull(fetchedExpense);
        assertEquals(expense.getId(), fetchedExpense.getId());
    }

    @Test
    void testGetExpenseById_NotFound() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> expenseService.getExpenseById(id));
    }

    @Test
    void testUpdateExpense() {
        UUID id = expense.getId();
        Expense updatedExpense = new Expense(id, "Updated Lunch", BigDecimal.valueOf(20.5), Expense.Category.FOOD, LocalDate.now());

        when(repository.findById(id)).thenReturn(Optional.of(expense));
        when(repository.save(updatedExpense)).thenReturn(updatedExpense);

        Expense result = expenseService.updateExpense(id, updatedExpense);
        assertEquals("Updated Lunch", result.getTitle());
        assertEquals(BigDecimal.valueOf(20.5), result.getAmount());
    }

    @Test
    void testDeleteExpense() {
        UUID id = expense.getId();
        when(repository.existsById(id)).thenReturn(true);

        expenseService.deleteExpense(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteExpense_NotFound() {
        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> expenseService.deleteExpense(id));
    }
}
