import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                System.out.println("- " + category + ": " + total));

        // b. Highest expense category
        Category highestCategory = totalPerCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("Highest expense category: " + highestCategory);
    }

    public static void main(String[] args) {
        List<Expense> expenses = List.of(
                new Expense("Lunch", 1500, Category.FOOD, LocalDate.of(2025, 3, 25)),
                new Expense("Uber Ride", 2500, Category.TRAVEL, LocalDate.of(2025, 3, 26)),
                new Expense("Groceries", 5000, Category.FOOD, LocalDate.of(2025, 3, 27))
        );

        summarizeExpenses(expenses);
    }
}
