package dev.terry.handlers.expenses;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseByIDHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Gson gson = new Gson();
        String json = ctx.body();

        if(App.expenseService.getExpenseWithId(id) != null)
        {
            Expense expense = gson.fromJson(json, Expense.class);
            if(App.employeeService.getEmployeeById(expense.getEmployeeId()) != null)
            {
                if(App.expenseService.getExpenseWithId(id).getStatus().name().equals("PENDING"))
                {
                    if(expense.getId() == id)
                    {
                        //Throw in the expense that we want to create from the ctx body
                        App.expenseService.updateExpense(expense);
                        ctx.result("Successfully updated the expense with ID of " + id + " " + json);
                    }
                    else {
                        ctx.status(400);
                        ctx.result("Expense ID mismatch. Cannot update expense with " + id + "  ID inputted is " + expense.getId());
                    }
                }
                else {
                    ctx.status(400);
                    ctx.result("Cannot modify because this expense has already been APPROVED or DENIED");
                }
            }
            else {
                ctx.status(400);
                ctx.result("Employee ID mismatch. Cannot update expense with " + expense.getEmployeeId() + " because ID inputted is " + expense.getId());
            }

        }
        else {
            ctx.status(404);
            ctx.result("Could not find expense with ID " + id);
        }

    }
}
