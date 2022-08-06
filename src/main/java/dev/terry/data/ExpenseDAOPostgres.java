package dev.terry.data;

import dev.terry.entities.Expense;
import dev.terry.entities.enums.Status;

import java.util.List;

public class ExpenseDAOPostgres implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense createExpenseWithEmployee(Expense expense, int employeeId) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public List<Expense> getExpensesWithStatus(String status) {
        return null;
    }

    @Override
    public Expense getExpenseWithId(int id) {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense updateStatus(Expense expense, Status status) {
        return null;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }

    @Override
    public List<Expense> getAssignedExpenses(int id) {
        return null;
    }
}
