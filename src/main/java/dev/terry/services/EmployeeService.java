package dev.terry.services;

import dev.terry.entities.Employee;

import java.util.Map;

public interface EmployeeService {

    //Add new employee
    Employee addNewEmployee(Employee employee);

    //Get all employees
    Map<Integer, Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    Employee updateEmployee(Employee employee);

    boolean deleteEmployee(int id);
}
