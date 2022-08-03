package dev.terry.services;

import dev.terry.data.EmployeeDAO;
import dev.terry.entities.Employee;

import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO)
    {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        //Checks to make sure valid entries
        validateEmployee(employee);

        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        return savedEmployee;
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.employeeDAO.getEmployeeByID(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        validateEmployee(employee);
        return this.employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return this.employeeDAO.deleteEmployee(id);
    }

    void validateEmployee(Employee employee)
    {
        if(employee.getId() == 0)
        {
            throw new RuntimeException("Cannot create an employee with Id of 0.");
        }
        if(employee.getFname() == "" || employee.getLname() == "")
        {
            throw new RuntimeException("Cannot set the name of an employee to nothing.");
        }
//        if(this.employeeDAO.getEmployeeByID(employee.getId()) != null)
//        {
//            //Employee already exists with that ID
//            throw new RuntimeException("There is already and existing employee under that ID, unable to create new employee");
//        }
    }
}
