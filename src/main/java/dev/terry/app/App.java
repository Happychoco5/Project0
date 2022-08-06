package dev.terry.app;

import dev.terry.data.EmployeeDAOLocal;
import dev.terry.data.EmployeeDAOPostgres;
import dev.terry.data.ExpenseDAOLocal;
import dev.terry.entities.Expense;
import dev.terry.handlers.employees.*;
import dev.terry.handlers.expenses.*;
import dev.terry.services.EmployeeService;
import dev.terry.services.EmployeeServiceImpl;
import dev.terry.services.ExpenseService;
import dev.terry.services.ExpenseServiceImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class App {
    public final static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public final static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOLocal());

    public static List<Expense> expenseList = new ArrayList<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        //Employee Routes
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        GetAllEmployeesHandler showAllEmployeesHandler = new GetAllEmployeesHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();

        app.post("/employees", createEmployeeHandler);//Adds a new employee based on JSON file to the list of employees
        app.get("/employees", showAllEmployeesHandler); // Returns all employees
        app.get("/employees/{id}", getEmployeeByIdHandler); //Get an employee by ID
        app.put("/employees/{id}", updateEmployeeHandler); //Update an existing employee
        app.delete("/employees/{id}", deleteEmployeeHandler); //delete an employee by ID

        //Expense Routes
        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        GetAllExpensesHandler getAllExpensesHandler = new GetAllExpensesHandler();
        GetExpenseByIDHandler getExpenseByID = new GetExpenseByIDHandler();
        UpdateExpenseByIDHandler updateExpenseByIDHandler = new UpdateExpenseByIDHandler();
        StatusHandler statusHandler = new StatusHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();
        GetAssignedExpenses getAssignedExpenses = new GetAssignedExpenses();
        CreateExpenseWithEmployeeAssigned createExpenseWithEmployeeAssigned = new CreateExpenseWithEmployeeAssigned();

        app.post("/expenses", createExpenseHandler); //Creates a new expense based on JSON file
        app.get("/expenses", getAllExpensesHandler); //Returns all expenses
        app.get("/expenses/{id}", getExpenseByID); //Get expense with ID
        app.put("/expenses/{id}", updateExpenseByIDHandler); //Modify an expense based on id
        app.patch("/expenses/{id}/approve", statusHandler); //Change an expense's status to APPROVED
        app.patch("/expenses/{id}/deny", statusHandler); //Same as above, except to DENY
        app.delete("/expenses/{id}", deleteExpenseHandler); //Deletes the expense with the id specified.

        //Final routes
        app.get("/employees/{id}/expenses", getAssignedExpenses); //Get all expenses assigned to this employee.
        app.post("/employees/{id}/expenses", createExpenseWithEmployeeAssigned); //Create expense with employee {id} assigned

        app.start();
    }
}
