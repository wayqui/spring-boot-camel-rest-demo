package com.wayqui.bootcamel.routes;

import com.wayqui.bootcamel.dto.EmployeeDto;
import com.wayqui.bootcamel.mapper.EmployeeMapper;
import com.wayqui.bootcamel.routes.beans.EmployeeRequest;
import com.wayqui.bootcamel.routes.beans.EmployeeResponse;
import com.wayqui.bootcamel.services.EmployeeService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeRoute extends RouteBuilder {

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
                .dataFormatProperty("prettyPrint", "true")
                .bindingMode(RestBindingMode.json);

        rest("/apis")
                .get("/employees")
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .route()
                    .process(exchange -> {
                        List<EmployeeResponse> response = EmployeeMapper.INSTANCE.dtosToResponses(
                                employeeService.getAllEmployee());
                        exchange.getMessage().setBody(response);
                    })
                    .endRest()
                .get("/employees/{id}")
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .route()
                    .process(exchange -> {
                        final String id = exchange.getIn().getHeader("id",String.class);
                        EmployeeResponse response = EmployeeMapper.INSTANCE.dtoToResponse(
                                employeeService.getEmployee(id));
                        exchange.getMessage().setBody(response);
                    })
                    .endRest()
                .post("/employees")
                    .consumes(MediaType.APPLICATION_JSON_VALUE)
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .type(EmployeeRequest.class)
                    .outType(EmployeeResponse.class)
                    .route()
                    .process(exchange -> {
                        EmployeeRequest request = exchange.getIn().getBody(EmployeeRequest.class);
                        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.requestToDto(request);
                        EmployeeResponse response = EmployeeMapper.INSTANCE.dtoToResponse(
                                employeeService.createEmployee(employeeDto));
                        exchange.getMessage().setBody(response);
                    })
                    .endRest()
                .put("/employees/{id}")
                    .consumes(MediaType.APPLICATION_JSON_VALUE)
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .type(EmployeeRequest.class)
                    .outType(EmployeeResponse.class)
                    .route()
                    .process(exchange -> {
                        final String id = exchange.getIn().getHeader("id",String.class);
                        final EmployeeRequest request = exchange.getIn().getBody(EmployeeRequest.class);

                        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.requestToDto(request);

                        EmployeeResponse response = EmployeeMapper.INSTANCE.dtoToResponse(
                                employeeService.updateEmployee(id, employeeDto));
                        exchange.getMessage().setBody(response);
                    })
                    .endRest()
                .delete("/employees/{id}")
                    .route()
                    .process(exchange -> {
                        final String id = exchange.getIn().getHeader("id",String.class);
                        employeeService.deleteEmployee(id);
                        exchange.getMessage().setBody(null);
                    })
                .endRest();
    }
}
