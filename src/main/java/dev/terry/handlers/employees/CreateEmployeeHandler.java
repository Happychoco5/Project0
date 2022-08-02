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

        if(App.employeeService.getEmployeeById(employee.getId()) == null)
        {
            Employee createdEmployee = App.employeeService.addNewEmployee(employee);
            String employeeJson = gson.toJson(createdEmployee);

            ctx.status(201);
            ctx.result("Successfully created a new employee: \n" + employeeJson);
        }
        else
        {
            ctx.status(400);
            ctx.result("There is already an employee with the ID: " + employee.getId() + ". Could not add this employee to the server.");
        }
    }
}
