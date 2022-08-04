package dev.terry.tests;

import dev.terry.data.ExpenseDAO;
import dev.terry.data.ExpenseDAOLocal;
import dev.terry.entities.Expense;
import dev.terry.entities.enums.Category;
import dev.terry.entities.enums.Status;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOLocal();

    @Test
    @Order(1)
    void create_expense(){
        Expense expense = new Expense(23, "I am burger", Category.FOOD, 200, 222);
        Expense savedExpense = expenseDAO.createExpense(expense);
        Assertions.assertNotEquals(0, savedExpense.getId());
    }

    @Test
    @Order(2)
    void create_expense_with_employee(){
        Expense expense = new Expense(24, "I am travel", Category.TRAVEL, 1000, 222);
        Expense savedExpense = expenseDAO.createExpenseWithEmployee(expense,222);
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
        Expense expense = expenseDAO.getExpenseWithId(23);
        Assertions.assertEquals("I am burger", expense.getDescription());
    }

    @Test
    @Order(6)
    void update_expense() {
        Expense expensev2 = new Expense(24, "Burger time", Category.FOOD, 399, 222);
        expenseDAO.updateExpense(expensev2);
        Expense expense = expenseDAO.getExpenseWithId(24);
        Assertions.assertEquals("Burger time", expense.getDescription());
    }

    @Test
    @Order(7)
    void update_status(){
        Expense expense = new Expense(27, "Calories", Category.FOOD, 350, 222);
        expenseDAO.createExpense(expense);
        expenseDAO.updateStatus(expense, Status.APPROVED);
        Assertions.assertEquals(Status.APPROVED, expense.getStatus());
    }
    @Test
    @Order(8)
    void delete_expense(){
        expenseDAO.deleteExpense(24);
        Assertions.assertEquals(null, expenseDAO.getExpenseWithId(24));
    }
    @Test
    @Order(9)
    void get_assigned_expenses(){
        Expense expense1 = new Expense(29, "Sandwich", Category.FOOD, 200, 214);
        Expense expense2 = new Expense(30, "Plane", Category.TRAVEL, 434, 223);
        Expense expense3 = new Expense(31, "The Deep", Category.MISC, 865, 223);
        Expense expense4 = new Expense(32, "Crunchy", Category.FOOD, 9065, 223);
        expenseDAO.createExpense(expense1);
        expenseDAO.createExpense(expense2);
        expenseDAO.createExpense(expense3);
        expenseDAO.createExpense(expense4);

        List<Expense> expenseList = expenseDAO.getAssignedExpenses(223);

        Assertions.assertEquals(3, expenseList.size());
    }
}
