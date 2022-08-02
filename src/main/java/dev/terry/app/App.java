package dev.terry.app;

import dev.terry.data.EmployeeDAOLocal;
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
    public final static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());
    public final static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOLocal());

    public static List<Expense> expenseList = new ArrayList<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        //Employee Routes
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        ShowAllEmployeesHandler showAllEmployeesHandler = new ShowAllEmployeesHandler();
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
        ShowAllExpensesHandler showAllExpensesHandler = new ShowAllExpensesHandler();
        GetExpensesWithStatusHandler expensesWithStatusHandler = new GetExpensesWithStatusHandler();
        GetExpenseByIDHandler getExpenseByID = new GetExpenseByIDHandler();
        UpdateExpenseByIDHandler updateExpenseByIDHandler = new UpdateExpenseByIDHandler();

        app.post("/expenses", createExpenseHandler); //Creates a new expense based on JSON file
        app.get("/expenses", showAllExpensesHandler); //Returns all expenses
        app.get("/expenses?status={status}", expensesWithStatusHandler); //Returns expenses with status.
        app.get("/expenses/{id}", getExpenseByID); //Get expense with ID
        app.put("/expenses/{id}", updateExpenseByIDHandler); //Modify an expense based on id

        app.start();
    }
}
