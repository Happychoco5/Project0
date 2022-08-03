package dev.terry.tests;

import dev.terry.data.EmployeeDAO;
import dev.terry.data.EmployeeDAOLocal;
import dev.terry.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTests {
    static EmployeeDAO employeeDAO = new EmployeeDAOLocal();

    @Test
    @Order(1)
    void create_employee_test()
    {
        Employee employee = new Employee(1, "Barber", "Frank");
        Employee savedEmployee = employeeDAO.createEmployee(employee);

        Assertions.assertNotEquals(0, savedEmployee.getId());
        Assertions.assertNotEquals("", savedEmployee.getFname());
        Assertions.assertNotEquals("", savedEmployee.getLname());
    }

    @Test
    @Order(2)
    void show_all_employees()
    {
        Assertions.assertNotEquals(0, employeeDAO.getAllEmployees().size());
    }

    @Test
    @Order(3)
    void get_employee_by_id()
    {
        Employee employee = employeeDAO.getEmployeeByID(1);
        Assertions.assertEquals(1, employee.getId());
    }

    @Test
    @Order(4)
    void update_employee(){
        Employee employeev2 = new Employee(2, "Brab", "Frunk");
        Employee savedEmployee = employeeDAO.updateEmployee(employeev2);
        Employee employee = employeeDAO.getEmployeeByID(1);
        Assertions.assertEquals("Brab", employee.getFname());
    }

    @Test
    @Order(5)
    void delete_employee(){
        this.employeeDAO.deleteEmployee(1);
        Assertions.assertNotEquals(1, employeeDAO.getEmployeeByID(1));
    }
}
