package dev.terry.handlers.employees;

import com.google.gson.Gson;
import dev.terry.app.App;
import dev.terry.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if(App.employeeService.getEmployeeById(id) != null)
        {
            //Change the employee with what's inside the body based on id
            String employeeJson = ctx.body();
            Gson gson = new Gson();

            Employee employee = gson.fromJson(employeeJson, Employee.class);
            //Check if the id inputted is equal to the id in the body of the JSON
            if(employee.getId() == id)
            {
                //Update the employee
                Employee updatedEmployee = App.employeeService.updateEmployee(employee);
                String json = gson.toJson(updatedEmployee);
                ctx.result("Successfully updated employee with ID " + id + " to " + json);
            }
            else {
                ctx.result("Employee ID mismatch. Cannot update employee with ID " + id + " because ID of inputted employee is " + employee.getId());
                ctx.status(400);
            }

        }
        else
        {
            ctx.result("Could not find employee with ID " + id);
            ctx.status(404);
        }
    }
}
