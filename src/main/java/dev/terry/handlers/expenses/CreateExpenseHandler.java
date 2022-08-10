package dev.terry.handlers.expenses;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();

        Expense expense = gson.fromJson(json, Expense.class);

//        for(Expense e : App.expenseService.getAllExpenses())
//        {
//            //Check to make sure the expense with ID does not exist.
//            if(e.getId() == expense.getId())
//            {
//                //Expense already exists
//                ctx.status(400);
//                ctx.result("Could not create expense with ID: " + expense.getId() + " because one already exists with that ID");
//                return;
//            }
//        }

        if(App.employeeService.getEmployeeById(expense.getEmployeeId()) == null)
        {
            //Employee assigned does not exist.
            ctx.status(400);
            ctx.result("There is no employee with ID of " + expense.getEmployeeId() + ". Please try again with a different ID");
            return;
        }

        String expenseJson = gson.toJson(expense);
        App.expenseService.createExpense(expense);
        ctx.status(201);
        ctx.result("Successfully created expense with employee " +
                App.employeeService.getEmployeeById(expense.getEmployeeId()).getFname() + " " +
                App.employeeService.getEmployeeById(expense.getEmployeeId()).getLname() + " assigned: " + json);
    }
}
