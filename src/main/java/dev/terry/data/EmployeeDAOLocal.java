package dev.terry.data;

import dev.terry.app.App;
import dev.terry.entities.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDAOLocal implements EmployeeDAO{

    private Map<Integer, Employee> employeeTable = new HashMap<>();
    private int idMaker = 1;

    //Create employee
    @Override
    public Employee createEmployee(Employee employee) {
        //employee.setId(idMaker);
        //idMaker++;
        this.employeeTable.put(employee.getId(), employee);

        return employee;
    }

    @Override
    public Map<Integer, Employee> showAllEmployees() {
        return this.employeeTable;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        Employee savedEmployee = this.employeeTable.get(id);
        return savedEmployee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        this.employeeTable.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public boolean deleteEmployee(int id) {
        Employee employee = this.employeeTable.remove(id);
        if(employee == null)
        {
            return false;
        }
        else {
            return true;
        }
    }
}
