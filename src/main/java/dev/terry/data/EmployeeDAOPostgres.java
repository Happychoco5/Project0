package dev.terry.data;

import dev.terry.app.App;
import dev.terry.entities.Employee;
import dev.terry.exceptions.ResourceNotFoundException;
import dev.terry.util.ConnectionUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDAOPostgres implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into employee values(default, ?, ?)"; //This is where I put my sql statement.
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //This is our "prepared statement" to use

            preparedStatement.setString(1, employee.getFname());
            preparedStatement.setString(2, employee.getLname());

            preparedStatement.execute(); //Executes the given statement with adjusted values

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next(); //Gets the next index of the result set.
            int generatedKey = rs.getInt("id"); //Stores the index we are currently on into an integer.
            employee.setId(generatedKey);
            return employee;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            Map<Integer, Employee> employeeMap = new HashMap<>();
            while(rs.next()){
                Employee employee = new Employee();

                employee.setId(rs.getInt("id"));
                employee.setFname(rs.getString("fName"));
                employee.setLname(rs.getString("lName"));
                System.out.println(employee);

                employeeMap.put(employee.getId(), employee);
            }

            return employeeMap;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next()){
                return null;
                //throw new ResourceNotFoundException("Could not find employee with id " + id);
            }

            Employee employee1 = new Employee();
            employee1.setId(rs.getInt("id"));
            employee1.setFname(rs.getString("fName"));
            employee1.setLname(rs.getString("lName"));

            System.out.println("The employee with the id: " + employee1.getId());
            return employee1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("This went off");
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update employee set fName = ?, lName = ? where id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, employee.getFname());
            preparedStatement.setString(2, employee.getLname());
            preparedStatement.setInt(3, employee.getId());

            preparedStatement.executeUpdate();
            return employee;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            if(App.employeeService.getEmployeeById(id) != null)
            {
                String sql = "delete from employee where id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                return true;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
