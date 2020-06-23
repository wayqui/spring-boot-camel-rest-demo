package com.wayqui.bootcamel.processors;

import com.wayqui.bootcamel.api.beans.EmployeeResponse;
import com.wayqui.bootcamel.mapper.EmployeeMapper;
import com.wayqui.bootcamel.services.EmployeeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class GetEmployeeProcessor implements Processor {

    EmployeeService employeeService;

    public GetEmployeeProcessor(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void process(Exchange exchange) {
        final String id = exchange.getIn().getHeader("id",String.class);
        EmployeeResponse response = EmployeeMapper.INSTANCE.dtoToResponse(
                employeeService.getEmployee(id));
        exchange.getMessage().setBody(response);
    }
}
