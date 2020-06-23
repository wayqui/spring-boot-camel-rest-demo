package com.wayqui.bootcamel.processors;

import com.wayqui.bootcamel.services.EmployeeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DeleteEmployeeProcessor implements Processor {

    EmployeeService employeeService;

    public DeleteEmployeeProcessor(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        final String id = exchange.getIn().getHeader("id",String.class);
        employeeService.deleteEmployee(id);
        exchange.getMessage().setBody(null);
    }
}
