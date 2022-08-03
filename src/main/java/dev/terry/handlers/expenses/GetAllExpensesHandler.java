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
            List<String> stringList = new ArrayList<>();
            for(Expense e : App.expenseList){
                String expense = e.toString();
                stringList.add(expense);
            }
            String json = gson.toJson(stringList);
            ctx.json(json);
        }
        else
        {
            List<Expense> expenses = App.expenseService.getExpensesWithStatus(status);
            String json = gson.toJson(expenses);
            ctx.result(json);

        }
    }
}
