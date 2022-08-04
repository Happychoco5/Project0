package dev.terry.tests;

import dev.terry.data.ExpenseDAO;
import dev.terry.data.ExpenseDAOLocal;
import dev.terry.entities.Expense;
import dev.terry.entities.enums.Category;
import dev.terry.entities.enums.Status;
import dev.terry.services.ExpenseService;
import dev.terry.services.ExpenseServiceImpl;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseServiceTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOLocal();
    static ExpenseService expenseService = new ExpenseServiceImpl(expenseDAO);

    @Test
    @Order(1)
    void create_expense(){
        Expense expense = new Expense(23, "I am burger", Category.FOOD, 200, 222);
        Expense savedExpense = expenseService.createExpense(expense);
        Assertions.assertNotEquals(0, savedExpense.getId());
    }

    @Test
    @Order(2)
    void create_expense_with_employee(){
        Expense expense = new Expense(24, "I am travel", Category.TRAVEL, 1000, 222);
        Expense savedExpense = expenseService.createExpenseWithEmployee(expense,222);
        Assertions.assertNotEquals(0, savedExpense.getId());
    }

    @Test
    @Order(3)
    void show_all_expenses(){
        Assertions.assertEquals(2, expenseService.getAllExpenses().size());
    }

    @Test
    @Order(4)
    void show_expenses_with_status(){
        String status = "PENDING";
        Assertions.assertEquals(2, expenseService.getExpensesWithStatus(status).size());
    }

    @Test
    @Order(5)
    void get_expense_by_id() {
        Expense expense = expenseService.getExpenseWithId(23);
        Assertions.assertEquals("I am burger", expense.getDescription());
    }

    @Test
    @Order(6)
    void update_expense() {
        Expense expensev2 = new Expense(24, "Burger time", Category.FOOD, 399, 222);
        expenseService.updateExpense(expensev2);
        Expense expense = expenseService.getExpenseWithId(24);
        Assertions.assertEquals("Burger time", expense.getDescription());
    }

    @Test
    @Order(7)
    void update_status(){
        Expense expense = new Expense(27, "Calories", Category.FOOD, 350, 222);
        expenseService.createExpense(expense);
        expenseService.updateStatus(expense, Status.APPROVED);
        Assertions.assertEquals(Status.APPROVED, expense.getStatus());
    }
    @Test
    @Order(8)
    void delete_expense(){
        expenseService.deleteExpense(24);
        Assertions.assertEquals(null, expenseService.getExpenseWithId(24));
    }
    @Test
    @Order(9)
    void get_assigned_expenses(){
        Expense expense1 = new Expense(29, "Sandwich", Category.FOOD, 200, 214);
        Expense expense2 = new Expense(30, "Plane", Category.TRAVEL, 434, 223);
        Expense expense3 = new Expense(31, "The Deep", Category.MISC, 865, 223);
        Expense expense4 = new Expense(32, "Crunchy", Category.FOOD, 9065, 223);
        expenseService.createExpense(expense1);
        expenseService.createExpense(expense2);
        expenseService.createExpense(expense3);
        expenseService.createExpense(expense4);

        List<Expense> expenseList = expenseService.getAssignedExpenses(223);

        Assertions.assertEquals(3, expenseList.size());
    }
}
