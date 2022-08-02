package dev.terry.handlers.employees;

import com.google.gson.Gson;
import dev.terry.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.employeeService.deleteEmployee(id);
        if(result)
        {
            //deleted the object
            ctx.result("Deleted employee with id " + id);
        } else{
            //Unable to find the object
            ctx.status(404);
            ctx.result("Could not find employee of id " + id);
        }
    }
}
