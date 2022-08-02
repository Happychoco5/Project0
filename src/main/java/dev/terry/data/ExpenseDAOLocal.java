package dev.terry.data;

import dev.terry.app.App;
import dev.terry.entities.Expense;
import dev.terry.entities.Status;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOLocal implements ExpenseDAO{
    private List<Expense> expenseList = new ArrayList<>();

    @Override
    public Expense createExpense(Expense expense) {

        //expense.setModifiable(true);
        expense.setStatus(Status.PENDING);

        App.expenseList.add(expense);
        return expense;
    }

    @Override
    public List<Expense> showAllExpenses() {

        return App.expenseList;
    }

    @Override
    public List<Expense> getExpensesWithStatus(Status status) {
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
    public Expense updateExpense(int id) {
        for(Expense e : App.expenseList)
        {
            if(e.getId() == id){
                //This is the one we want to modify
                return e;
            }
        }
        //If we can't find it, return null
        return null;
    }


}
