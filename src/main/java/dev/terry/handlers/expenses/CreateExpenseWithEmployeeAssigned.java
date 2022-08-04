package dev.terry.handlers.expenses;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseWithEmployeeAssigned implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Gson gson = new Gson();
        if(App.employeeService.getEmployeeById(id) != null){
            String json = ctx.body();
            Expense expense = gson.fromJson(json, Expense.class);
            if(expense.getEmployeeId() == id){
                for(Expense e : App.expenseList) {
                    //Check to make sure the expense with ID does not exist.
                    if (e.getId() == expense.getId()) {
                        //Expense already exists
                        ctx.status(400);
                        ctx.result("Could not create expense with ID: " + expense.getId() + " because one already exists with that ID");
                        return;
                    }
                }

                App.expenseService.createExpense(expense);
                ctx.status(201);
                ctx.result("Successfully created expense with employee " +
                        App.employeeService.getEmployeeById(expense.getEmployeeId()).getFname() + " " +
                        App.employeeService.getEmployeeById(expense.getEmployeeId()).getLname() + " assigned: " + json);
            }
            else {
                ctx.status(400);
                ctx.result("Employee ID mismatch. Cannot create an expense under employee " + id + " if employee ID inputted is " + expense.getEmployeeId());
            }

        }
        else {
            //Could not find employee
            ctx.status(404);
            ctx.result("Could not find employee with ID " + id);
        }
    }
}
