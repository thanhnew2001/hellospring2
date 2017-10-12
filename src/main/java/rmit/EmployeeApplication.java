package rmit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by CoT on 10/12/17.
 */
public class EmployeeApplication {

    EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String getEmployees(){
        return employeeService.getEmployees();
    }


}
