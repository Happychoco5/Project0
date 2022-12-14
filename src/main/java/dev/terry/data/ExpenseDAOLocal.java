package dev.terry.data;

import dev.terry.app.App;
import dev.terry.entities.Expense;
import dev.terry.entities.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOLocal implements ExpenseDAO{
    //private List<Expense> expenseList = new ArrayList<>();

    @Override
    public Expense createExpense(Expense expense) {

        //expense.setModifiable(true);
        expense.setStatus(Status.PENDING);

        App.expenseList.add(expense);
        return expense;
    }

    @Override
    public Expense createExpenseWithEmployee(Expense expense, int employeeId) {
        expense.setEmployeeId(employeeId);
        return createExpense(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {

        return App.expenseList;
    }

    @Override
    public List<Expense> getExpensesWithStatus(String status) {
        List<Expense> tempList = new ArrayList<>();
        for(Expense e : App.expenseList){
            if(e.getStatus().name().equals(status))
            {
                tempList.add(e);
            }
        }

        return tempList;
    }

    @Override
    public Expense getExpenseWithId(int id) {
        for(Expense e : App.expenseList)
        {
            if(e.getId() == id)
            {
                return e;
            }

        }
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        int index = -1;
        for(int i = 0; i < App.expenseList.size(); i++)
        {
            if(App.expenseList.get(i).getId() == expense.getId()){
                //This is the one we want to modify
                //Delete this
                index = i;
                break;
            }
        }
        expense.setStatus(Status.PENDING);
        App.expenseList.set(index, expense);
        return expense;
    }

    @Override
    public Expense updateStatus(Expense expense, Status status) {
        int index = -1;
        for(int i = 0; i < App.expenseList.size(); i++)
        {
            if(App.expenseList.get(i).getId() == expense.getId()){
                //This is the one we want to modify
                //Delete this
                index = i;
                break;
            }
        }
        expense.setStatus(status);
        App.expenseList.set(index, expense);
        return expense;
    }

    @Override
    public boolean deleteExpense(int id) {
        if(getExpenseWithId(id) != null){
            //Delete the expense, return true
            App.expenseList.remove(getExpenseWithId(id));
            return true;
        }
        return false;
    }

    @Override
    public List<Expense> getAssignedExpenses(int id) {
        List<Expense> myExpenses = new ArrayList<>();
        for(Expense e : App.expenseList)
        {
            if(e.getEmployeeId() == id){
                //Place into list
                myExpenses.add(e);
            }
        }
        return myExpenses;
    }

}
