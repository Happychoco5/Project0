package dev.terry.tests;

import dev.terry.data.EmployeeDAO;
import dev.terry.data.EmployeeDAOLocal;
import dev.terry.entities.Employee;
import dev.terry.services.EmployeeService;
import dev.terry.services.EmployeeServiceImpl;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOLocal();
    static EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);

    @Test
    @Order(1)
    void create_employee_test()
    {
        Employee employee = new Employee(1, "", "Frank");
        Employee savedEmployee = employeeService.addNewEmployee(employee);

        Assertions.assertNotEquals(0, savedEmployee.getId());
        Assertions.assertNotEquals("", savedEmployee.getFname());
        Assertions.assertNotEquals("", savedEmployee.getLname());
    }

    @Test
    @Order(2)
    void show_all_employees()
    {
        Assertions.assertNotEquals(0, employeeService.getAllEmployees().size());
    }

    @Test
    @Order(3)
    void get_employee_by_id()
    {
        Employee employee = employeeService.getEmployeeById(1);
        Assertions.assertEquals(1, employee.getId());
    }

    @Test
    @Order(4)
    void update_employee(){
        Employee employeev2 = new Employee(1, "Brab", "Frunk");
        Employee savedEmployee = employeeService.updateEmployee(employeev2);
        Employee employee = employeeService.getEmployeeById(1);
        Assertions.assertEquals("Brab", employee.getFname());
    }

    @Test
    @Order(5)
    void delete_employee(){
        this.employeeDAO.deleteEmployee(1);
        Assertions.assertNotEquals(1, employeeService.getEmployeeById(1));
    }
}
