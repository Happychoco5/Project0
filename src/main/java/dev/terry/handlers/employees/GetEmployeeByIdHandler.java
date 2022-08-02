package dev.terry.handlers.employees;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetEmployeeByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));

        if(App.employeeService.getEmployeeById(id) != null)
        {
            Gson gson = new Gson();
            String json = gson.toJson(App.employeeService.getEmployeeById(id));
            ctx.result("Employee with id of " + id + " " + json);
        }
        else {
            ctx.status(404);
            ctx.result("Unable to find employee under ID " + id);
        }
    }
}
