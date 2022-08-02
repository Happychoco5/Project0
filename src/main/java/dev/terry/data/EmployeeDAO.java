package dev.terry.data;

import dev.terry.entities.Employee;

import java.util.Map;

public interface EmployeeDAO {

    //Create a new employee
    Employee createEmployee(Employee employee);

    //Show all employees
    Map<Integer, Employee> showAllEmployees();

    //Get employee by ID
    Employee getEmployeeByID(int id);

    //Replace/Update employee
    Employee updateEmployee(Employee employee);

    //Delete an employee
    boolean deleteEmployee(int id);
}
