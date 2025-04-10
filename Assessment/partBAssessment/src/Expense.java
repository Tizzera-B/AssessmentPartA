import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

enum Category {
    FOOD, TRAVEL, ENTERTAINMENT, UTILITIES, OTHER
}

class Expense {
    private String description;
    private int amount;
    private Category category;
    private LocalDate date;

    public Expense(String description, int amount, Category category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return description + ": " + amount + " (" + category + ")";
    }
}


