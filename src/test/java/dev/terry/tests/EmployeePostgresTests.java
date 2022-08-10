package dev.terry.tests;

import dev.terry.data.EmployeeDAO;
import dev.terry.data.EmployeeDAOLocal;
import dev.terry.data.EmployeeDAOPostgres;
import dev.terry.entities.Employee;
import dev.terry.util.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeePostgresTests {
    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();

//    @BeforeAll
//    static void setup(){
//        try(Connection conn = ConnectionUtil.createConnection()){
//            String sql = "create table employee(\n" +
//                    "\tid serial primary key,\n" +
//                    "\tfName varchar(50) not null,\n" +
//                    "\tlName varchar(50) not null\n" +
//                    ");";
//            Statement statement = conn.createStatement();
//            statement.execute(sql);
//        }catch(SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }

    @Test
    @Order(1)
    void create_employee_test()
    {
        Employee employee = new Employee(0, "Barber", "Frank");
        Employee employee1 = new Employee(0, "Frank", "Sinatraa");
        employeeDAO.createEmployee(employee1);
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
        Employee employeev2 = new Employee(1, "Brab", "Frunk");
        Employee savedEmployee = employeeDAO.updateEmployee(employeev2);
        Employee employee = employeeDAO.getEmployeeByID(1);
        Assertions.assertEquals("Brab", employee.getFname());
    }

    @Test
    @Order(5)
    void delete_employee(){
        Assertions.assertTrue(this.employeeDAO.deleteEmployee(1));
    }

//        @AfterAll //Runs after the last test finishes
//    static void teardown(){
//        try(Connection conn = ConnectionUtil.createConnection()){
//            String sql = "drop table employee";
//            Statement statement = conn.createStatement();
//            statement.execute(sql);
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
}
