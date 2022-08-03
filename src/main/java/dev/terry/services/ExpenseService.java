package dev.terry.services;

import dev.terry.entities.Expense;
import dev.terry.entities.Status;

import java.util.List;

public interface ExpenseService {

    Expense createExpense(Expense expense);

    List<Expense> showAllExpenses();

    List<Expense> getExpensesWithStatus(Status status);

    Expense getExpenseWithId(int id);

    Expense updateExpense(Expense expense);

    Expense updateStatus(Expense expense, Status status);
}
