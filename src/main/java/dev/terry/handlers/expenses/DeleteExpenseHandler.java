package dev.terry.handlers.expenses;

import dev.terry.app.App;
import dev.terry.entities.enums.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));

        if(App.expenseService.getExpenseWithId(id) != null)
        {
            //Check to make sure it is pending
            if(App.expenseService.getExpenseWithId(id).getStatus().equals(Status.PENDING))
            {
                //We can delete it now
                App.expenseService.deleteExpense(id);
                ctx.result("Successfully deleted expense with ID: " + id);
            }
            else {
                ctx.status(422);
                ctx.result("Unable to delete the expense specified because it has already been APPROVED or DENIED.");
            }
        }
        else {
            //Could not find this expense
            ctx.status(404);
            ctx.result("Could not find expense with ID of " + id);
        }
    }
}
