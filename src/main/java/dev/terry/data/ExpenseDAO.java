package dev.terry.data;

import dev.terry.entities.Expense;
import dev.terry.entities.enums.Status;

import java.util.List;

public interface ExpenseDAO {
    Expense createExpense(Expense expense);

    Expense createExpenseWithEmployee(Expense expense, int employeeId);

    List<Expense> getAllExpenses();

    List<Expense> getExpensesWithStatus(String status);

    Expense getExpenseWithId(int id);

    Expense updateExpense(Expense expense);

    Expense updateStatus(Expense expense, Status status);

    boolean deleteExpense(int id);

    List<Expense> getAssignedExpenses(int id);
}
