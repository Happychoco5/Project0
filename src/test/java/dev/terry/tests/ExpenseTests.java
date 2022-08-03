package dev.terry.tests;

import dev.terry.app.App;
import dev.terry.data.ExpenseDAO;
import dev.terry.data.ExpenseDAOLocal;
import dev.terry.entities.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpenseTests {

    ExpenseDAO expenseDAO = new ExpenseDAOLocal();

    @Test
    void create_expense(){
        //Expense expense = new Expense(21, 200.0, 1);
        //Assertions.assertNotEquals(0, expense.getId());
    }

    @Test
    void show_all_expenses(){
        App.expenseService.showAllExpenses();
        Assertions.assertNotEquals(0, App.expenseList.size());
    }

    @Test
    void show_expenses_with_status(){
        String status = "PENDING";
        Assertions.assertEquals(status, App.expenseList.get(0).getStatus());
    }

    @Test
    void get_expense_by_id()
    {

    }
}
