package dev.terry.handlers.expenses;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShowAllExpensesHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        List<String> stringList = new ArrayList<>();
        System.out.println("Outside the for loop");
        if(App.expenseList != null)
        {
            for(Expense e : App.expenseList){
                String expense = e.toString();
                stringList.add(expense);
            }
        }
        else {
            ctx.result("There are currently no items within the expenses list");
        }
        Gson gson = new Gson();
        String json = gson.toJson(stringList);
        ctx.json(json);
    }
}
