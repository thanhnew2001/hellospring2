# hellospring2

This Hello Spring 2 example demonstrates how to use Maven and Spring. With IntelliJ IDEA, we don't have to download and install Maven but it is integrated into the IDE. Very handy, right?

What is Maven? 
Basically, Maven is a build tool that help us to download neccessary libraries, build our source code to jar or war.
To use Maven we have to create a pom.xml file and a project structure: src/main/java
In fact, if we use Intellij IDEA to create a Maven project, we don't have to do all these boilerplate stuff

Spring
Spring is very common now so everyone know that Spring is a Inversion of Control container or Dependency Injection framework. If that sounds too much complicated, let think about Spring as a tool that help us create new object without using the keyword new. Because we don't have to create new object, we don't have to set any member objects but Spring will also take care of that.

As the setting of member classes is moved away from our code, it is believed that our code is more readable and easier to maintain. Someone calls it as loosely couple. 

To use Spring we need to add a dependency to Maven pom.xml
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.0.0.RELEASE</version>
        </dependency>

We have 3 classes: EmployeeService, EmployeeApplication, and MainApplication. 
EmployeeApplication has a member class EmployeeService to perform some tasks. We call it as a dependency.
To use EmployeeApplication we don't have to create any class. Spring will do it for us if we configure the beans.xml file properly:

<?xml version = "1.0" encoding = "UTF-8"?>
<beans xmlns = "http://www.springframework.org/schema/beans"
       xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation = "http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <bean id="rmit.EmployeeService" class="rmit.EmployeeService">
    </bean>
    
    <bean id="rmit.EmployeeApplication" class="rmit.EmployeeApplication">
        <property name="employeeService" ref="rmit.EmployeeService"/>
    </bean>
</beans>

In the pom.xml, the EmployeeApplication bean has a property namely EmployeeService. This is how we tell Spring inject the dependency for us. 

In the EmployeeApplication, we need a setter method to help Spring inject this dependency:

    EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
 To use it, we need to create an object called ClassPathXMLApplicationContext, we call getBeans method from this object to create object we want:
 
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        EmployeeApplication employeeApplication = context.getBean(EmployeeApplication.class);

        String employees = employeeApplication.employeeService.getEmployees();

        System.out.println(employees);
        
  Notice that this is very similar to how the Factory design pattern works.
  
  Many will start to ask questions if we have to create ClassPathXMLApplicationContext manually like this, how can we make Spring work with an web application. 
  
  Don't worry Spring offer a special Servlet namely ServletDispatcher, all the web request must pass through this servlet and Spring will delegage corresponding controller to process the request. We do all these configuration in web.xml file.
  
  Remember web.xml is a web descriptor that always comes with a web application.
