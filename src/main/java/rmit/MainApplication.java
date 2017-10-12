package rmit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by CoT on 10/12/17.
 */
public class MainApplication {

    public static void main(String[] args){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        EmployeeApplication employeeApplication = context.getBean(EmployeeApplication.class);

        String employees = employeeApplication.employeeService.getEmployees();

        System.out.println(employees);

    }
}
