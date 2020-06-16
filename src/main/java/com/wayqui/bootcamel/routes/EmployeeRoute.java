package com.wayqui.bootcamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .port(9091)
                .host("localhost")
                .dataFormatProperty("prettyPrint", "true")
                .bindingMode(RestBindingMode.json);

        rest("/apis")
                .get("/employees")
                    .consumes(MediaType.APPLICATION_JSON_VALUE)
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .route()
                    .process(exchange -> exchange.getMessage().setBody("This is a GET!"))
                    .endRest()
                .post("/employees")
                    .consumes(MediaType.APPLICATION_JSON_VALUE)
                    .produces(MediaType.APPLICATION_JSON_VALUE)
                    .route()
                    .process(exchange -> exchange.getMessage().setBody("This is a POST!"))
                    .endRest();
    }
}
