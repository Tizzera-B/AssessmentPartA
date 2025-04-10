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
}

public class ExpenseSummary {

    public static void summarizeExpenses(List<Expense> expenses) {
        // a. Total amount spent per category
        Map<Category, Integer> totalPerCategory = expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingInt(Expense::getAmount)
                ));

        System.out.println("Total spent per category:");
        totalPerCategory.forEach((category, total) ->
                System.out.println("- " + category + ": " + total)
        );

        // b. Highest expense category
        Optional<Map.Entry<Category, Integer>> maxEntry = totalPerCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        maxEntry.ifPresent(entry ->
                System.out.println("Highest expense category: " + entry.getKey())
        );
    }

    // Example usage
    public static void main(String[] args) {
        List<Expense> expenses = List.of(
                new Expense("Lunch", 1500, Category.FOOD, LocalDate.of(2025, 3, 25)),
                new Expense("Uber Ride", 2500, Category.TRAVEL, LocalDate.of(2025, 3, 26)),
                new Expense("Groceries", 5000, Category.FOOD, LocalDate.of(2025, 3, 27))
        );

        summarizeExpenses(expenses);
    }
}
