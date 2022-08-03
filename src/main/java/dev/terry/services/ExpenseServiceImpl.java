package dev.terry.services;

import dev.terry.app.App;
import dev.terry.data.ExpenseDAO;
import dev.terry.entities.Expense;
import dev.terry.entities.Status;

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
    public List<Expense> showAllExpenses() {
        return expenseDAO.showAllExpenses();
    }

    @Override
    public List<Expense> getExpensesWithStatus(Status status){
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

    void validateExpense(Expense expense){
        if(expense.getId() == 0){
            throw new RuntimeException("Cannot create an expense with an ID of 0");
        }
        if(expense.getAmount() == 0){
            throw new RuntimeException("Cannot create an expense with amount of 0");
        }
        if(expense.getEmployeeAssigned() == 0){
            throw new RuntimeException("Cannot set the employee assigned to 0");
        }
        //We cannot validate against this.
//        for(Expense e : App.expenseList){
//            if(e.getId() == expense.getId())
//            {
//                throw new RuntimeException("Expense with that ID already exists.");
//            }
//        }
    }


}
