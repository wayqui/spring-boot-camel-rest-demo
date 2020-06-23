package com.wayqui.bootcamel.processors;

import com.wayqui.bootcamel.api.beans.EmployeeResponse;
import com.wayqui.bootcamel.mapper.EmployeeMapper;
import com.wayqui.bootcamel.services.EmployeeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

public class GetAllEmployeesProcessor implements Processor {

    EmployeeService employeeService;

    public GetAllEmployeesProcessor(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void process(Exchange exchange) {
        List<EmployeeResponse> response = EmployeeMapper.INSTANCE.dtosToResponses(
                employeeService.getAllEmployee());
        exchange.getMessage().setBody(response);
    }
}
