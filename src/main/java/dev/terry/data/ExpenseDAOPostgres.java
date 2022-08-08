package dev.terry.data;

import dev.terry.app.App;
import dev.terry.entities.Expense;
import dev.terry.entities.enums.Category;
import dev.terry.entities.enums.Status;
import dev.terry.exceptions.ResourceNotFoundException;
import dev.terry.util.ConnectionUtil;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOPostgres implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into expense values(default, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, expense.getDescription());
            preparedStatement.setString(2, expense.getType().name());
            preparedStatement.setDouble(3, expense.getAmount());
            preparedStatement.setInt(4, expense.getEmployeeId());
            preparedStatement.setString(5, "PENDING");

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int generatedKey = rs.getInt("id");

            expense.setId(generatedKey);
            return expense;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense createExpenseWithEmployee(Expense expense, int employeeId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            Expense savedExpense = createExpense(expense);

            savedExpense.setEmployeeId(employeeId);

            return savedExpense;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "select * from expense";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Expense> expenseList = new ArrayList<>();
            while(rs.next()){
                Expense expense = new Expense();

                expense.setId(rs.getInt("id"));
                expense.setDescription(rs.getString("description"));
                expense.setType(Category.valueOf(rs.getString("category")));
                expense.setAmount(rs.getDouble("amount"));
                expense.setEmployeeId(rs.getInt("employeeId"));
                expense.setStatus(Status.valueOf(rs.getString("status")));

                expenseList.add(expense);
            }
            return expenseList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Expense> getExpensesWithStatus(String status) {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "select * from expense where status = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, status);

            ResultSet rs = preparedStatement.executeQuery();

            List<Expense> expenseList = new ArrayList<>();

            while(rs.next()){
                Expense expense = new Expense();

                expense.setId(rs.getInt("id"));
                expense.setDescription(rs.getString("description"));
                expense.setType(Category.valueOf(rs.getString("category")));
                expense.setAmount(rs.getDouble("amount"));
                expense.setId(rs.getInt("employeeId"));
                expense.setStatus(Status.valueOf(rs.getString("status")));

                expenseList.add(expense);
            }
            return expenseList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense getExpenseWithId(int id) {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "select * from expense where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()){
                throw new ResourceNotFoundException("Could not find expense with id " + id);
            }

            Expense expense = new Expense();

            expense.setId(rs.getInt("id"));
            expense.setDescription(rs.getString("description"));
            expense.setType(Category.valueOf(rs.getString("category")));
            expense.setAmount(rs.getDouble("amount"));
            expense.setId(rs.getInt("employeeId"));
            expense.setStatus(Status.valueOf(rs.getString("status")));
            System.out.println(expense.getStatus());
            return expense;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set description = ?, category = ?, amount = ?, employeeId = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, expense.getDescription());
            preparedStatement.setString(2, expense.getType().name());
            preparedStatement.setDouble(3, expense.getAmount());
            preparedStatement.setInt(4, expense.getEmployeeId());
            preparedStatement.setString(5, "PENDING");
            preparedStatement.setInt(6, expense.getId());

            preparedStatement.executeUpdate();
            return expense;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Expense updateStatus(Expense expense, Status status) {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            //You cannot get the status from the expense because we never set the status of expense.
            //You have to get it from the App/DAO
            String sql = "update expense set status = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, expense.getId());

            preparedStatement.executeUpdate();

            return expense;

        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteExpense(int id) {
        System.out.println(getExpenseWithId(id));
        if(getExpenseWithId(id) != null)
        {
            try(Connection conn = ConnectionUtil.createConnection())
            {
                //You cannot get the status from the expense because we never set the status of expense.
                //You have to get it from the App/DAO
                String sql = "delete from expense where id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setInt(1, id);
                preparedStatement.execute();

                return true;
            }
            catch(SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Expense> getAssignedExpenses(int id) {

        try(Connection conn = ConnectionUtil.createConnection()){
            List<Expense> expenseList = new ArrayList<>();
            String sql = "select * from expense where employeeId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {
                Expense expense = new Expense();

                expense.setId(rs.getInt("id"));
                expense.setDescription(rs.getString("description"));
                expense.setType(Category.valueOf(rs.getString("category")));
                expense.setAmount(rs.getDouble("amount"));
                expense.setEmployeeId(rs.getInt("employeeId"));
                expense.setStatus(Status.valueOf(rs.getString("status")));

                expenseList.add(expense);
            }
            return expenseList;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
