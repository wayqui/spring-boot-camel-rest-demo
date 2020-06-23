package com.wayqui.bootcamel.routes;

import com.wayqui.bootcamel.processors.*;
import com.wayqui.bootcamel.services.EmployeeService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRoutes extends RouteBuilder {

    @Value("${server.address}")
    private String host;

    @Value("${server.port}")
    private int port;

    @Autowired
    EmployeeService employeeService;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .port(port)
                .host(host)
                .bindingMode(RestBindingMode.json)
                .clientRequestValidation(true)
                .enableCORS(true);

        from("direct:getAllEmployees")
                .process(new GetAllEmployeesProcessor(employeeService))
                .endRest();

        from("direct:getEmployee")
                .process(new GetEmployeeProcessor(employeeService))
                .endRest();

        from("direct:addEmployee")
                .process(new AddEmployeeProcessor(employeeService))
                .endRest();

        from("direct:updateEmployee")
                .process(new UpdateEmployeeProcessor(employeeService))
                .endRest();

        from("direct:deleteEmployee")
                .process(new DeleteEmployeeProcessor(employeeService))
                .endRest();
    }
}
