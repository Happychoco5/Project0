package dev.terry.services;

import dev.terry.data.ExpenseDAO;
import dev.terry.entities.Expense;
import dev.terry.entities.enums.Status;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;
    public ExpenseServiceImpl(ExpenseDAO expenseDAO){
        this.expenseDAO = expenseDAO;
    }

    @Override
    public Expense createExpense(Expense expense) {
        validateExpense(expense);

        return expenseDAO.createExpense(expense);
    }

    @Override
    public Expense createExpenseWithEmployee(Expense expense, int employeeId) {
        validateExpense(expense);

        return expenseDAO.createExpenseWithEmployee(expense, employeeId);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseDAO.getAllExpenses();
    }

    @Override
    public List<Expense> getExpensesWithStatus(String status){
        return expenseDAO.getExpensesWithStatus(status);
    }

    @Override
    public Expense getExpenseWithId(int id) {
        return this.expenseDAO.getExpenseWithId(id);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        validateExpense(expense);
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public Expense updateStatus(Expense expense, Status status) {
        return this.expenseDAO.updateStatus(expense, status);
    }

    @Override
    public boolean deleteExpense(int id) {
        return this.expenseDAO.deleteExpense(id);
    }

    @Override
    public List<Expense> getAssignedExpenses(int id) {
        return this.expenseDAO.getAssignedExpenses(id);
    }

    void validateExpense(Expense expense){
        if(expense.getId() == 0){
            throw new RuntimeException("Cannot create an expense with an ID of 0");
        }
        if(expense.getAmount() <= 0){
            throw new RuntimeException("Cannot create an expense with amount of, or lower than, 0");
        }
        if(expense.getEmployeeId() == 0){
            throw new RuntimeException("Cannot set the employee assigned to 0");
        }
    }


}
