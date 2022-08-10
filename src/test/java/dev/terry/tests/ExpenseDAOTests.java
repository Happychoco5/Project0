package dev.terry.tests;

import dev.terry.app.App;
import dev.terry.data.*;
import dev.terry.entities.Employee;
import dev.terry.entities.Expense;
import dev.terry.entities.enums.Category;
import dev.terry.entities.enums.Status;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOPostgres();
    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();

    @Test
    @Order(1)
    void create_expense(){
        Employee employee = new Employee(1, "Barber", "Frank");
        Employee savedEmployee = employeeDAO.createEmployee(employee);

        Expense expense = new Expense(0, "I am burger", Category.FOOD, 200, 2);
        Expense savedExpense = expenseDAO.createExpense(expense);
        Assertions.assertNotEquals(0, savedExpense.getId());
    }

    @Test
    @Order(2)
    void create_expense_with_employee(){
        Expense expense = new Expense(24, "I am travel", Category.TRAVEL, 1000, 2);
        Expense savedExpense = expenseDAO.createExpenseWithEmployee(expense,1);
        Assertions.assertNotEquals(0, savedExpense.getId());
    }

    @Test
    @Order(3)
    void show_all_expenses(){
        Assertions.assertEquals(2, expenseDAO.getAllExpenses().size());
    }

    @Test
    @Order(4)
    void show_expenses_with_status(){
        String status = "PENDING";
        Assertions.assertEquals(2, expenseDAO.getExpensesWithStatus(status).size());
    }

    @Test
    @Order(5)
    void get_expense_by_id() {
        Expense expense = expenseDAO.getExpenseWithId(1);
        Assertions.assertEquals("I am burger", expense.getDescription());
    }

    @Test
    @Order(6)
    void update_expense() {
        Expense expensev2 = new Expense(1, "Burger time", Category.FOOD, 399, 2);
        expenseDAO.updateExpense(expensev2);
        Expense expense = expenseDAO.getExpenseWithId(1);
        Assertions.assertEquals("Burger time", expense.getDescription());
    }

    @Test
    @Order(7)
    void update_status(){
        Expense expense = new Expense(1, "Calories", Category.FOOD, 350, 2);
        expenseDAO.createExpense(expense);
        Expense savedExpense = expenseDAO.updateStatus(expense, Status.APPROVED);
        Assertions.assertEquals(Status.APPROVED, expenseDAO.getExpenseWithId(3).getStatus());
    }
    @Test
    @Order(8)
    void delete_expense(){
        Assertions.assertTrue(expenseDAO.deleteExpense(1));
    }
    @Test
    @Order(9)
    void get_assigned_expenses(){
        Expense expense1 = new Expense(1, "Sandwich", Category.FOOD, 200, 2);
        Expense expense2 = new Expense(1, "Plane", Category.TRAVEL, 434, 2);
        Expense expense3 = new Expense(1, "The Deep", Category.MISC, 865, 2);
        Expense expense4 = new Expense(1, "Crunchy", Category.FOOD, 9065, 2);
        expenseDAO.createExpense(expense1);
        expenseDAO.createExpense(expense2);
        expenseDAO.createExpense(expense3);
        expenseDAO.createExpense(expense4);

        List<Expense> expenseList = expenseDAO.getAssignedExpenses(2);

        Assertions.assertEquals(6, expenseList.size());
    }
}
