package dev.terry.handlers.expenses;

import com.google.gson.Gson;
import dev.terry.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAssignedExpenses implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if(App.employeeService.getEmployeeById(id) != null)
        {
            Gson gson = new Gson();
            //Get the employee's assigned expenses.
            String json = gson.toJson(App.expenseService.getAssignedExpenses(id));
            ctx.result(json);
        }
        else {
            ctx.status(404);
            ctx.result("Could not find employee with ID " + id);
        }
    }
}
