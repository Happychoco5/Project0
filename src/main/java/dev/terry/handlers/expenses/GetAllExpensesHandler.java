package dev.terry.handlers.expenses;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GetAllExpensesHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        String status = ctx.queryParam("status");
        if(status == null)
        {
            String json = gson.toJson(App.expenseService.getAllExpenses());
            ctx.json(json);
        }
        else
        {
            //List<Expense> expenses = App.expenseService.getExpensesWithStatus(status);
            String json = gson.toJson(App.expenseService.getExpensesWithStatus(status));
            ctx.result(json);

        }
    }
}
