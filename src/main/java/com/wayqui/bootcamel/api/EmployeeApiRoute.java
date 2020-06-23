package com.wayqui.bootcamel.api;

import com.wayqui.bootcamel.api.beans.EmployeeRequest;
import com.wayqui.bootcamel.api.beans.EmployeeResponse;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class EmployeeApiRoute extends RouteBuilder {

    @Value("${server.address}")
    private String host;

    @Value("${server.port}")
    private int port;

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .port(port)
                .host(host)
                .bindingMode(RestBindingMode.json)
                .clientRequestValidation(true)
                .enableCORS(true);

        rest("/apis")
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .consumes(MediaType.APPLICATION_JSON_VALUE)
            .get("/employees")
                .to("direct:getAllEmployees")
            .get("/employees/{id}")
                .to("direct:getEmployee")
            .post("/employees")
                .type(EmployeeRequest.class)
                .outType(EmployeeResponse.class)
                .to("direct:addEmployee")
            .put("/employees/{id}")
                .type(EmployeeRequest.class)
                .outType(EmployeeResponse.class)
                .to("direct:updateEmployee")
            .delete("/employees/{id}")
                .to("direct:deleteEmployee");
    }
}
