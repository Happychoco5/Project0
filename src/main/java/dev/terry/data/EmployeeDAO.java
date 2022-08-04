package dev.terry.data;

import dev.terry.entities.Employee;
import dev.terry.entities.Expense;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {

    //Create a new employee
    Employee createEmployee(Employee employee);

    //Show all employees
    Map<Integer, Employee> getAllEmployees();

    //Get employee by ID
    Employee getEmployeeByID(int id);

    //Replace/Update employee
    Employee updateEmployee(Employee employee);

    //Delete an employee
    boolean deleteEmployee(int id);
}
