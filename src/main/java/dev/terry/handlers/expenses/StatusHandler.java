package dev.terry.handlers.expenses;

import dev.terry.app.App;
import dev.terry.entities.Expense;
import dev.terry.entities.enums.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class StatusHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));

        if(App.expenseService.getExpenseWithId(id) != null)
        {
            Expense expense = App.expenseService.getExpenseWithId(id);
            System.out.println(App.expenseService.getExpenseWithId(id));

            if(expense.getStatus().name().equals("PENDING"))
            {
                //Execute code
                if(ctx.path().toLowerCase().contains("approve"))
                {
                    //We approve the expense.
                    SetStatus(expense, Status.APPROVED);
                    ctx.result("Successfully set expense with ID: " + expense.getId() + " to status APPROVED.");
                }
                else if(ctx.path().toLowerCase().contains("deny")){
                    //We deny the expense
                    SetStatus(expense, Status.DENIED);
                    ctx.result("Successfully set expense with ID: " + id + " to status DENIED.");
                }
            }
            else
            {
                ctx.status(422);
                ctx.result("This status is already approved or denied. Unable to modify.");
            }

        }
        else {
            //Return error.
            ctx.status(404);
            ctx.result("There is no ID of " + id + " in the expenses.");
        }
    }

    public void SetStatus(Expense expense, Status status)
    {
        App.expenseService.updateStatus(expense, status);
    }
}
