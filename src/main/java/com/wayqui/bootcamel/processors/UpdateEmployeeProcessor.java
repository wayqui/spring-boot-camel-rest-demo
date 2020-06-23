package com.wayqui.bootcamel.processors;

import com.wayqui.bootcamel.api.beans.EmployeeRequest;
import com.wayqui.bootcamel.api.beans.EmployeeResponse;
import com.wayqui.bootcamel.dto.EmployeeDto;
import com.wayqui.bootcamel.mapper.EmployeeMapper;
import com.wayqui.bootcamel.services.EmployeeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class UpdateEmployeeProcessor implements Processor {

    EmployeeService employeeService;

    public UpdateEmployeeProcessor(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        final String id = exchange.getIn().getHeader("id",String.class);
        final EmployeeRequest request = exchange.getIn().getBody(EmployeeRequest.class);

        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.requestToDto(request);

        EmployeeResponse response = EmployeeMapper.INSTANCE.dtoToResponse(
                employeeService.updateEmployee(id, employeeDto));
        exchange.getMessage().setBody(response);
    }
}
