package com.example.Assessment;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
