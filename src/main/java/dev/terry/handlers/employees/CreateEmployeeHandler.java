package dev.terry.handlers.employees;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateEmployeeHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);

        Employee createdEmployee = App.employeeService.addNewEmployee(employee);
        String employeeJson = gson.toJson(createdEmployee);

        ctx.status(201);
        ctx.result("Successfully created a new employee: \n" + employeeJson);
    }
}
