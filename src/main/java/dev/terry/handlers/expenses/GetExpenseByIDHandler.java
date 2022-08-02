package dev.terry.handlers.expenses;

import com.google.gson.Gson;
import dev.terry.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetExpenseByIDHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Gson gson = new Gson();
        if(App.expenseService.getExpenseWithId(id) != null)
        {
            //Return the object
            String json = gson.toJson(App.expenseService.getExpenseWithId(id));
            ctx.result(json);
        } else {
            ctx.status(404);
          ctx.result("There is no expense with ID " + id + ". Please try again with a different ID.");
        }
    }
}
