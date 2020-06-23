package com.wayqui.bootcamel.processors;

import com.wayqui.bootcamel.api.beans.EmployeeRequest;
import com.wayqui.bootcamel.api.beans.EmployeeResponse;
import com.wayqui.bootcamel.dto.EmployeeDto;
import com.wayqui.bootcamel.mapper.EmployeeMapper;
import com.wayqui.bootcamel.services.EmployeeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class AddEmployeeProcessor implements Processor {

    EmployeeService employeeService;

    public AddEmployeeProcessor(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void process(Exchange exchange) {
        EmployeeRequest request = exchange.getIn().getBody(EmployeeRequest.class);
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.requestToDto(request);
        EmployeeResponse response = EmployeeMapper.INSTANCE.dtoToResponse(
                employeeService.createEmployee(employeeDto));
        exchange.getMessage().setBody(response);
    }
}
